package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.example.librarystar.Models.BookWithAuthors;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView rv;
    Button but;
    ArrayList<Book> books;
    ArrayList<Author> authors;
    int i;
    ProgressBar pb;
    Context cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        rv = findViewById(R.id.book_horizontal_rv);
        but = findViewById(R.id.book_list_get_back);
        pb = findViewById(R.id.book_list_pb);
        books = new ArrayList<>();
        cont = this;
        authors = new ArrayList<>();
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookListActivity.this, UserMainActivity.class);
                startActivity(intent);
            }
        });
        Call<ArrayList<Book>> getBooks = apiInterface.getBooks();
        getBooks.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()){
                    books = response.body();
                    set_rv();
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
    }
    void set_rv(){
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
                            rv.setLayoutManager(new LinearLayoutManager(cont, LinearLayoutManager.VERTICAL, false));
                            rv.setHasFixedSize(true);
                            rv.setAdapter(new HoryzontalBookRecyclerAdapter(balist, cont));
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
                    Toast.makeText(cont, "Ошибка запроса.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}