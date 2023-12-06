import socket
import pickle

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(('localhost', 8080))
server_socket.listen(1)
print("Сервер запущен.")

while True:
    conn, addr = server_socket.accept()
    print(f"Подключился клиент по адресу {addr}")
    data = conn.recv(4096)
    car = pickle.loads(data)
    print("Получена машина:")
    print(car.display_info())
    car.change_name("ABOBA")
    print("Машина с измененной моделью:")
    print(f"{car.display_info()}")
    conn.close()
