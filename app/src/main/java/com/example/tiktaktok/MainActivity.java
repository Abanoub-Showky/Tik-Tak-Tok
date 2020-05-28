package com.example.tiktaktok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
    }

    //    player = 0 (player red)  player = 1 (player yellow)  2 means empty image view
    static int player = 0;
    int[] gameStates = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winingPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;
    int counter=0;

    public void dropIn(View view) {
        ImageView imageView = (ImageView) view;
        int tag = Integer.parseInt(view.getTag().toString());

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
                        gameActive = false;
                        showAlertDialog("red",R.drawable.red);
                    } else {
//                      owning player 1 (yellow)
                        gameActive = false;
                        showAlertDialog("yellow",R.drawable.yellow);
                    }
                }
            }
            for (int i=0;i<gameStates.length;i++){
                if(gameStates[i]!=2){
                    counter++;
                }
            }
            if(counter==gameStates.length){
                gameActive=false;
                showAlertDialog("draw",R.drawable.image_draw);
            }else {
                counter=0;
            }
            imageView.animate().translationYBy(1500).rotation(5000).setDuration(200);
        }
    }


    public void showAlertDialog(String player, int imageDialog) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.cutom_dialog);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        if(!player.equals("draw")){
            text.setText(player+" win !");
        }else {
            text.setText("it's a draw");
        }
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(imageDialog);
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.player = 0;
                gameActive = true;
                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    ImageView image = (ImageView) gridLayout.getChildAt(i);
                    image.setImageDrawable(null);
                    gameStates[i] = 2;
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
