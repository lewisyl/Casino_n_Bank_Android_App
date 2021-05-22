package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;

public class Bank extends AppCompatActivity {
    TextView bankName;
    TextView accountNum;
    TextView currentBalance;
    TextView wallet;
    EditText depositAmount;
    EditText withdrawAmount;
    Button deposit;
    Button withdraw;
    Button backHome;
    Finance finance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        bankName = (TextView) findViewById(R.id.bkBankName);
        accountNum = (TextView) findViewById(R.id.bkAccountNum);
        currentBalance = (TextView) findViewById(R.id.bkBalance);
        wallet = (TextView) findViewById(R.id.bkWallet);
        depositAmount = (EditText) findViewById(R.id.bkDepositAmount);
        withdrawAmount = (EditText) findViewById(R.id.bkWithdrawAmount);
        deposit = (Button) findViewById(R.id.bkDepositBtn);
        withdraw = (Button) findViewById(R.id.bkWithdrawBtn);
        backHome = (Button) findViewById(R.id.bkBackHome);

        finance = (Finance) getIntent().getSerializableExtra("MyFinance");

        int balance = finance.get_balance();
        int moneyInPocket = finance.get_wallet();

        bankName.setText(finance.get_bankName());
        accountNum.setText(finance.get_accountNum());
        currentBalance.setText("$ " + balance);
        wallet.setText("$ " + moneyInPocket);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = depositAmount.getText().toString();
                int finalAmount;
                if (!amount.equals("")) {
                    finalAmount = Integer.parseInt(amount);
                    deposit(finalAmount);
                    return;
                }
                emptyInputErr();
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = withdrawAmount.getText().toString();
                int finalAmount;
                if (!amount.equals("")) {
                    finalAmount = Integer.parseInt(amount);
                    withdraw(finalAmount);
                    return;
                }
                emptyInputErr();
            }
        });
    }

    private void backHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.putExtra("MyFinance", finance));
    }

    private void deposit(int amount) {
        int currentWalletBalance = finance.get_wallet();
        int currentAccountBalance = finance.get_balance();

        if (amount > currentWalletBalance) {
            String title = "Check Your Pocket! No Fraud!";
            String errMsg = "You don't have enough cash in your pocket to make this deposit.";
            wrongAmountErr(title, errMsg);
        } else if (amount <= 0) {
            negativeAmountErr();
        } else {
            finance.set_wallet(currentWalletBalance - amount);
            finance.set_balance(currentAccountBalance + amount);
            currentBalance.setText("$ " + finance.get_balance());
            wallet.setText("$ " + finance.get_wallet());
            depositAmount.setText("");
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "Success! You just deposited $" + amount + " to your account.",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void withdraw(int amount) {
        int currentWalletBalance = finance.get_wallet();
        int currentAccountBalance = finance.get_balance();

        if (amount > currentAccountBalance) {
            String title = "Overdraft";
            String errMsg = "Sorry dude, overdraft is not allowed.";
            wrongAmountErr(title, errMsg);
        } else if (amount <= 0) {
            negativeAmountErr();
        } else {
            finance.set_wallet(currentWalletBalance + amount);
            finance.set_balance(currentAccountBalance - amount);
            currentBalance.setText("$ " + finance.get_balance());
            wallet.setText("$ " + finance.get_wallet());
            withdrawAmount.setText("");
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "Success! You just withdrew $" + amount + " from your account.",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void negativeAmountErr() {
        AlertDialog.Builder negativeAmount = new AlertDialog.Builder(this);
        negativeAmount.setTitle("Negative Amount is NOT Allowed")
                .setMessage("Please only enter positive amount.")
                .setNegativeButton("Check and Try Again", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void wrongAmountErr(String title, String msg) {
        AlertDialog.Builder wrongAmount = new AlertDialog.Builder(this);
        wrongAmount.setTitle("Check Your Pocket! No Fraud!")
                .setMessage("You don't have enough cash in your pocket to make this deposit.")
                .setNegativeButton("Check and Try Again", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void emptyInputErr() {
        AlertDialog.Builder emptyInput = new AlertDialog.Builder(this);
        emptyInput.setTitle("No Amount Entered")
                .setMessage("Please enter a positive amount.")
                .setNegativeButton("Check and Try Again", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}