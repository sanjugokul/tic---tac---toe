package com.example.t_cube;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView Title;


    int Player1=0;
    int Player2=1;

    int activePlayer=Player1;

    int[] filledpos={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean isgameActive=true;

    int Count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
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

    private void setUI() {
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        Title=findViewById(R.id.tvtitle);

    }


    @Override
    public void onClick(View view) {
        //Logic for Button press

        if(!isgameActive)
            return;
        Button clickedbtn=findViewById(view.getId());
        int clickedtag=Integer.parseInt(view.getTag().toString());

        if(filledpos[clickedtag]!=-1){
            return;
        }
        filledpos[clickedtag]=activePlayer;
        Count++;

        if(activePlayer==Player1){
            clickedbtn.setText("O");
            activePlayer=Player2;
            Title.setText("Player 2 Turn");
        }else{
            clickedbtn.setText("X");
            activePlayer=Player1;
            Title.setText("Player 1 Turn");
        }

        checkForwin();

    }



    private void checkForwin() {
        //Check who is winner

        int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i=0;i<8;i++){
            int value0=winningpos[i][0];
            int value1=winningpos[i][1];
            int value2=winningpos[i][2];

            if(filledpos[value0]==filledpos[value1] && filledpos[value1]==filledpos[value2] && filledpos[value0]!=-1){
                //Winner Declare

                isgameActive=false;
                if(filledpos[value0]==Player1){
                    showDialog("Player 1 is Winner");
                }else{
                    showDialog("Player 2 is winner");
                }
            }
        }

    }


private void showDialog(String winnerText){
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                }).show();
}

    private void checkForDraw() {

        if(Count==9){
            new AlertDialog.Builder(this)
                    .setTitle("Match Drawn")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            restartGame();
                        }
                    }).show();
        }
    }

private void restartGame(){
        Count=0;
        activePlayer=Player1;
        filledpos=new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn0.setText("");
    btn1.setText("");
    btn2.setText("");
    btn3.setText("");
    btn4.setText("");
    btn5.setText("");
    btn6.setText("");
    btn7.setText("");
    btn8.setText("");
    isgameActive=true;
    Title.setText("Player 1 Turn");

}
}

