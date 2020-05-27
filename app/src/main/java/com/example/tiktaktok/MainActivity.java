package com.example.tiktaktok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    player = 0 (player red)  player = 1 (player yellow)  2 means empty image view
    int player = 0;
    int[] gameStates = {2,2,2,2,2,2,2,2,2};
    int[][] winingPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){
        ImageView imageView = (ImageView) view;
        imageView.setTranslationY(-1500);
        int tag = Integer.parseInt(view.getTag().toString());
        gameStates[tag] = player;
        if(player==0){
            imageView.setImageResource(R.drawable.red);
            player=1;
        }else {
            imageView.setImageResource(R.drawable.yellow);
            player=0;
        }

        for(int[] positions : winingPosition ){
            if(gameStates[positions[0]]==gameStates[positions[1]]&&gameStates[positions[1]]==gameStates[positions[2]]&&gameStates[positions[0]]!=2){
                if(gameStates[positions[0]]==0){
//                    owning player 0 (red)
                    Toast.makeText(this, "player red !", Toast.LENGTH_SHORT).show();
                }else {
//                    owning player 1 (yellow)
                    Toast.makeText(this, "player yellow !", Toast.LENGTH_SHORT).show();
                }
            }
        }

        imageView.animate().translationYBy(1500).rotation(5000).setDuration(200);
    }

}
