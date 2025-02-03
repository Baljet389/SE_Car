package com.example.controlrccar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wheelView: CustomWheelView = findViewById(R.id.wheelView)
        wheelView.onWheelChangeListener = { rotation ->
            Toast.makeText(this, "Rotation: $rotation°", Toast.LENGTH_SHORT).show()
        }
    }
}