package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.BookWithAuthors;
import com.example.librarystar.Models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;
    ArrayList<Book> books;
    ArrayList<Author> authors;
    ApiInterface apiInterface;
    Button all_books;
    RecyclerView rv;
    Context cont;
    ProgressBar pb;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        Intent thisintent = getIntent();
        cont = this;
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        pb = findViewById(R.id.main_progress_bar);
        bottomNavigationView.setBackgroundColor(Color.parseColor("#D29366"));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.first_item:
                        intent = new Intent(UserMainActivity.this, UserMainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.second_item:
                        intent = new Intent(UserMainActivity.this, GenreListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.third_item:
                        intent = new Intent(UserMainActivity.this, AuthorListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.fourth_item:
                        intent = new Intent(UserMainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        rv = findViewById(R.id.user_horizontal_rv);
        all_books = findViewById(R.id.all_books_button);
        all_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UserMainActivity.this, BookListActivity.class);
                startActivity(intent1);
            }
        });
        books = new ArrayList<>();
        authors = new ArrayList<>();
        Call<ArrayList<Book>> getUser = apiInterface.getBooksByUserId(StaticData.user_id);
        getUser.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()){
                    books = response.body();
                    set_rv();
                }
                else {
                    Toast.makeText(cont, "Не найдено любимых книг", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });
    }
    void set_rv(){
        if (books.isEmpty()){
            pb.setVisibility(ProgressBar.INVISIBLE);
            return;
        }
        i = 0;
        ArrayList<BookWithAuthors> balist = new ArrayList<BookWithAuthors>();
        authors = new ArrayList<>();
        for (Book book : books){
            authors.clear();
            Call<ArrayList<Author>> getAuthorsBook = apiInterface.getAuthorsByBookId(book.getId());
            getAuthorsBook.enqueue(new Callback<ArrayList<Author>>() {
                @Override
                public void onResponse(Call<ArrayList<Author>> call, Response<ArrayList<Author>> response) {
                    if (response.isSuccessful()){
                        authors = response.body();
                        balist.add(new BookWithAuthors(book, authors){});
                        if (i+1==books.size()){
                            rv.setLayoutManager(new LinearLayoutManager(cont, LinearLayoutManager.HORIZONTAL, false));
                            rv.setHasFixedSize(true);
                            rv.setAdapter(new VerticalBookRecyclerAdapter(balist, cont));
                            pb.setVisibility(ProgressBar.INVISIBLE);
                        }
                        i++;
                    }
                    else {
                        Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Author>> call, Throwable t) {

                }
            });
        }
    }
}