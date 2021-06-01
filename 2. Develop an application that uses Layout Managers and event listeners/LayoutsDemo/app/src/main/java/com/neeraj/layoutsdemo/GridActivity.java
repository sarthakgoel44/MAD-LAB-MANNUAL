package com.neeraj.layoutsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GridActivity extends AppCompatActivity {
    Boolean toastflag = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("GridLayout Demo");
        setContentView(R.layout.activity_grid);
    }

    public void toastNotification(View view){
        if(toastflag == Boolean.FALSE){
            Toast.makeText(this, "Toast notifications ON", Toast.LENGTH_SHORT).show();
            toastflag = Boolean.TRUE;
        }
        else{
            Toast.makeText(this, "Toast notifications OFF", Toast.LENGTH_SHORT).show();
            toastflag = Boolean.FALSE;
        }
    }

    public void addToWishist(View view){
        if(toastflag == Boolean.TRUE){
            Toast.makeText(this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
        }
    }

    public void orderItem(View view){
        if(toastflag == Boolean.TRUE){
            Toast.makeText(this, "Your order has been placed :)", Toast.LENGTH_SHORT).show();
        }
    }
}