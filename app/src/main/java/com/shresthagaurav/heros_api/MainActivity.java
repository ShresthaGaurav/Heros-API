package com.shresthagaurav.heros_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shresthagaurav.heros_api.apiclass.ApiCall;
import com.shresthagaurav.heros_api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText ename, epass, erepass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ename = findViewById(R.id.edname);
        epass = findViewById(R.id.pass);
        btn = findViewById(R.id.btnSignup);
        erepass = findViewById(R.id.repass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckEmpty(ename, epass, erepass)) {
                    String name = ename.getText().toString();
                    String pass = epass.getText().toString();
                    String repass = erepass.getText().toString();
                    if (pass.equals(repass)) {
                        User user = new User(name, pass);
                        ApiCall apiCall = new ApiCall();
                        final Call<Void>voidCall=apiCall.calls().register(user);
                        voidCall.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(MainActivity.this, "user Created", Toast.LENGTH_SHORT).show();
                                ename.setText("");
                                epass.setText("");
                                erepass.setText("");
                                Intent intent = new Intent(MainActivity.this,Log_IN.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "password and repasswor doesn't match", Toast.LENGTH_SHORT).show();
                  return;
                   }
                }
            }
        });
    }

    public boolean CheckEmpty(EditText et_name, EditText et_salary, EditText et_age) {

        if (!TextUtils.isEmpty(et_name.getText().toString())) {
            if (!TextUtils.isEmpty(et_salary.getText().toString())) {
                if (!TextUtils.isEmpty(et_age.getText().toString())) {
                    return true;

                } else {
                    et_age.setError("enter the name");
                }
            } else {
                et_salary.setError("enter the pass");
            }

        } else {
            et_name.setError("enter the repass");
        }
        return false;
    }
}
