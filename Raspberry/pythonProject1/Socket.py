"""
*****************************************************************************
 * Copyright 2012-2025, All rights reserved, For internal use only
 *
 * FILE: tcp_listener.py
 * PROJECT: RC Car Controller
 * MODULE: Network Communication
 *
 * Description:
 * TCP server script that listens for incoming connections from clients.
 * It receives messages and processes them using the SplitMessage function.
 *
 * Notes:
 * - Listens on port 1050 for incoming TCP connections.
 * - Handles multiple client connections sequentially.
 * - Decodes incoming messages and processes them.
 * - Closes connections properly after communication ends.
 *
 * Compiler dependencies or special instructions:
 * - Requires Python 3.x.
 * - Requires the 'handleMessage' module with the SplitMessage function.
 *
 * REVISION HISTORY
 * Date:        By:            Description:
 * 2025-03-12   Jacob Jaeger   Initial implementation and documentation update.
 *
*****************************************************************************
"""

import socket
from handleMessage import SplitMessage


def tcp_listener():
    host = '0.0.0.0'
    port = 1050
    listener = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    listener.bind((host, port))

    listener.listen(5)
    print(f"Listening for connections on {host}:{port}...")

    while True:
        client_socket, client_address = listener.accept()
        print(f"Connection established with {client_address}")

        try:
            while True:
                data = client_socket.recv(1024)
                if not data:
                    break

                SplitMessage(data.decode())
        except Exception as e:
            print(f"Error reading data: {e}")
        finally:
            # Schließe die Verbindung zum Client
            client_socket.close()
            print("Connection closed.")