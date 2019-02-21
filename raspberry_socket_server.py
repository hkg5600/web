import socket
 
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(('', 1212))
server_socket.listen(1)
print('waiting')
client_socket, addr = server_socket.accept()
print('connected by', addr)
data = client_socket.recv(1024)
#print(data)
msg = bytearray('nice to meet you!', 'utf-8')
client_socket.send(msg)
while True:
    try:
        data = client_socket.recv(1024).decode()
        print(data)
        if data == "10":
            print("go")
        if data == "20":
            print("back")
        if data == "30":
            print("left")
        if data == "40":
            print("right")
        if data == "50"
            print("stop")
        if data == "100":
            print("프로그램을 종료합니다")
            break
    except Exception as ex:
        print(ex)
        break
        
client_socket.close()
