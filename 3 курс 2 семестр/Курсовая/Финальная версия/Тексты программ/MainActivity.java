package com.example.librarystar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarystar.Models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    Button reg, aut;
    EditText log, pasw;
    TextView restore_pasw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        reg = findViewById(R.id.registration_button);
        aut = findViewById(R.id.auth_button);
        log = findViewById(R.id.login_field);
        pasw = findViewById(R.id.password_field);
        restore_pasw = findViewById(R.id.forgot_password_text_button);
        //restore_pasw.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent intent = new Intent(MainActivity.this, RestoreActivity.class);
        //        startActivity(intent);
        //    }
        //});
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        aut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(log.getText().toString(), HashText.hashText(pasw.getText().toString()), "2006-04-27", null, null, null, 1, null, null);
                Call<User> getUser = apiInterface.getUserByLogs(user);
                getUser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, UserMainActivity.class);
                            User user = response.body();
                            StaticData.user_id = user.getId();
                            switch (user.getRole()){
                                case 1:
                                    StaticData.user_role = "Пользователь";
                                    break;
                                case 2:
                                    StaticData.user_role = "Администратор";
                                    break;
                                case 3:
                                    StaticData.user_role = "Издатель";
                                    break;
                            }
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Неверный логин или пароль.", Toast.LENGTH_SHORT).show();
                            log.setText("");
                            pasw.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }
}