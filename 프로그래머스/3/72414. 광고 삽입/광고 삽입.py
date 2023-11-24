def convert_to_seconds(time):
    h, m, s = map(int, time.split(":"))
    return h * 3600 + m * 60 + s

def add(times, start, end, play_time):
    times[start] += 1
    if end < play_time:
        times[end] -= 1

def convert_to_time(seconds):
    h = seconds // 3600
    m = (seconds % 3600) // 60
    s = seconds % 60
    return f"{h:02d}:{m:02d}:{s:02d}"

def solution(play_time, adv_time, logs):
    play_time = convert_to_seconds(play_time)
    times = [0] * (play_time + 1)
    adv_time = convert_to_seconds(adv_time)

    for log in logs:
        start, end = map(convert_to_seconds, log.split("-"))
        add(times, start, end, play_time)

    for i in range(1, play_time):
        times[i] += times[i - 1]

    sum_time = sum(times[:adv_time])
    max_time = sum_time
    max_index = 0

    for i in range(1, play_time - adv_time + 1):
        sum_time += times[i + adv_time - 1] - times[i - 1]
        if sum_time > max_time:
            max_time = sum_time
            max_index = i

    return convert_to_time(max_index)



