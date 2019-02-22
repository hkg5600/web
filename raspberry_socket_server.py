import socket
 
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #소켓 객체 생성
server_socket.bind(('', 1212)) #ip주소와 포트 번호

while True:
    server_socket.listen(1) #대기열 한자리를 뜻함
    print('waiting') #기다리는 동안 뜰 메세지
    client_socket, addr = server_socket.accept() #대기를 풀어주고 접속을 허용함 cli~~는 연결고리이고 addr은 연결된 것의 정보이다
    print('connected by', addr)         
    data = client_socket.recv(1024) #데이터를 받을 때 쓰는 코드
    #print(data) 출력
    msg = bytearray('nice to meet you!', 'utf-8') #메세지를 보내는데 바이트 형식이어야 한다
    client_socket.send(msg)     #메세지 전달                
    flag = 0 #오류로 인해 연결이 끊기면 1이 되면서 서버도 꺼진다 오류가 아닌 클라이언트가 프로그램을 종료했을 시에는 다시 서버가 클라이언트를 기다리는 곳부터 시작된다
    while True:
        try:
            data = client_socket.recv(1024).decode() #클라이언트로부터 받는 값
            print(data) #아래부터 프로토콜과 특정 프로토콜을 받을 시 수행하는 행동
            if data == "10": #10을 받을 경우 앞으로 간다
                print("go")
                
            if data == "20": #20을 받을 경우 뒤로 간다
                print("back")
                
            if data == "30": #30을 받을 경우 왼쪽으로 바퀴를 돌린다
                print("left")
                
            if data == "40": #40을 받을 경우 오른쪽으로 바퀴를 돌린다
                print("right")
            if data == "50": #50을 받을 경우 멈춘다 
                print("stop")
                
            if data == "100": #클라이언트를 종료하면 100이 오고 서버는 다시 클라이언트를 기다린다
                print("연결이 끊겼습니다\n")
                break
        except Exception as ex:
            print(ex) #예상치 못한 에러(클라이언트 프로그램이 갑자기 종료됨)가 발생했을 시 뜨는 경고창 
            flag = 1; #flag가 1이 되면서 프로그램이 종료된다
            break
    if flag == 1:
        break
client_socket.close() #사용한 소켓은 닫아줘야 한다
