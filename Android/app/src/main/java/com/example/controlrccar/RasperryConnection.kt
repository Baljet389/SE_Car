/*****************************************************************************
 * Copyright 2012-2025, All rights reserved, For internal use only
 *
 * FILE: RaspberryConnection.kt
 * PROJECT: RC Car Controller
 * MODULE: Network Communication
 *
 * Description:
 * Handles the TCP connection between the Android app and a Raspberry Pi.
 * Provides methods to establish a socket connection, send messages, and disconnect.
 *
 * Notes:
 * - Uses a separate thread for network operations.
 * - Automatically logs connection success or failure.
 * - Ensures the output stream and socket are properly managed.
 *
 * Compiler dependencies or special instructions:
 * - Requires internet permissions in AndroidManifest.xml.
 * - Designed for use with a Raspberry Pi server.
 *
 * REVISION HISTORY
 * Date:        By:            Description:
 * 2025-03-12   Jacob Jaeger   Initial implementation and documentation update.
 *
 *****************************************************************************/
package com.example.controlrccar

import android.util.Log
import java.io.OutputStream
import java.io.PrintWriter
import  java.io.Serializable
import java.net.Socket
class RaspberryConnection:Serializable {
    private var socket: Socket? = null
    private var outputStream: OutputStream? = null
    private var writer: PrintWriter? = null

    fun connect(ip: String, port: Int) {
        Thread {
            try {

                socket = Socket(ip, port)
                val outputStream = socket?.getOutputStream() ?: throw NullPointerException("Output stream is null")
                writer =  PrintWriter(outputStream, true)
                Log.d("DEBUG", "Connection success")
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }.start()
    }
    fun sendMessage(message: String) {
        Thread {
            try {
                writer?.println(message)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun disconnect() {
        try {
            writer?.close()
            outputStream?.close()
            socket?.close()
        }   catch (e: Exception) {
            e.printStackTrace()
        }
    }

}