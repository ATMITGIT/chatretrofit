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
import com.example.diplomprogect.storage.SharedPrefManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nikname_editText_register, secret_editText_register, login_editText_register, pass_editText_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nikname_editText_register = findViewById(R.id.nikname_editText_register);
        secret_editText_register = findViewById(R.id.secret_editText_register);
        login_editText_register = findViewById(R.id.login_editText_register);
        pass_editText_register = findViewById(R.id.pass_editText_register);

        findViewById(R.id.reg_button_register).setOnClickListener(this);
        findViewById(R.id.login_textView_register).setOnClickListener(this);
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


    private void UserSignUp(){
        String nickname = nikname_editText_register.getText().toString().trim();
        String secret = secret_editText_register.getText().toString().trim();
        String login = login_editText_register.getText().toString().trim();
        String pass = pass_editText_register.getText().toString().trim();


        if (nickname.isEmpty()){
            nikname_editText_register.setError("Не правильно введено имя");
            nikname_editText_register.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(nickname).matches()){
            login_editText_register.setError("Напишите майл правильно");
            login_editText_register.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            secret_editText_register.setError("Не корректный секрет");
            secret_editText_register.requestFocus();
            return;
        }
        if(pass.length() <3 ){
            pass_editText_register.setError("Пароль короткий");
            pass_editText_register.requestFocus();
            return;
        }
        if (login.isEmpty()){
            login_editText_register.setError("Не правильно");
            login_editText_register.requestFocus();
            return;
        }


        if(secret.isEmpty()){
            pass_editText_register.setError("Пароль не верный");
            pass_editText_register.requestFocus();
            return;
        }




        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .reg(nickname, secret, login, pass);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String s = response.body().string();
                        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(  MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
                
            });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_button_register:
                UserSignUp();
                break;
            case R.id.login_textView_register:

                startActivity(new Intent(this, LoginActivity.class));

                break;
        }
    }
}
