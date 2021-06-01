package com.neeraj.layoutsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLinear(View view) {
        Intent intent = new Intent(this, LinearActivity.class);
        startActivity(intent);
    }

    public void goToRelative(View view) {
        Intent intent = new Intent(this, RelativeActivity.class);
        startActivity(intent);
    }

    public void goToGrid(View view) {
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }

}