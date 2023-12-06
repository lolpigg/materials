import math

def calculate_discriminant(a, b, c):
    discriminant = b ** 2 - 4 * a * c
    return discriminant

def calculate_roots(a, b, c):
    discriminant = calculate_discriminant(a, b, c)
    if discriminant > 0:
        x1 = (-b + math.sqrt(discriminant)) / (2 * a)
        x2 = (-b - math.sqrt(discriminant)) / (2 * a)
        return x1, x2
    elif discriminant == 0:
        x = -b / (2 * a)
        return x
    else:
        return "Нет корней"

def calculate_probability_of_joint_events(event1, event2):
    joint_probability = event1 + event2 - event1*event2
    return joint_probability

def calculate_probability_of_independent_events(event1, event2):
    independent_probability = event1 + event2
    return independent_probability