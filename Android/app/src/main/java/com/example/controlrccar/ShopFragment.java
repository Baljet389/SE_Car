package com.example.controlrccar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
public class ShopFragment extends AppCompatActivity {
    RaspberryConnection connection;


    @Override
     protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shop);
        connection=(RaspberryConnection) getIntent().getSerializableExtra("connection");
        Button buttonHome = findViewById(R.id.btnHome);
        buttonHome.setOnClickListener(v ->{
            Intent intent=new Intent(ShopFragment.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        });
        Button buttonIP=findViewById(R.id.button_IP_set);
        buttonIP.setOnClickListener(v->{
                connection = new RaspberryConnection();
                connection.connect(((EditText) findViewById(R.id.editTextText)).getText().toString(), 1050);

            });
    }
}


