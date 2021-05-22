package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button casinoBtn;
    Button bankBtn;
    Finance finance;
    TextView bankName;
    TextView accountNum;
    TextView balance;
    TextView wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casinoBtn = (Button) findViewById(R.id.casinoBtn);
        bankBtn = (Button) findViewById(R.id.bankBtn);
        bankName = (TextView) findViewById(R.id.maBankName);
        accountNum = (TextView) findViewById(R.id.maAccountNum);
        balance = (TextView) findViewById(R.id.maAccountBalance);
        wallet = (TextView) findViewById(R.id.maWallet);

        if (getIntent().getSerializableExtra("MyFinance") == null || getIntent().getSerializableExtra("MyFinance") == null) {
            finance = new Finance(bankName.getText().toString(), accountNum.getText().toString());
        } else {
            finance = (Finance) getIntent().getSerializableExtra("MyFinance");
        }

        balance.setText("$ " + finance.get_balance());
        wallet.setText("$ " + finance.get_wallet());

        casinoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCasino();
            }
        });

        bankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBank();
            }
        });
    }

    private void openCasino() {
        if (finance.get_wallet() < 50) {
            AlertDialog.Builder noMoney = new AlertDialog.Builder(this);
            noMoney.setTitle("No $$, No Gambling!")
                    .setMessage("You gotta have at least $50 in your pocket to enter the casino!")
                    .setNegativeButton("Go Home", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            Intent intent = new Intent(this, Casino.class);
            startActivity(intent.putExtra("MyFinance", finance));
        }
    }

    private void openBank() {
        Intent intent = new Intent(this, Bank.class);
        startActivity(intent.putExtra("MyFinance", finance));
    }
}