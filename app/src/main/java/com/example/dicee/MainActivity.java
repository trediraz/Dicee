package com.example.dicee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    final int[] diceImages = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainActiityScreen();
    }

    private void setMainActiityScreen() {
        setContentView(R.layout.activity_main);
        setInMainScreenButtons();
    }

    private void setInMainScreenButtons() {
        final Button rollOneDiceButton = findViewById(R.id.roll_one_button), rollTwoDiceButton = findViewById(R.id.roll_two_button);
        rollOneDiceButton.setOnClickListener(changeScreenToRollOneListener);
        rollTwoDiceButton.setOnClickListener(changeScreenToRollTwoListener);
    }

    private View.OnClickListener changeScreenToRollOneListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            setContentView(R.layout.one_dice);
            ImageView[] oneDiceViewImages = new ImageView[]{findViewById(R.id.only_dice)};
            setInSubScreenButtons(oneDiceViewImages);
        }
    };
    private View.OnClickListener changeScreenToRollTwoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            setContentView(R.layout.two_dice);
            ImageView[] twoDiceViewImages = new ImageView[]{findViewById(R.id.left_dice), findViewById(R.id.right_dice)};
            setInSubScreenButtons(twoDiceViewImages);
        }
    };

    private  void setInSubScreenButtons(final ImageView[] imageViews){
        final Button rollButton = findViewById(R.id.roll_button), returnButton = findViewById(R.id.returnButton);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll(imageViews);
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMainActiityScreen();
            }
        });
    }

    private void roll(final ImageView[] imageViews)
    {
        final Handler handler = new Handler();
        final int BASIC_DELAY = 70, ADDED_DELAY_MODIFIER = 6, NUMBER_OF_ROLLS_TO_DISPLAY = 15;
        for (int i = 0; i < NUMBER_OF_ROLLS_TO_DISPLAY; i++) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDiceImages(imageViews);
                }
            }, ((BASIC_DELAY + (ADDED_DELAY_MODIFIER * i)) * i));
        }
    }

    private void setDiceImages(ImageView[] diceViewImages){
        Random randomNumberGenerator = new Random();
        for (ImageView diceViewImage : diceViewImages)
            diceViewImage.setImageResource(diceImages[randomNumberGenerator.nextInt(6)]);
    }

}