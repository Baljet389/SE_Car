import re
from machine import Pin, PWM

def SplitMessage(message):
    try:
        if "-" in message:
            seekbar, angle = message.split("-")
        else:
            return
    except Exception as e:
        return
    print("Seekbar"+seekbar)  # 100: forward 0: backward 50: neutral
    if seekbar >70:
        pwm0 = PWM(Pin(0), freq=2000, duty_u16=32768)
    if seekbar <70:
        pwm1 = PWM(Pin(1), freq=0, duty_u16 = 32768)
        
    print("Angle"+angle)  # 160: go right 20: go left 90: neutral

