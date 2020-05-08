package com.example.constructionpartsdatabasecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PartActivity extends AppCompatActivity {

    private TextView nameView;
    private TextView priceView;
    private TextView storeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);

        nameView = findViewById(R.id.nameView);
        priceView = findViewById(R.id.priceView);
        storeView = findViewById(R.id.storeView);

        PartCartManager pcm = new PartCartManager(this);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        String[] part = pcm.get(name);
        nameView.setText(part[0]);
        priceView.setText(part[1]);
        storeView.setText(part[2]);


    }

    public void editPressed(View v){
        Intent i = new Intent(this, AddActivity.class);
        i.putExtra("ADD", false);
        i.putExtra("NAME", nameView.getText().toString());
        i.putExtra("PRICE", priceView.getText().toString());
        i.putExtra("STORE", storeView.getText().toString());
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void deletePressed(View v){
        PartCartManager pcm = new PartCartManager(this);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        pcm.delete(name);
        finish();
    }

    public void calcPressed(View v){
        Intent i = new Intent(this, CalculatorActivity.class);
        i.putExtra("PRICE", priceView.getText().toString());
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
