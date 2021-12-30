package com.example.tictoctoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count=0, Xwin=0 ,Owin=0;
    boolean gameActive=true;
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};




    public void playerTap(View view){
        Button play=findViewById(R.id.play);
        Button restart=findViewById(R.id.restart);
        ImageView img=(ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        TextView x=findViewById(R.id.xscore);
        TextView o=findViewById(R.id.oscore);
        x.setText(Integer.toString(Xwin));
        o.setText(Integer.toString(Owin));


        if(gameState[tappedImage]==2 && gameActive) {
            count++;
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            TextView status = findViewById(R.id.status);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                status.setText("O Turn - Tap to Play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                status.setText("X Turn - Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);


            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                    String Winner;
                    if (gameState[winPosition[0]] == 0) {
                        ++Xwin;
                        Winner = "X has Won";
                        x.setText(Integer.toString(Xwin));
                    } else {
                        ++Owin;
                        Winner = "O has Won";
                        o.setText(Integer.toString(Owin));
                    }
                    gameActive = false;
                    status.setText(Winner);
                    play.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                }
            }

            if (count == 9 && gameActive) {
                status.setText("Game is Draw");
                gameActive = false;
                play.setVisibility(View.VISIBLE);
                restart.setVisibility(View.VISIBLE);

            }
        }

    }

    public void ResetScore(View view)
    {
        Xwin=0;
        Owin=0;
        TextView x=findViewById(R.id.xscore);
        TextView o=findViewById(R.id.oscore);
        x.setText(Integer.toString(Xwin));
        o.setText(Integer.toString(Owin));
        gameReset(view);
    }


    public void gameReset(View view){
        Button play=findViewById(R.id.play);
        Button restart=findViewById(R.id.restart);
        gameActive=true;
        count=0;
        for(int i = 0 ; i < gameState.length ;i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        play.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.INVISIBLE);

        TextView status=findViewById(R.id.status);

        if (activePlayer == 0) {
            activePlayer = 1;
            status.setText("O Turn - Tap to Play");
        }else {
            activePlayer = 0;
            status.setText("X Turn - Tap to Play");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}