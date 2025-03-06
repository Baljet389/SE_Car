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
