package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bank extends AppCompatActivity {
    TextView bankName;
    TextView accountNum;
    TextView currentBalance;
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
        depositAmount = (EditText) findViewById(R.id.bkDepositAmount);
        withdrawAmount = (EditText) findViewById(R.id.bkWithdrawAmount);
        deposit = (Button) findViewById(R.id.bkDepositBtn);
        withdraw = (Button) findViewById(R.id.bkWithdrawBtn);
        backHome = (Button) findViewById(R.id.bkBackHome);

        finance = (Finance) getIntent().getSerializableExtra("MyFinance");

        int balance = finance.get_balance();

        bankName.setText(finance.get_bankName());
        accountNum.setText(finance.get_accountNum());
        currentBalance.setText("$ " + balance);


        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newB = balance + 888;
                finance.set_balance(newB);
                backHome();
            }
        });

    }
    private void backHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.putExtra("MyFinance", finance));
    }

}