package com.shresthagaurav.heros_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shresthagaurav.heros_api.apiclass.ApiCall;
import com.shresthagaurav.heros_api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Log_IN extends AppCompatActivity {
EditText username,password;
Button btnlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        username =findViewById(R.id.snname);
        password =findViewById(R.id.snpass);
        btnlog =findViewById(R.id.snbtnlogin);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( CheckEmpty(username,password)){
                   final User user= new User(username.getText().toString(),password.getText().toString());
                   ApiCall apiCall = new ApiCall();
                   final Call<Void> LoginCall=apiCall.calls().login(user);
                 LoginCall.enqueue(new Callback<Void>() {
                     @Override
                     public void onResponse(Call<Void> call, Response<Void> response) {
                         if (!response.isSuccessful()) {
                             Toast.makeText(Log_IN.this, "User not found" , Toast.LENGTH_SHORT).show();
                             Log.d("error", "error" + response.code());
                             return;
                         }
                         Toast.makeText(Log_IN.this, "Welcome", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(Log_IN.this,DashBoard.class);
                         intent.putExtra("user",user.getUsername());
                         startActivity(intent);
                     }

                     @Override
                     public void onFailure(Call<Void> call, Throwable t) {
                         Toast.makeText(Log_IN.this, "error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                     }
                 });
               }
            }
        });
    }

    public boolean CheckEmpty( EditText et_salary, EditText et_age) {


            if (!TextUtils.isEmpty(et_salary.getText().toString())) {
                if (!TextUtils.isEmpty(et_age.getText().toString())) {
                    return true;

                } else {
                    et_age.setError("enter the name");
                }
            } else {
                et_salary.setError("enter the pass");
            }
        return false;
        }

    }

