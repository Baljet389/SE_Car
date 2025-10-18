from machine import Pin, PWM

def SplitMessage(message):
    print(message)
    try:
        if "-" in message:
            seekbar, angle = message.split("-")
        else:
            return
    except Exception as e:
        return
    # 100: forward 0: backward 50: neutral
    # 160: go right 20: go left 90: neutral
    pin8 = machine.Pin(8, machine.Pin.OUT)  # Pin 8 als Ausgang setzen
    pin13 = machine.Pin(13, machine.Pin.OUT)  # Pin 8 als Ausgang setzen
    if int(seekbar) > 70:
        pin13.value(1)
        pin8.value(1)  # Pin 8 auf HIGH (3.3V)    else:
    else:
        pin13.value(0)
        pin8.value(0)
     

