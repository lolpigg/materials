package com.example.librarystar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.librarystar.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    ImageView img, notification;
    TextView tv, rd;
    Button exit, change, admin;
    ApiInterface apiInterface;
    Intent thisintent;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        thisintent = getIntent();
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.fourth_item);
        bottomNavigationView.setBackgroundColor(Color.parseColor("#D29366"));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.first_item:
                        intent = new Intent(ProfileActivity.this, UserMainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.second_item:
                        intent = new Intent(ProfileActivity.this, GenreListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.third_item:
                        intent = new Intent(ProfileActivity.this, AuthorListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.fourth_item:
                        intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        tv = findViewById(R.id.profileText);
        rd = findViewById(R.id.registrationDateText);
        img = findViewById(R.id.profileImg);
        exit = findViewById(R.id.exitFromAccountButton);
        change = findViewById(R.id.changePasswordButton);
        admin = findViewById(R.id.adminMenuButton);
        notification = findViewById(R.id.profile_notification);
        if (!Objects.equals(StaticData.user_role, "Администратор")){
            admin.setVisibility(View.GONE);
        }
        else{
            admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(ProfileActivity.this, AdminActivity.class);
                    startActivity(intent1);
                }
            });
        }
        if (!Objects.equals(StaticData.user_role, "Пользователь")){
            notification.setVisibility(View.GONE);
        }
        else {
            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(ProfileActivity.this, NotificationListActivity.class);
                    startActivity(intent1);
                }
            });
        }
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        Call<User> getUser = apiInterface.getUser(StaticData.user_id);
        Context cont = this;
        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();
                    tv.setText(user.getLogin());
                    Picasso.with(cont).load("https://lolpigg.pythonanywhere.com" + user.getAvatarPath()).into(img);
                    rd.setText("Дата регистрации: " + user.getRegistrationDate());
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(cont, "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        change.setVisibility(View.GONE);
    }
}