package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Casino extends AppCompatActivity {
    Button backHomeBtn;
    Button player1RollBtn;
    Button player2RollBtn;
    Button player1DoneBtn;
    Button player2DoneBtn;
    ImageView dice1Image;
    ImageView dice2Image;
    ImageView dice3Image;
    Random random = new Random();
    TextView player1Points;
    TextView player2Points;
    TextView player1RollingChance;
    TextView player2RollingChance;
    int p1, p2 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);

        backHomeBtn = (Button) findViewById(R.id.casinoBackHome);
        player1RollBtn = (Button) findViewById(R.id.player1Roll);
        player2RollBtn = (Button) findViewById(R.id.player2Roll);
        player1DoneBtn = (Button) findViewById(R.id.player1Done);
        player2DoneBtn = (Button) findViewById(R.id.player2Done);
        dice1Image = (ImageView) findViewById(R.id.dice1);
        dice2Image = (ImageView) findViewById(R.id.dice2);
        dice3Image = (ImageView) findViewById(R.id.dice3);
        player1Points = (TextView) findViewById(R.id.player1Points);
        player2Points = (TextView) findViewById(R.id.player2Points);
        player1RollingChance = (TextView) findViewById(R.id.player1RollingChance);
        player2RollingChance = (TextView) findViewById(R.id.player2RollingChance);

        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });

        player1RollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int player = 1;
                rollDice(player);

            }
        });

        player2RollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int player = 2;
                rollDice(player);
            }
        });
    }

    private void backHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void rollDice(int player) {
        int randomDice1 = random.nextInt(6) + 1;
        int randomDice2 = random.nextInt(6) + 1;
        int randomDice3 = random.nextInt(6) + 1;

        int rollPoints = randomDice1 + randomDice2 + randomDice3;

        selectDiceImg(randomDice1, dice1Image);
        selectDiceImg(randomDice2, dice2Image);
        selectDiceImg(randomDice3, dice3Image);

        if (player == 1) {
            player1Points.setText(String.valueOf(rollPoints));
        } else {
            player2Points.setText(String.valueOf(rollPoints));
        }
    }

    private void selectDiceImg (int randomResult, ImageView diceImage) {
        switch (randomResult) {
            case 1:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice2);
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice3);
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice4);
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice5);
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice6);
                break;
        }
    }
}