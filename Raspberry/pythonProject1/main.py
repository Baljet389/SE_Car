from Socket import tcp_listener
from wifi_check import connect

ssid = "iPhone von Johannes"
password = "test1234"
connect(ssid,password)
while True:

    tcp_listener()
