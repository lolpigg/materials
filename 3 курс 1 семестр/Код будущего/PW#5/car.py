class Engine:
    def __init__(self, weight, max_speed, fuel_consumption):
        self.weight = weight
        self.max_speed = max_speed
        self.fuel_consumption = fuel_consumption

class Tank:
    def __init__(self, weight, tank_volume):
        self.weight = weight
        self.tank_volume = tank_volume

class Brakes:
    def __init__(self, weight, brake_efficiency):
        self.weight = weight
        self.brake_efficiency = brake_efficiency

class Body:
    def __init__(self, weight):
        self.weight = weight

class Car:
    def __init__(self, name, engine, tank, brakes, body):
        self.name = name
        self.engine = engine
        self.tank = tank
        self.brakes = brakes
        self.body = body

    @property
    def weight(self):
        return self.engine.weight + self.tank.weight + self.brakes.weight + self.body.weight

    @property
    def max_range_per_tank(self):
        return (self.engine.max_speed * self.tank.tank_volume) / self.engine.fuel_consumption

    @property
    def braking_distance(self):
        return self.weight * self.brakes.brake_efficiency
