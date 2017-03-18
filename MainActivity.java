package com.example.muhammadmurtaza.snakey;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPlay(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}