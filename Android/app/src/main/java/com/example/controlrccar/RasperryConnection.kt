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