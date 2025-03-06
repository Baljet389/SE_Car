import re


def SplitMessage(message):
    try:
        if "-" in message:
            seekbar, angle = message.split("-")
        else:
            return
    except Exception as e:
        return
    print("Seekbar"+seekbar)  # 100: forward 0: backward 50: neutral
    print("Angle"+angle)  # 160: go right 20: go left 90: neutral

