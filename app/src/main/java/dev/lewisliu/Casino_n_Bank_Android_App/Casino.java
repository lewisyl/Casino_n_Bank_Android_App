package dev.lewisliu.Casino_n_Bank_Android_App;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
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
    Button player1PassBtn;
    Button player2PassBtn;
    ImageView dice1Image;
    ImageView dice2Image;
    ImageView dice3Image;
    Random random = new Random();
    TextView player1Points;
    TextView player2Points;
    TextView player1TotalPts;
    TextView player2TotalPts;
    TextView player1RollingChance;
    TextView player2RollingChance;
    TextView wallet;
    ConstraintLayout player1Slot;
    ConstraintLayout player2Slot;
    Finance finance;
    int chance = 3;
    int player1Total, player2Total, rollPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);

        backHomeBtn = (Button) findViewById(R.id.casinoBackHome);
        player1Slot = (ConstraintLayout) findViewById(R.id.player1Slot);
        player2Slot = (ConstraintLayout) findViewById(R.id.player2Slot);
        player1RollBtn = (Button) findViewById(R.id.player1Roll);
        player2RollBtn = (Button) findViewById(R.id.player2Roll);
        player1PassBtn = (Button) findViewById(R.id.player1Pass);
        player2PassBtn = (Button) findViewById(R.id.player2Pass);
        dice1Image = (ImageView) findViewById(R.id.dice1);
        dice2Image = (ImageView) findViewById(R.id.dice2);
        dice3Image = (ImageView) findViewById(R.id.dice3);
        player1Points = (TextView) findViewById(R.id.player1Points);
        player2Points = (TextView) findViewById(R.id.player2Points);
        player1TotalPts = (TextView) findViewById(R.id.player1TotalPt);
        player2TotalPts = (TextView) findViewById(R.id.player2TotalPt);
        player1RollingChance = (TextView) findViewById(R.id.player1RollingChance);
        player2RollingChance = (TextView) findViewById(R.id.player2RollingChance);
        wallet = (TextView) findViewById(R.id.csnWallet);

        finance = (Finance) getIntent().getSerializableExtra("MyFinance");
        wallet.setText("$ " + getCurrentWallet());

        player2PassBtn.setEnabled(false);
        player2RollBtn.setEnabled(false);
        player2Slot.setBackground(null);

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
                rollDice(player, getCurrentWallet());

            }
        });

        player2RollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int player = 2;
                rollDice(player, getCurrentWallet());
            }
        });

        player1PassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int player = 1;
                switchPlayer(player, rollPoints, getCurrentWallet());
                rollPoints = 0;
            }
        });

        player2PassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int player = 2;
                switchPlayer(player, rollPoints, getCurrentWallet());
                rollPoints = 0;
            }
        });
    }

    private void backHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.putExtra("MyFinance", finance));
    }

    private void rollDice(int player, int amountInPocket) {
        int randomDice1 = random.nextInt(6) + 1;
        int randomDice2 = random.nextInt(6) + 1;
        int randomDice3 = random.nextInt(6) + 1;

        rollPoints = randomDice1 + randomDice2 + randomDice3;

        selectDiceImg(randomDice1, dice1Image);
        selectDiceImg(randomDice2, dice2Image);
        selectDiceImg(randomDice3, dice3Image);

        chance --;

        if (player == 1) {
            player1Points.setText(String.valueOf(rollPoints));
            player1RollingChance.setText(String.valueOf(chance));
        } else {
            player2Points.setText(String.valueOf(rollPoints));
            player2RollingChance.setText(String.valueOf(chance));
        }
        if (chance == 0) {
            switchPlayer(player, rollPoints, amountInPocket);
            rollPoints = 0;
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

    private void switchPlayer(int player, int rollPoints, int amountInPocket) {
        chance = 3;
        if (player == 1) {
            player1Total += rollPoints;
            if (player1Total >= 100) {
                int currentWalletAmount = amountInPocket + 50;
                finance.set_wallet(currentWalletAmount);
                wallet.setText("$ " + currentWalletAmount);
                winningDialog(player, currentWalletAmount);
                return;
            }
            player1TotalPts.setText(String.valueOf(player1Total));

            player2RollBtn.setEnabled(true);
            player2PassBtn.setEnabled(true);
            player1RollBtn.setEnabled(false);
            player1PassBtn.setEnabled(false);

            player2RollingChance.setText("3");

            player1Slot.setBackground(null);
            player2Slot.setBackground(getResources().getDrawable(R.drawable.customborder));
        } else if (player == 2){
            player2Total += rollPoints;
            if (player2Total >= 100) {
                int currentWalletAmount = amountInPocket - 50;
                finance.set_wallet(currentWalletAmount);
                currentWalletAmount = getCurrentWallet();
                wallet.setText("$ " + currentWalletAmount);
                if (currentWalletAmount < 50) {
                    noMoneyDialog();
                } else {
                    winningDialog(player, currentWalletAmount);
                }
                return;
            }
            player2TotalPts.setText(String.valueOf(player2Total));

            player2RollBtn.setEnabled(false);
            player2PassBtn.setEnabled(false);
            player1RollBtn.setEnabled(true);
            player1PassBtn.setEnabled(true);

            player1RollingChance.setText("3");

            player2Slot.setBackground(null);
            player1Slot.setBackground(getResources().getDrawable(R.drawable.customborder));
        }
    }

    private void winningDialog(int player, int amountInPocket) {
        AlertDialog.Builder win = new AlertDialog.Builder(this);
        win.setTitle("The Winner is Player " + player + "!!")
                .setMessage("Do You Want To Play Again?")
                .setPositiveButton("Heck Yeah! One More Round!",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resetTabletop(amountInPocket);
                    }
                })
                .setNegativeButton("My Mom Asks Me To Go Home...",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backHome();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void noMoneyDialog() {
        AlertDialog.Builder noMoney = new AlertDialog.Builder(this);
        noMoney.setTitle("YOU LOST!")
                .setMessage("Go Home! You've NO $$ in your pocket!")
                .setNegativeButton("Go Home",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                backHome();
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void resetTabletop (int amountInPocket) {
        player2RollBtn.setEnabled(false);
        player2PassBtn.setEnabled(false);
        player1RollBtn.setEnabled(true);
        player1PassBtn.setEnabled(true);
        player1Slot.setBackground(getResources().getDrawable(R.drawable.customborder));
        player2Slot.setBackground(null);

        wallet.setText("$ " + amountInPocket);

        player1Total = 0;
        player2Total = 0;
        rollPoints = 0;

        player1Points.setText("0");
        player2Points.setText("0");
        player1TotalPts.setText("0");
        player2TotalPts.setText("0");
        player1RollingChance.setText("3");
        player2RollingChance.setText("3");
    }

    private int getCurrentWallet() {
        return finance.get_wallet();
    }
}