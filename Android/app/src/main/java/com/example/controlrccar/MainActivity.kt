/*****************************************************************************
 * Copyright 2012-2025, All rights reserved, For internal use only
 *
 * FILE: MainActivity.kt
 * PROJECT: RC Car Controller
 * MODULE: Main Application
 *
 * Description:
 * The main activity of the RC Car Controller app. It initializes the user interface,
 * handles interactions with the CustomWheelView and SeekBar, and sends control commands
 * via the ConnectionManager.
 *
 * Notes:
 * - The wheel view allows steering control.
 * - The SeekBar controls speed or throttle.
 * - Changes beyond a threshold trigger messages to the connection.
 * - A button navigates to the ShopFragment.
 *
 * Compiler dependencies or special instructions:
 * - Requires Android API level 21 (Lollipop) or higher.
 * - Uses Kotlin standard functions.
 *
 * REVISION HISTORY
 * Date:        By:            Description:
 * 2025-03-12   Jacob Jaeger   Initial implementation and documentation update.
 *
 *****************************************************************************/
package com.example.controlrccar

import android.os.Bundle
import android.widget.Button
import  android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    var lastAngle:Int =90
    var lastProgress:Int =50
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wheel:CustomWheelView =findViewById(R.id.wheelView)
        val seekbar:SeekBar=findViewById(R.id.verticalSeekBar)
        wheel.setWheelAngle(90f)
        wheel.onWheelChangeListener = {
            if (abs(wheel.currentAngle.toInt() - lastAngle) > 5) {
                val connection = ConnectionManager.connection
                connection?.sendMessage(
                    seekbar.progress.toString()   +"-"+ wheel.currentAngle.toInt().toString()
                )
                lastAngle=wheel.currentAngle.toInt()
            }
        }
        val btnConnection: Button= findViewById(R.id.btnShop)
        btnConnection.setOnClickListener {
            val intent = Intent(this@MainActivity, ShopFragment::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)

        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if(abs(progress-lastProgress)>5) {
                    val connection = ConnectionManager.connection
                    connection?.sendMessage(
                        progress.toString()  +"-"+wheel.currentAngle.toInt().toString()
                    )
                    lastProgress=progress
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Optional: Do something when the user starts touching the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Optional: Do something when the user stops touching the SeekBar


            }
        })

    }
}