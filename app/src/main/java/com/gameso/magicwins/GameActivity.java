package com.gameso.magicwins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    private ImageView imageViewIcon;
    private TextView textViewPoints;
    private boolean elementMoving = true;
    private int points;
    private Point size;
    private int screenHeight;
    private int speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button buttonExitGame = findViewById(R.id.buttonExitGame);
        buttonExitGame.setOnClickListener(onClickExitGame);

        imageViewIcon = findViewById(R.id.imageViewIcon);
        textViewPoints = findViewById(R.id.textViewPoints);
        Display display = getWindowManager().getDefaultDisplay();
        size = new Point();
        elementMoving = true;
        display.getSize(size);
        screenHeight = size.y;

        imageViewIcon.setOnClickListener(view -> {
            addPoint();
            createNewElement();
        });
        startMoving();
    }

    View.OnClickListener onClickExitGame = view -> finishAffinity();

    private int getRandomBonusIcon(){
        int resourceId;
        switch((int) (Math.random()*3)){
            case 0:
                resourceId = R.drawable.bonusicon_1;
                break;
            case 1:
                resourceId = R.drawable.bonusicon_2;
                break;
            default:
                resourceId = R.drawable.bonusicon_3;
        }
        return resourceId;
    }

    private void addPoint(){
        points++;
        textViewPoints.setText(String.format(Locale.getDefault(), "Points : %d", points));
    }

    private void createNewElement(){
        int newX = (int) (Math.random() * (size.x - imageViewIcon.getWidth()));
        imageViewIcon.setImageResource(getRandomBonusIcon());

        imageViewIcon.setX(newX);
        imageViewIcon.setY(0);
        speed = (int) (Math.random()*10);
    }


    private void startMoving() {
        Thread thread = new Thread( () -> {
            try {
                while (elementMoving) {
                    if(imageViewIcon.getY() >= size.y - (int) (size.y/20)){
                        elementMoving = false;
                        Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    runOnUiThread(() -> imageViewIcon.setY(imageViewIcon.getY() + (int) (screenHeight / 150) + speed));
                    Thread.sleep(20);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}