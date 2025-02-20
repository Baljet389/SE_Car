package com.example.controlrccar
import java.io.OutputStream
import java.io.PrintWriter
import java.net.Socket

class RaspberryConnection {
    private var socket: Socket? = null
    private var outputStream: OutputStream? = null
    private var writer: PrintWriter? = null
    var success:Boolean=false
    fun connect(ip: String, port: Int) {
        Thread {
            try {
                socket = Socket(ip, port)
                val outputStream = socket?.getOutputStream() ?: throw NullPointerException("Output stream is null")
                writer =  PrintWriter(outputStream, true)
                success=true
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

    fun disconnect() {
        try {
            writer?.close()
            outputStream?.close()
            socket?.close()
            success=false
        }   catch (e: Exception) {
            e.printStackTrace()
        }
    }
}