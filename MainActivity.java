package com.example.tictac;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState= {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPosition = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7}, {2,5,8},{0,4,8}, {2,4,6}};


    @SuppressLint("SetTextI18n")
    public void PlayerTab(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2 && gameActive){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                img.setImageResource(R.drawable.icon);
                activePlayer = 1;
                TextView Status = findViewById(R.id.Status);
                Status.setText("O's turn - tap to play");
            }
            else{
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
                TextView Status = findViewById(R.id.Status);
                Status.setText("X's Turn - Tap to Play");

            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition: winPosition){
            if(gameState[winPosition[0]]== gameState[winPosition[1]] &&
                    gameState[winPosition[1]]== gameState[winPosition[2]]&&
                    gameState[winPosition[0]]!= 2){
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]]==0){
                    winnerStr = "X has Won......";
                }
                else{
                    winnerStr = "O has Won......";
                }
                TextView Status = findViewById(R.id.Status);
                Status.setText(winnerStr);
            }
        }
    }
    @SuppressLint("SetTextI18n")
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView Status = findViewById(R.id.Status);
        Status.setText("X's Turn - Tap to Play");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the restart button by its ID
        Button restartButton = findViewById(R.id.button);

        // Set an OnClickListener for the restart button
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameReset(v);
            }
        });
    }

}
