from Socket import tcp_listener
from wifi_check import connect


ssid = "Vodafone-34AC"
password = "RYxHLt6t4nAm679Z"
connect(ssid, password)

while True:
    tcp_listener()
