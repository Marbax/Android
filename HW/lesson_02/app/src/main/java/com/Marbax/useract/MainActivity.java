package com.Marbax.useract;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String INTENT_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSend).setOnClickListener(view -> {
            String name = ((EditText) findViewById(R.id.PTName)).getText().toString();
            String lastName = ((EditText) findViewById(R.id.PTLastName)).getText().toString();
            if (!name.isEmpty() && name != null) {
                if (!lastName.isEmpty() && lastName != null) {
                    User user = new User(name, lastName);

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(INTENT_USER,user);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Last Name can't be an empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Name can't be an empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}