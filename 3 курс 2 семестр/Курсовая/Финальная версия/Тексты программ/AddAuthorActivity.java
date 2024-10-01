package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.Genre;
import com.example.librarystar.Models.User;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAuthorActivity extends AppCompatActivity {
    EditText name, born, dead;
    Button add_img, save;
    String base64_img;
    Context cont;
    ApiInterface apiInterface;
    private static final int PICK_IMAGE_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_author);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        cont = this;
        name = findViewById(R.id.add_author_name);
        born = findViewById(R.id.add_author_born);
        dead = findViewById(R.id.add_author_dead);
        add_img = findViewById(R.id.add_author_add_img);
        save = findViewById(R.id.add_author_save);
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
                SaveAuthor();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
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
    private void SaveAuthor(){
        try {
            String nameValue = name.getText().toString();
            String bornValue = born.getText().toString();
            String deadValue = dead.getText().toString();
            if (nameValue.isEmpty() || bornValue.isEmpty()) {
                Toast.makeText(cont, "Пожалуйста, заполните поля имени и рождения", Toast.LENGTH_SHORT).show();
            } else if (base64_img.equals("")){
                Toast.makeText(cont, "Пожалуйста, добавьте изображение автора.", Toast.LENGTH_SHORT).show();
            }
            else {
                Author author = new Author(nameValue, Integer.parseInt(bornValue), Integer.parseInt(deadValue), base64_img);
                Call<Author> addAuthor = apiInterface.addAuthor(author);
                addAuthor.enqueue(new Callback<Author>() {
                    @Override
                    public void onResponse(Call<Author> call, Response<Author> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(cont, "Автор успешно добавлен!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddAuthorActivity.this, UserMainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(cont, "Ошибка сервера или данных.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Author> call, Throwable t) {
                        Toast.makeText(cont, "Ошибка запросов.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        catch (Exception e){
            Toast.makeText(cont, "Ошибка добавления автора! Проверьте вводимых формат данных", Toast.LENGTH_SHORT).show();
        }
    }
}