import socket
import select

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server_socket.bind(('localhost', 8080))
server_socket.listen(5)

sockets_list = [server_socket]
clients = {}
while True:
    read_sockets, _, exception_sockets = select.select(sockets_list, [], sockets_list)
    for notified_socket in read_sockets:
        if notified_socket == server_socket:
            client_socket, client_address = server_socket.accept()
            sockets_list.append(client_socket)
            clients[client_socket] = client_address
            print(f'Подключился клиент по адресу {client_address[0]}:{client_address[1]}')
        else:
            message = notified_socket.recv(1024)
            if len(message) == 0:
                sockets_list.remove(notified_socket)
                del clients[notified_socket]
                continue
            client_address = clients[notified_socket]
            print(f'Получено сообщение от клиента по адресу {client_address[0]}:{client_address[1]} - {message.decode()}')
    for notified_socket in exception_sockets:
        sockets_list.remove(notified_socket)
        del clients[notified_socket]
