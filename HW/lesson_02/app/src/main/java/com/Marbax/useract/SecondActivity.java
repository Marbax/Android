package com.Marbax.useract;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView = (TextView) findViewById(R.id.TVResult);

        User user = getIntent().getParcelableExtra(MainActivity.INTENT_USER);

        mTextView.setText("User is " + user.Name + " " + user.LastName);
    }
}