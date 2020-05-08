package com.example.constructionpartsdatabasecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText priceInput;
    private EditText storeInput;
    private Button addToCart;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameInput = findViewById(R.id.nameInput);
        priceInput = findViewById(R.id.priceInput);
        storeInput = findViewById(R.id.storeInput);
        addToCart = findViewById(R.id.addToCart);
        Intent i = getIntent();
        add = i.getBooleanExtra("ADD", true);
        if(add){
            addToCart.setText("ADD");
        }else{
            addToCart.setText("EDIT");
            nameInput.setText(i.getStringExtra("NAME"));
            priceInput.setText(i.getStringExtra("PRICE"));
            storeInput.setText(i.getStringExtra("STORE"));
        }
    }

    public void addToCart(View v){
        if(nameInput != null) {
            if(priceInput != null) {
                if(storeInput != null) {
                    String name = nameInput.getText().toString();
                    String price = priceInput.getText().toString();
                    String store = storeInput.getText().toString();
                    PartCartManager pcm = new PartCartManager(this);
                    if (add) {
                        pcm.insert(name, price, store);
                    } else {
                        pcm.updateByName(name, price, store);
                    }
                    finish();
                }else{
                    Toast.makeText(this, "You must enter STORE where item is sold", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "You must enter PRICE of item", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "You must enter NAME of item", Toast.LENGTH_SHORT).show();
        }
    }

}
