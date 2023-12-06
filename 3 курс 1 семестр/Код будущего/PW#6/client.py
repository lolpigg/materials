import socket
client_socket1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket1.connect(('localhost', 8080))
client_socket1.send("Первый клиент".encode())
client_socket2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket2.connect(('localhost', 8080))
client_socket2.send("Второй клиент".encode())
