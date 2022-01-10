package com.gameso.magicwins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);


        Button buttonExitEndGame = findViewById(R.id.buttonExitEndGame);
        Button buttonTryAgain = findViewById(R.id.buttonTryAgain);
        buttonExitEndGame.setOnClickListener(onClickExitEndGame);
        buttonTryAgain.setOnClickListener(onClickTryAgain);

    }

    View.OnClickListener onClickExitEndGame = view -> finishAffinity();
    View.OnClickListener onClickTryAgain = view -> {
        Intent intentStart = new Intent(GameEndActivity.this, GameActivity.class);
        startActivity(intentStart);
        finish();
    };
}