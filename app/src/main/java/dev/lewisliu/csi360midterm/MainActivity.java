package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button casinoBtn;
    Button bankBtn;
    Account account;
    TextView bankName;
    TextView accountNum;
    TextView balance;
    TextView showRecentAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casinoBtn = (Button) findViewById(R.id.casinoBtn);
        bankBtn = (Button) findViewById(R.id.bankBtn);
        bankName = (TextView) findViewById(R.id.maBankName);
        accountNum = (TextView) findViewById(R.id.maAccountNum);
        balance = (TextView) findViewById(R.id.maAccountBalance);
        showRecentAction = (TextView) findViewById(R.id.maShowRecentAction);

        if (getIntent().getSerializableExtra("MyAccount") == null) {
            account = new Account(bankName.getText().toString(), accountNum.getText().toString());
        } else {
            account = (Account) getIntent().getSerializableExtra("MyAccount");
        }

        balance.setText("$ " + account.get_balance());

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
        Intent intent = new Intent(this, Casino.class);
        startActivity(intent);
    }

    private void openBank() {
        Intent intent = new Intent(this, Bank.class);
        startActivity(intent.putExtra("MyAccount", account));
    }
}