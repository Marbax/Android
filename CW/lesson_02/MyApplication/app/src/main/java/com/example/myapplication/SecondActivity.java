package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String INTENT_RESULT = "result";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int val1 =  getIntent().getIntExtra(MainActivity.INTENT_ARG_1,-1);
        int val2 = getIntent().getIntExtra(MainActivity.INTENT_ARG_2,-1);
        int res = val1 + val2;

        Toast.makeText(this, res+"", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent().putExtra(INTENT_RESULT,res );
        setResult(Activity.RESULT_OK,intent);
    }
}
