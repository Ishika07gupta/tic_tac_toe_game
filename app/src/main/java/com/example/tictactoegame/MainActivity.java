package com.example.tictactoegame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    Button restart;
    TextView headerText;

    int player_o = 0;
    int player_x = 1;
    int activeplayer = player_o;
    int[] pos = {-1, -1, -1, -1, -1, -1, -1, -1,-1};
    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.header_text);
        headerText.setText("o turn");
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        if (!isGameActive)
            return;
        Button clickedbtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());
        if (pos[clickedTag] != -1) {
            return;
        }
            pos[clickedTag] = activeplayer;
            if (activeplayer == player_o) {
                clickedbtn.setText("o");
                clickedbtn.setBackground(getDrawable(android.R.color.holo_red_light));
                activeplayer = player_x;
                headerText.setText("x turn");
            } else {
                clickedbtn.setText("x");
                clickedbtn.setBackground(getDrawable(android.R.color.holo_blue_light));
                activeplayer = player_o;
                headerText.setText("o turn");
            }

            checkWin();
            checkDraw();

    }
    private boolean checkWin() {
        int[][] winningpos = {{0, 1, 2}, {3, 4, 5}, {2, 4, 6}, {0, 4, 8}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 0}};
        for (int i = 0; i < 8; i++) {
            int val0 = winningpos[i][0];
            int val1 = winningpos[i][1];
            int val2 = winningpos[i][2];
            if (pos[val0] == pos[val1] && pos[val1] == pos[val2] && pos[val0] != -1) {
                    isGameActive = false;
                    if (pos[val0] == player_o)
                        showMsg("O is winner");
                    else {
                        showMsg("X is winner");
                    }
                    return true;
                }

            }
        return false;
    }
    private void checkDraw()
    {
        int count=0;
        for(int i=0;i<9;i++)
        {
            if (pos[i] != -1) {
                count++;
            }
        }
        if(count==9)
        {
            if(checkWin())
                return;
            showMsg(" Game Tie");
            isGameActive=false;
        }
    }

    private void showMsg(String winner) {
        new AlertDialog.Builder(this)
                .setTitle(winner)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        restart();
                    }
                })
                .show();
    }

    private void restart() {
        activeplayer = player_o;
        headerText.setText("o turn");
        pos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(android.R.color.darker_gray));
        btn1.setBackground(getDrawable(android.R.color.darker_gray));
        btn2.setBackground(getDrawable(android.R.color.darker_gray));
        btn3.setBackground(getDrawable(android.R.color.darker_gray));
        btn4.setBackground(getDrawable(android.R.color.darker_gray));
        btn5.setBackground(getDrawable(android.R.color.darker_gray));
        btn6.setBackground(getDrawable(android.R.color.darker_gray));
        btn7.setBackground(getDrawable(android.R.color.darker_gray));
        btn8.setBackground(getDrawable(android.R.color.darker_gray));

        isGameActive = true;
    }


}