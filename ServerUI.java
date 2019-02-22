package user_interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerUI {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	Socket c_socket = null; //소켓 객체 생성
	
	public int first_con() { //처음 소켓 연결에 사용할 메소드 반환값은 성공여부이다
		try{
			String data = "Hi"; //소켓에 보낼 메세지
			c_socket = new Socket("localhost", 1212); //ip주소와 포트번호
			//ta.append("connected!");
			OutputStream output_data = c_socket.getOutputStream(); //데이터 전송에 사용할 객체
			output_data.write(data.getBytes()); //데이터 전송 data변수에 저장된다
			InputStream input_data = c_socket.getInputStream(); //데이터 수신에 사용할 객체
			byte[] receiveBuffer = new byte[100]; //바이트 형식으로 리시브버퍼 생성
			input_data.read(receiveBuffer); //리시브버퍼에 데이터를 받는다
			System.out.println("message from server : " + new String(receiveBuffer));
			if (receiveBuffer != null) {
				return 1;
			}
				//ta.append("message from server : " + new String(receiveBuffer));
		} catch(IOException e) {
			e.printStackTrace();
		
		}
		return 0;
	}
	public void sendServer(String data) { //연결이 성공적으로 이루어진 후에 데이터 전송에 사용될 메소드 
		try {							  //인자 값은 전송될 데이터
		OutputStream output_data = c_socket.getOutputStream(); //전송 객체 생성
		output_data.write(data.getBytes()); //전송
		} catch(IOException e) {
			e.printStackTrace(); //에러 출력
		}
	}

}
