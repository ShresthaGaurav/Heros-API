package com.shresthagaurav.heros_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity {
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        name =findViewById(R.id.name);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            name.setText(bundle.getString("user"));
        }
    }
}
