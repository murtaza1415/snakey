package com.example.muhammadmurtaza.snakey;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    public int playerPos = 0;
    public int compPos = 0;
    public int playerFlag = 0;
    public int compFlag = 0;
    public int redX;
    public int redY;
    public int blueX;
    public int blueY;
    public static final String EXTRA_MESSAGE = "snakey.message";
    public static List<Snake> snakesList = new ArrayList<>();
    public static List<Ladder> laddersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        snakesList.add(new Snake(21,15));
        snakesList.add(new Snake(23,6));
        snakesList.add(new Snake(29,15));
        snakesList.add(new Snake(35,18));
        snakesList.add(new Snake(47,32));
        snakesList.add(new Snake(52,38));
        snakesList.add(new Snake(71,34));
        snakesList.add(new Snake(82,59));
        snakesList.add(new Snake(95,78));
        snakesList.add(new Snake(99,79));

        laddersList.add(new Ladder(2,18));
        laddersList.add(new Ladder(12,28));
        laddersList.add(new Ladder(11,31));
        laddersList.add(new Ladder(22,40));
        laddersList.add(new Ladder(36,62));
        laddersList.add(new Ladder(41,59));
        laddersList.add(new Ladder(46,55));
        laddersList.add(new Ladder(70,94));
        laddersList.add(new Ladder(77,84));
        laddersList.add(new Ladder(85,97));
    }

    public void buttonRoll(View view) {
        movePlayer();
    }

    public void movePlayer() {
        Random ran = new Random();
        int DiceVal = ran.nextInt(6) + 1;
        String val = "dice" + DiceVal;
        ImageView diceImage = (ImageView) findViewById(R.id.dice);
        diceImage.setImageResource(getResources().getIdentifier(val, "drawable", getPackageName()));

       /** if(playerFlag == 1 && DiceVal!=6) {
            moveComp();
            return;
        }
        if(playerFlag == 1 && DiceVal==6) {
            playerFlag = 0;
        }*/

        if((playerPos + DiceVal) > 100) {
            moveComp();
            return;
        }


        playerPos += DiceVal;
        for(int i=0; i<snakesList.size(); i++)
        {
            if(playerPos == snakesList.get(i).pos) {
                playerPos = snakesList.get(i).tail;
                playerFlag = 1;
                break;
            }
            if(playerPos == laddersList.get(i).pos) {
                playerPos = laddersList.get(i).top;
                break;
            }
        }
        TextView textPlayer = (TextView) findViewById(R.id.textYou);
        textPlayer.setText("You: " + playerPos);
        if(playerPos == 100) {
            Intent intent = new Intent(this, Result.class);
            String message = "You Won!";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

        moveRed();
        if(DiceVal==6) {
            return;
        }
        else {
            moveComp();
        }
    }

    public void moveComp() {
        Random ran = new Random();
        int DiceVal = ran.nextInt(6) + 1;
        String val = "dice" + DiceVal;

        /**if(compFlag == 1 && DiceVal!=6) {
            return;
        }
        if(compFlag == 1 && DiceVal==6) {
            compFlag = 0;
        }*/

        if(DiceVal==6) {
            moveComp();
        }

        if((compPos + DiceVal) > 100) {
            return;
        }
        compPos += DiceVal;
        for(int i=0; i<snakesList.size(); i++)
        {
            if(compPos == snakesList.get(i).pos) {
                compPos = snakesList.get(i).tail;
                compFlag = 1;
                break;
            }
            if(compPos == laddersList.get(i).pos) {
                compPos = laddersList.get(i).top;
                break;
            }
        }
        TextView textComp = (TextView) findViewById(R.id.textComp);
        textComp.setText("Comp: " + compPos);

        if(compPos == 100) {
            Intent intent = new Intent(this, Result.class);
            String message = "You Lost!";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

        //TextView textTurn = (TextView) findViewById(R.id.textTurn);
        //textTurn.setText("Comp's turn");
        moveBlue();
        //textTurn.setText("Your turn");
    }

    public void moveRed() {
        ImageView board = (ImageView) findViewById(R.id.board);
        ImageView red = (ImageView) findViewById(R.id.red);
        float board_x = board.getX();
        float board_y = board.getY();
        float board_h = board.getHeight();
        redY = (int)board_y + (int)board_h - 40;

        if(((playerPos-1)/10)%2 != 1) {
            redX = 72 * (((playerPos - 1) % 10));
        }
        else{
            redX = 648-(72*((playerPos-1) % 10));
        }

        red.setX(redX);

        //board height is 768. 77
        redY = redY-(77*((playerPos-1)/10));
        red.setY(redY);
    }

    public void moveBlue() {
        ImageView board = (ImageView) findViewById(R.id.board);
        ImageView blue = (ImageView) findViewById(R.id.blue);
        float board_x = board.getX();
        float board_y = board.getY();
        float board_h = board.getHeight();
        blueY = (int)board_y + (int)board_h - 40;

        if(((compPos-1)/10)%2 != 1) {
            blueX = 72 * (((compPos - 1) % 10));
        }
        else{
            blueX = 648-(72*((compPos-1) % 10));
        }


        //board height is 768. 77
        blueY = blueY-(77*((compPos-1)/10));

        blue.setY(blueY);
        blue.setX(blueX);

    }
}