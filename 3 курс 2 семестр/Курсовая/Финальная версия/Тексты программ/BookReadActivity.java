package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.BookWithAuthors;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookReadActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView pageNumber;
    private TextView pageCount;
    private Button back;
    private int currentPage = 0;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPageObj;
    private ParcelFileDescriptor parcelFileDescriptor;
    private SharedPreferences sharedPreferences;
    private ApiInterface apiInterface;
    private int id;
    private Context cont;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read);

        viewPager = findViewById(R.id.viewPager);
        pageNumber = findViewById(R.id.pageNumber);
        pageCount = findViewById(R.id.pageCount);
        back = findViewById(R.id.read_book_go_back);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BookReadActivity.this, BookDetailActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });
        cont = this;
        sharedPreferences = getSharedPreferences("PDFPrefs", MODE_PRIVATE);
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        Call<Book> getAuthorsBook = apiInterface.getBook(id);
        getAuthorsBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    book = response.body();
                    currentPage = sharedPreferences.getInt("page" + book.getId(), 0);
                    PdfBuild();
                }
                else {
                    Toast.makeText(cont, "Ошибка запроса.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void PdfBuild() {
        String base64Pdf = book.getPdf_path();
        byte[] pdfAsBytes = Base64.decode(base64Pdf, Base64.DEFAULT);

        try {
            File tempFile = File.createTempFile(book.getName(), ".pdf", getCacheDir());
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(pdfAsBytes);
            fos.close();

            parcelFileDescriptor = ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY);
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);

            int pageCount = pdfRenderer.getPageCount();
            this.pageCount.setText(" / " + pageCount);

            PdfPagerAdapter pdfPagerAdapter = new PdfPagerAdapter();
            viewPager.setAdapter(pdfPagerAdapter);
            viewPager.setCurrentItem(currentPage);
            showPage(currentPage);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    showPage(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

        } catch (IOException e) {
            Toast.makeText(this, "Ошибка загрузки PDF", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }




    private class PdfPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(BookReadActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setTag("pdf_page_" + position);

            container.addView(imageView);
            showPage(currentPage);
            return imageView;
        }



        @Override
        public int getCount() {
            return pdfRenderer.getPageCount();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    private void showPage(int index) {
        if (pdfRenderer.getPageCount() <= index) {
            return;
        }

        if (currentPageObj != null) {
            currentPageObj.close();
        }

        currentPageObj = pdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(currentPageObj.getWidth(), currentPageObj.getHeight(), Bitmap.Config.ARGB_8888);
        currentPageObj.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        ImageView imageView = viewPager.findViewWithTag("pdf_page_" + index);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }

        pageNumber.setText("Страница " + (index + 1));
        currentPage = index;
    }



    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("page" + book.getId(), currentPage);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        try {
            if (currentPageObj != null) {
                currentPageObj.close();
            }
            if (pdfRenderer != null) {
                pdfRenderer.close();
            }
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
