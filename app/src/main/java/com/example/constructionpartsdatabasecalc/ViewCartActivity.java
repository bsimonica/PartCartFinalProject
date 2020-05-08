package com.example.constructionpartsdatabasecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewCartActivity extends AppCompatActivity {

    private ScrollView scrollCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        scrollCart = findViewById(R.id.scrollCart);
        scrollCart.removeAllViewsInLayout();
        PartCartManager pcm = new PartCartManager(this);
        ArrayList<String> list = pcm.getParts();
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());
        for(String name : list){
            TextView text = new TextView(this);
            text.setText(name);
            text.setTextSize(40);
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), PartActivity.class);
                    i.putExtra("NAME", ((TextView) v).getText().toString());
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });
            grid.addView(text);
        }
        scrollCart.addView(grid);
    }

    public void addPressed(View v){
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
}
