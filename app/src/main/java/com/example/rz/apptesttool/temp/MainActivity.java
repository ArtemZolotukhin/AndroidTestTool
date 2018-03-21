package com.example.rz.apptesttool.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rz.apptesttool.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_activity_2).setOnClickListener((v) -> {
            startActivity(new Intent(this, Main2Activity.class));
        });

        findViewById(R.id.btn_activity_3).setOnClickListener((v) -> {
            startActivity(new Intent(this, Main3Activity.class));
        });
    }
}
