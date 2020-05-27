package com.example.tiktaktok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button playAgainButton;
    TextView resultTextView;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        resultTextView = (TextView) findViewById(R.id.winnerTextView);
        gridLayout = findViewById(R.id.gridLayout);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = 0;
                gameActive = true;
                resultTextView.setVisibility(View.INVISIBLE);
                playAgainButton.setVisibility(View.INVISIBLE);
                for(int i=0; i<gridLayout.getChildCount(); i++) {
                    ImageView image = (ImageView) gridLayout.getChildAt(i);
                    image.setImageDrawable(null);
//                  use this loop to reset gameState Array also
                    gameStates[i] =2;
                }
            }
        });
    }

    //    player = 0 (player red)  player = 1 (player yellow)  2 means empty image view
    int player = 0;
    int[] gameStates = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winingPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView imageView = (ImageView) view;
        int tag = Integer.parseInt(view.getTag().toString());
        Toast.makeText(this, gameStates[tag]+"", Toast.LENGTH_SHORT).show();
        if (gameStates[tag] == 2 && gameActive) {
            imageView.setTranslationY(-1500);
            gameStates[tag] = player;
            if (player == 0) {
                imageView.setImageResource(R.drawable.red);
                player = 1;
            } else {
                imageView.setImageResource(R.drawable.yellow);
                player = 0;
            }

            for (int[] positions : winingPosition) {
                if (gameStates[positions[0]] == gameStates[positions[1]] && gameStates[positions[1]] == gameStates[positions[2]] && gameStates[positions[0]] != 2) {
                    if (gameStates[positions[0]] == 0) {
//                    owning player 0 (red)
                        Toast.makeText(this, "player red !", Toast.LENGTH_SHORT).show();
                        resultTextView.setText("player red");
                        resultTextView.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                        gameActive = false;
                    } else {
//                      owning player 1 (yellow)
                        resultTextView.setText("player yellow");
                        resultTextView.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                        gameActive = false;
                    }
                }
            }

            imageView.animate().translationYBy(1500).rotation(5000).setDuration(200);
        }
    }

}
