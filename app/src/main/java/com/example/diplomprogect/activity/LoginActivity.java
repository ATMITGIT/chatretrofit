package com.example.diplomprogect.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diplomprogect.R;
import com.example.diplomprogect.api.RetrofitClient;

import com.example.diplomprogect.models.Data;
import com.example.diplomprogect.models.ResponseData;
import com.example.diplomprogect.models.UsersResponse;
import com.example.diplomprogect.storage.SharedPrefManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email_editText_login;
    private EditText pass_editText_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_editText_login = findViewById(R.id.email_editText_login);
        pass_editText_login = findViewById(R.id.pass_editText_login);

        findViewById(R.id.login_button_login).setOnClickListener(this);
        findViewById(R.id.login_textView_login).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, ChatListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin(){

        String login = email_editText_login.getText().toString().trim();
        String pass = pass_editText_login.getText().toString().trim();

        if (login.isEmpty()){
            email_editText_login.setError("Не правильно введено имя");
            email_editText_login.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()){
            email_editText_login.setError("Напишите майл правильно");
            email_editText_login.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            pass_editText_login.setError("Пароль не верный");
            pass_editText_login.requestFocus();
            return;
        }

        if(pass.length() <3 ) {
            pass_editText_login.setError("Пароль короткий");
            pass_editText_login.requestFocus();
            return;
        }

        Call<Data> call = RetrofitClient
                .getInstance().getApi().userLogin(login,pass);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                if(!data.isStatus()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Пора покормить кота!", Toast.LENGTH_SHORT);
                    toast.show();}
                else {
                   SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(data.getResponse().getData());

                 SharedPrefManager.getInstance(LoginActivity.this)
                            .saveToken(data.getResponse().getToken());
                    Toast.makeText(getApplicationContext(),String.valueOf(data.getResponse().getData().getLogin()),Toast.LENGTH_SHORT).show();

                 Intent intent = new Intent(LoginActivity.this, ChatListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button_login:
                userLogin();
                break;
            case R.id.login_textView_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

}
