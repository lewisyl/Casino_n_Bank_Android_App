package dev.lewisliu.csi360midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button casinoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casinoBtn = (Button) findViewById(R.id.casinoBtn);
        casinoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCasino();
            }
        });
    }

    public void openCasino() {
        Intent intent = new Intent(this, Casino.class);
        startActivity(intent);
    }
}