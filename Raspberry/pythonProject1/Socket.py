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