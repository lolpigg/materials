package com.example.librarystar;

import android.content.Context;
import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorDetailActivity extends AppCompatActivity {
    ImageView img;
    TextView name, desc;
    RecyclerView rv;
    int id;
    ApiInterface apiInterface;
    Context cont;
    ArrayList<Book> books;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_author_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cont = this;
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        rv = findViewById(R.id.author_detail_rv);
        name = findViewById(R.id.author_detail_above);
        desc = findViewById(R.id.author_detail_under);
        img = findViewById(R.id.author_detail_img);
        pb = findViewById(R.id.author_detail_pb);
        id = getIntent().getIntExtra("id", 0);
        Call<ArrayList<Book>> getBooks = apiInterface.getBooksByAuthorId(id);
        getBooks.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()){
                    books = response.body();
                    rv.setLayoutManager(new LinearLayoutManager(cont, LinearLayoutManager.VERTICAL, false));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(new HoryzontalBookWithoutAuthorsRecyclerAdapter(books, cont));
                    pb.setVisibility(ProgressBar.INVISIBLE);
                }
                else {
                    Toast.makeText(cont, "Не найдено книг", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Toast.makeText(cont, "Ошибка запроса", Toast.LENGTH_SHORT).show();
            }
        });
        Call<Author> getAuthor = apiInterface.getAuthor(id);
        getAuthor.enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if (response.isSuccessful()){
                    Author auth = response.body();
                    name.setText(auth.getFull_name());
                    String a = String.valueOf(auth.getYear_of_birth());
                    if (auth.getYear_of_death() != null) a+=" - " + auth.getYear_of_death();
                    desc.setText(a);
                    Picasso.with(cont).load("https://lolpigg.pythonanywhere.com" + auth.getImage_path()).into(img);
                }
                else {
                    Toast.makeText(cont, "Не найдено книг", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Toast.makeText(cont, "Ошибка запроса", Toast.LENGTH_SHORT).show();
            }
        });
    }
}