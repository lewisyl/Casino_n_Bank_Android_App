package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button casinoBtn;
    Button bankBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casinoBtn = (Button) findViewById(R.id.casinoBtn);
        bankBtn = (Button) findViewById(R.id.bankBtn);

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

    public void openCasino() {
        Intent intent = new Intent(this, Casino.class);
        startActivity(intent);
    }

    private void openBank() {
        Intent intent = new Intent(this, Bank.class);
        startActivity(intent);
    }
}