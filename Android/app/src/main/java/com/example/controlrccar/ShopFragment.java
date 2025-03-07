package com.example.controlrccar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.widget.EditText;
public class ShopFragment extends AppCompatActivity {
    RaspberryConnection connection;


    @Override
     protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shop);
        Button buttonHome = findViewById(R.id.btnHome);
        buttonHome.setOnClickListener(v ->{
            Intent intent=new Intent(ShopFragment.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        });
        Button buttonIP=findViewById(R.id.button_IP_set);
        buttonIP.setOnClickListener(v->{
                ConnectionManager.connection=new RaspberryConnection();
            if (ConnectionManager.connection != null) {
                EditText editText = findViewById(R.id.editTextText);
                if (editText != null) {
                    String ip = editText.getText().toString();
                    ConnectionManager.connection.connect(ip, 1050);
                } else {
                    Log.e("ERROR", "EditText is null!");
                }
            } else {
                Log.e("ERROR", "Connection object is null!");
            }

        });
    }
}


