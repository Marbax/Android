package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        EditText edtiText = findViewById(R.id.ETName);
        Button btn = findViewById(R.id.confirm);

        btn.setOnClickListener(view ->{
            Toast.makeText(getApplicationContext(),"Hello, "+ edtiText.getText(),Toast.LENGTH_SHORT).show();
           textView.setText("Hello, "+ edtiText.getText());
        });
    }
}