package com.example.constructionpartsdatabasecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    private TextView priceCalc;
    private EditText numParts;
    private TextView totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        priceCalc = findViewById(R.id.priceCalc);
        numParts = findViewById(R.id.numParts);
        totalCost = findViewById(R.id.totalCost);
        Intent i = getIntent();
        priceCalc.setText(i.getStringExtra("PRICE"));
    }

    public void calcPressed(View v){
        if(numParts != null) {
            String p = priceCalc.getText().toString();
            String n = numParts.getText().toString();
            double prc = Double.valueOf(p).doubleValue();
            double num = Double.valueOf(n).doubleValue();
            double tot = prc * num;
            totalCost.setText(String.valueOf(String.format("%.2f", tot)));
        }else{
            Toast.makeText(this, "You must enter AMOUNT of desired parts", Toast.LENGTH_SHORT).show();
        }
    }
}
