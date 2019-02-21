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
	
	Socket c_socket = null;
	
	public int first_con() {
		try{
			String data = "Hi";
			c_socket = new Socket("localhost", 1212);
			//ta.append("connected!");
			OutputStream output_data = c_socket.getOutputStream();
			output_data.write(data.getBytes());
			InputStream input_data = c_socket.getInputStream();
			byte[] receiveBuffer = new byte[100];
			input_data.read(receiveBuffer);
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
	public void sendServer(String data) {
		try {
		OutputStream output_data = c_socket.getOutputStream();
		output_data.write(data.getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
