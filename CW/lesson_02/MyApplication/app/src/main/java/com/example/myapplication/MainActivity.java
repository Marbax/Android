package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTENT_ARG_1 = "intentArg1";
    public static final String INTENT_ARG_2 = "intentArg2";
    public static final int REQUEST_CODE = 1;
    private static final int REQUEST_PHONE_CALL = 99;
    private static final int REQUEST_CAMERA = 777;
    private final Intent callIntent = new Intent(Intent.ACTION_CALL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSecondACtivity).setOnClickListener(this::onClick);
        findViewById(R.id.btnCall).setOnClickListener(this::onClick);
        findViewById(R.id.btnBrowse).setOnClickListener(this::onClick);
        findViewById(R.id.btnCamera).setOnClickListener(this::onClick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            int res = data.getIntExtra(SecondActivity.INTENT_RESULT, -1);
            Log.d("Main activity", "onActivityResult: " + res);
            Toast.makeText(this, res + "", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            Bitmap img = (Bitmap) data.getExtras().get("data");
            ImageView imgView = findViewById(R.id.imCam);
            imgView.setImageBitmap(img);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSecondACtivity: {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(INTENT_ARG_1, 10);
                intent.putExtra(INTENT_ARG_2, 25);
                startActivityForResult(intent, REQUEST_CODE);
            }
            break;
            case R.id.btnCall: {
                //call
                callIntent.setData(Uri.parse("tel:0631234567"));
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    startActivity(callIntent);
                }

                //Book
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("content://contacts/people"));
                //startActivity(intent);
            }
            break;
            case R.id.btnBrowse: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://ukr.net"));
                startActivity(intent);

            }
            break;
            case R.id.btnCamera: {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
            break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            }
        }
    }
}