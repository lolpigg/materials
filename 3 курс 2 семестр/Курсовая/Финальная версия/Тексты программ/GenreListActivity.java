package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.BookWithAuthors;
import com.example.librarystar.Models.Genre;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreListActivity extends AppCompatActivity {
    Intent intent;
    ApiInterface apiInterface;
    Context cont;
    ArrayList<Genre> genres;
    RecyclerView rv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genre_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.genre_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.second_item);
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        pb = findViewById(R.id.genre_pb);
        rv = findViewById(R.id.genre_grid_rv);
        bottomNavigationView.setBackgroundColor(Color.parseColor("#D29366"));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.first_item:
                        intent = new Intent(GenreListActivity.this, UserMainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.second_item:
                        intent = new Intent(GenreListActivity.this, GenreListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.third_item:
                        intent = new Intent(GenreListActivity.this, AuthorListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.fourth_item:
                        intent = new Intent(GenreListActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        Call<ArrayList<Genre>> getAuthorsBook = apiInterface.getGenresList();
        getAuthorsBook.enqueue(new Callback<ArrayList<Genre>>() {
            @Override
            public void onResponse(Call<ArrayList<Genre>> call, Response<ArrayList<Genre>> response) {
                if (response.isSuccessful()){
                    genres = response.body();
                    rv.setLayoutManager(new GridLayoutManager(cont, 2));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(new GridGenreRecyclerAdapter(genres, cont));
                    pb.setVisibility(ProgressBar.INVISIBLE);
                }
                else {
                    Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Genre>> call, Throwable t) {

            }
        });
    }
}