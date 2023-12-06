import car
engine = car.Engine(weight=200, max_speed=200, fuel_consumption=10)
tank = car.Tank(weight=100, tank_volume=50)
brakes = car.Brakes(weight=50, brake_efficiency=0.7)
body = car.Body(weight=500)

my_car = car.Car(name="MyCar", engine=engine, tank=tank, brakes=brakes, body=body)

print("Car Name:", my_car.name)
print("Total Weight:", my_car.weight)
print("Max Range per Tank:", my_car.max_range_per_tank)
print("Braking Distance:", my_car.braking_distance)
