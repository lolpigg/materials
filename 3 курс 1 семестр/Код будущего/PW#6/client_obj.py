import socket
import pickle
from car import Car
car = Car("Toyota", "Camry", 2022)
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(('localhost', 8080))
data = pickle.dumps(car)
client_socket.sendall(data)
client_socket.close()
