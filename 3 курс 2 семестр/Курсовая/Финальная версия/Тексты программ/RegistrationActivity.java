package com.example.librarystar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.librarystar.Models.Role;
import com.example.librarystar.Models.User;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText log, p1, p2;
    Button auth, reg;
    ApiInterface apiInterface;
    Role role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        log = findViewById(R.id.reg_login_field);
        p1 = findViewById(R.id.reg_password_field);
        p2 = findViewById(R.id.reg_password_field_2);
        auth = findViewById(R.id.reg_auth_button);
        reg = findViewById(R.id.reg_registration_button);
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!p1.getText().toString().equals(p2.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Пароли не повторяются!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (log.getText().length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Логин должен быть длиной не менее 6 символов.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!StaticData.isPasswordValid(p1.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Пароль не соответствует требованиям.\n Требования для пароля:" +
                            "\n• Не короче 8 символов" +
                            "\n• Хотя бы один спецсимвол" +
                            "\n• Хотя бы одна цифра" +
                            "\n• Без пробелов" +
                            "\n• Только латинские буквы", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Call<Role> getUser = apiInterface.getRoleUser();
                getUser.enqueue(new Callback<Role>() {
                    @Override
                    public void onResponse(Call<Role> call, Response<Role> response) {
                        if (response.isSuccessful()) {
                            role = response.body();
                            addUser(role.getId());
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Ошибка сервера.", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<Role> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Ошибка получения роли", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void addUser(int role){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(calendar.getTime());
        User user = new User(log.getText().toString(), HashText.hashText(p1.getText().toString()),
                formattedDate, null, null, null, role, null, null);
        Call<User> addUser = apiInterface.addUser(user);
        addUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    StaticData.user_id = user.getId();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Ошибка данных или сервера", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
            }
        });
    }
}