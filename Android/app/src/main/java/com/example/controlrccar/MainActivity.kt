package com.example.controlrccar

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    var connection:RaspberryConnection ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnection: Button= findViewById(R.id.btnShop)
        btnConnection.setOnClickListener {
            val intent = Intent(this@MainActivity, ShopFragment::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            intent.putExtra("connection",connection)
            startActivity(intent)
        }

    }
}