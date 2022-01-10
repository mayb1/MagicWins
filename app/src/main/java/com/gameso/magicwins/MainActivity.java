package com.gameso.magicwins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartGame = findViewById(R.id.buttonStartGame);
        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(onClickExit);
        buttonStartGame.setOnClickListener(onClickStart);
    }

    View.OnClickListener onClickExit = view -> finishAffinity();
    View.OnClickListener onClickStart = view -> {
        Intent intentStart = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intentStart);
        finish();
    };
}