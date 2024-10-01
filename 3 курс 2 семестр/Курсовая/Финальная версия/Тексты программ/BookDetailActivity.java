package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.BookWithAuthors;
import com.example.librarystar.Models.Genre;
import com.example.librarystar.Models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {
    TextView above, under, desc;
    Button read, back;
    ApiInterface apiInterface;
    ProgressBar pb;
    ImageView img;
    Book book;
    Genre genre;
    User publisher;
    ArrayList<Author> authors;
    int id;
    int i = 0;
    Context cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cont = this;
        above = findViewById(R.id.book_detail_above);
        back = findViewById(R.id.book_detail_back);
        under = findViewById(R.id.book_detail_under);
        desc = findViewById(R.id.book_detail_description);
        read = findViewById(R.id.book_detail_read);
        pb = findViewById(R.id.book_detail_pb);
        img = findViewById(R.id.book_detail_img);
        id = getIntent().getIntExtra("id", 0);
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        Call<Book> getBook = apiInterface.getBook(id);
        getBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    book = response.body();
                    second_step();
                }
                else{
                    Toast.makeText(BookDetailActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(BookDetailActivity.this, "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDetailActivity.this, BookReadActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDetailActivity.this, UserMainActivity.class);
                startActivity(intent);
            }
        });
    }
    void second_step(){
        Call<Genre> getBook = apiInterface.getGenre(book.getGenre());
        getBook.enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                if (response.isSuccessful()){
                    genre = response.body();
                    final_step();
                }
                else{
                    Toast.makeText(BookDetailActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                Toast.makeText(BookDetailActivity.this, "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void final_step() {
        i = 0;
        authors = new ArrayList<>();
        authors.clear();
        Call<ArrayList<Author>> getAuthorsBook = apiInterface.getAuthorsByBookId(book.getId());
        getAuthorsBook.enqueue(new Callback<ArrayList<Author>>() {
            @Override
            public void onResponse(Call<ArrayList<Author>> call, Response<ArrayList<Author>> response) {
                if (response.isSuccessful()) {
                    authors = response.body();
                    above.setText(book.getName());
                    String text = "";
                    Picasso.with(cont).load("https://lolpigg.pythonanywhere.com" + book.getImage_path()).into(img);
                    for (Author author: authors) {
                        text += "\n" + author.getFull_name();
                    }
                    text += "\n" + book.getYear_of_creating() + "\n" + genre.getName();
                    under.setText(text);
                    desc.setText(book.getDescription());
                    pb.setVisibility(ProgressBar.INVISIBLE);
                    i++;
                } else {
                    Toast.makeText(cont, "Ошибка запроса.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Author>> call, Throwable t) {
                Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
            }
        });
        }
}