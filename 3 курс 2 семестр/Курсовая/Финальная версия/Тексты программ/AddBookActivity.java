package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.AuthorBooks;
import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.Genre;
import com.example.librarystar.Models.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {
    EditText name, description, year;
    Spinner publisherSpinner, genreSpinner, authorSpinner;
    Button add_auth, add_pdf, add_img, save;
    TextView auth_tv;
    ApiInterface apiInterface;
    Context cont;
    ArrayList<Genre> genres;
    ArrayList<User> publishers;
    ArrayList<Author> authors;
    ArrayList<Author> addedAuthors;
    String base64_pdf;
    String base64_img;
    private static final int PICK_PDF_REQUEST = 1;
    private static final int PICK_IMAGE_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cont = this;
        base64_pdf = "";
        base64_img = "";
        name = findViewById(R.id.add_book_name);
        description = findViewById(R.id.add_book_description);
        year = findViewById(R.id.add_book_year);
        publisherSpinner = (Spinner) findViewById(R.id.add_book_publisher_spinner);
        genreSpinner = (Spinner) findViewById(R.id.add_book_genre_spinner);
        authorSpinner = (Spinner) findViewById(R.id.add_book_author_spinner);
        add_auth = findViewById(R.id.add_book_add_author);
        add_pdf = findViewById(R.id.add_book_add_pdf);
        add_img = findViewById(R.id.add_book_add_image);
        save = findViewById(R.id.add_book_save);
        auth_tv = findViewById(R.id.addedAuthorsTB);
        addedAuthors = new ArrayList<>();
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        Call<ArrayList<User>> getPublishers = apiInterface.getUsersByRole(3);
        getPublishers.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()){
                    publishers = response.body();
                    ArrayList<String> publisher_names = new ArrayList<>();
                    for (User publisher: publishers) {
                        publisher_names.add(publisher.getPublisherName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(cont, android.R.layout.simple_spinner_item, publisher_names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    publisherSpinner.setAdapter(adapter);
                }
                else {
                    Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(cont, "Ошибка запросов.", Toast.LENGTH_SHORT).show();
            }
        });
        Call<ArrayList<Genre>> getGenres = apiInterface.getGenresList();
        getGenres.enqueue(new Callback<ArrayList<Genre>>() {
            @Override
            public void onResponse(Call<ArrayList<Genre>> call, Response<ArrayList<Genre>> response) {
                if (response.isSuccessful()){
                    genres = response.body();
                    ArrayList<String> genre_names = new ArrayList<>();
                    for (Genre genre: genres) {
                        genre_names.add(genre.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(cont, android.R.layout.simple_spinner_item, genre_names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    genreSpinner.setAdapter(adapter);
                }
                else {
                    Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Genre>> call, Throwable t) {
                Toast.makeText(cont, "Ошибка запросов.", Toast.LENGTH_SHORT).show();
            }
        });
        Call<ArrayList<Author>> getAuthors = apiInterface.getAuthorsList();
        getAuthors.enqueue(new Callback<ArrayList<Author>>() {
            @Override
            public void onResponse(Call<ArrayList<Author>> call, Response<ArrayList<Author>> response) {
                if (response.isSuccessful()){
                    authors = response.body();
                    ArrayList<String> author_names = new ArrayList<>();
                    for (Author author: authors) {
                        author_names.add(author.getFull_name());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(cont, android.R.layout.simple_spinner_item, author_names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    authorSpinner.setAdapter(adapter);
                }
                else {
                    Toast.makeText(cont, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Author>> call, Throwable t) {
                Toast.makeText(cont, "Ошибка запросов.", Toast.LENGTH_SHORT).show();
            }
        });
        add_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = authors.get((int) authorSpinner.getSelectedItemId());
                for (Author auth: addedAuthors) {
                    if (auth.getId() == author.getId()){
                        Toast.makeText(cont, "Выбранный автор уже добавлен!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                addedAuthors.add(author);
                auth_tv.setText("Добавлено авторов: " + addedAuthors.size());
            }
        });
        add_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_PDF_REQUEST);
            }
        });
        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                String nameValue = name.getText().toString();
                String descriptionValue = description.getText().toString();
                String yearValue = year.getText().toString();
                String publisherName = publisherSpinner.getSelectedItem().toString();
                String genreName = genreSpinner.getSelectedItem().toString();
                User publisher = null;
                Genre genre = null;
                for (User publ: publishers) {
                    if (publ.getPublisherName().equals(publisherName)){
                        publisher = publ;
                    }
                }
                for (Genre genr: genres){
                    if (genr.getName().equals(genreName)){
                        genre = genr;
                    }
                }
                if (nameValue.isEmpty() || descriptionValue.isEmpty() || yearValue.isEmpty()) {
                    Toast.makeText(cont, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else if (base64_pdf.equals("") || base64_img.equals("")){
                    Toast.makeText(cont, "Пожалуйста, добавьте pdf и картинку книги.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Book book = new Book(nameValue, Integer.parseInt(yearValue), base64_img, base64_pdf, publisher.getId(), genre.getId(), false, null, true, descriptionValue);
                    Call<Book> addBookCall = apiInterface.addBook(book);
                    addBookCall.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            if (response.isSuccessful()){
                                Book book = response.body();
                                Toast.makeText(cont, "Книга успешно добавлена!", Toast.LENGTH_SHORT).show();
                                AddAuthors(book.getId());
                                description.setText("");
                                name.setText("");
                                year.setText("");
                                base64_img = "";
                                base64_pdf = "";
                                addedAuthors.clear();
                            }
                            else {
                                Toast.makeText(cont, "Ошибка сервера или данных.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(cont, "Ошибка запросов.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                }
                catch (Exception e){
                    Toast.makeText(cont, "Ошибка добавления книги! Проверьте вводимых формат данных", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedPdfUri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedPdfUri);
                byte[] pdfBytes = new byte[inputStream.available()];
                inputStream.read(pdfBytes);
                inputStream.close();

                base64_pdf = Base64.encodeToString(pdfBytes, Base64.DEFAULT);
                Toast.makeText(cont, "Pdf успешно добавлен", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                byte[] imageBytes = new byte[inputStream.available()];
                inputStream.read(imageBytes);
                inputStream.close();
                base64_img = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Toast.makeText(cont, "Изображение успешно добавлено", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void AddAuthors(int book_id){
        for (Author auth: addedAuthors){
            Call<AuthorBooks> addAB = apiInterface.addAuthorBook(new AuthorBooks(auth.getId(), book_id));
            addAB.enqueue(new Callback<AuthorBooks>() {
                @Override
                public void onResponse(Call<AuthorBooks> call, Response<AuthorBooks> response) {
                    if (response.isSuccessful()){
                    }
                    else {
                        Toast.makeText(cont, "Ошибка сервера при добавлении авторов.", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<AuthorBooks> call, Throwable t) {
                    Toast.makeText(cont, "Ошибка запросов при добавлении авторов.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Intent intent = new Intent(AddBookActivity.this, UserMainActivity.class);
        startActivity(intent);
    }
}