package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarystar.Models.BookWithAuthors;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.BookWithAuthors;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthorListActivity extends AppCompatActivity {
    Intent intent;
    ApiInterface apiInterface;
    ArrayList<Author> authors;
    Context cont;
    RecyclerView rv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_author_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cont = this;
        rv = findViewById(R.id.author_vertical_rv);
        pb = findViewById(R.id.author_pb);
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        BottomNavigationView bottomNavigationView = findViewById(R.id.author_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.third_item);
        bottomNavigationView.setBackgroundColor(Color.parseColor("#D29366"));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.first_item:
                        intent = new Intent(AuthorListActivity.this, UserMainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.second_item:
                        intent = new Intent(AuthorListActivity.this, GenreListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.third_item:
                        intent = new Intent(AuthorListActivity.this, AuthorListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.fourth_item:
                        intent = new Intent(AuthorListActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        Call<ArrayList<Author>> getAuthorsBook = apiInterface.getAuthorsList();
        getAuthorsBook.enqueue(new Callback<ArrayList<Author>>() {
            @Override
            public void onResponse(Call<ArrayList<Author>> call, Response<ArrayList<Author>> response) {
                if (response.isSuccessful()){
                    authors = response.body();
                    rv.setLayoutManager(new LinearLayoutManager(cont, LinearLayoutManager.VERTICAL, false));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(new HorizontalAuthorRecyclerAdapter(authors, cont));
                    pb.setVisibility(ProgressBar.INVISIBLE);
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