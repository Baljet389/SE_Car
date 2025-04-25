"""
*****************************************************************************
 * Copyright 2012-2025, All rights reserved, For internal use only
 *
 * FILE: wifi_connection.py
 * PROJECT: RC Car Controller
 * MODULE: Network Configuration
 *
 * Description:
 * This script handles the Wi-Fi connection setup for the RC Car Controller.
 * It attempts to connect to a specified Wi-Fi network using provided credentials.
 *
 * Notes:
 * - Uses MicroPython's network module to manage the connection.
 * - Waits for a successful connection within a timeout period.
 * - Prints connection status and IP configuration upon success.
 *
 * Compiler dependencies or special instructions:
 * - Designed for MicroPython-based systems (e.g., ESP32, Raspberry Pi Pico W).
 * - Requires a valid SSID and password for connection.
 *
 * REVISION HISTORY
 * Date:        By:                 Description:
 * 2025-03-12   Johannes Winter     Initial implementation and documentation update.
 *
*****************************************************************************
"""
import network
import time


def connect(ssid,password):
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.connect(ssid, password)

    # Warten, bis die Verbindung steht
    max_wait = 10
    while max_wait > 0:
        if wlan.isconnected():
            print("Verbunden mit:", wlan.ifconfig())
            break
        max_wait -= 1
        time.sleep(1)

    if not wlan.isconnected():
        print("Fehler: WLAN nicht verbunden!")
    else:
        print("Erfolgreich verbunden.")
