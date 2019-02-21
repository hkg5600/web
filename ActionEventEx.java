package user_interface;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Robot;

public class ActionEventEx extends Frame implements ActionListener,WindowListener {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
		
	Panel p;
	Button input, exit, go, back, left, right, stop;
	TextArea ta;
	ServerUI SU = new ServerUI();
	
	public ActionEventEx() {
      
      super("ActionEnvet Test");
      p=new Panel();
      go = new Button("go");
      back = new Button("back");
      left = new Button("left");
      right = new Button("right");
      stop = new Button("stop");
      exit=new Button("exit");
      ta=new TextArea();
      
      go.addActionListener(this);
      back.addActionListener(this);
      left.addActionListener(this);
      right.addActionListener(this);   // 리스너 연결
      stop.addActionListener(this);
      exit.addActionListener(this);
      addWindowListener(this);
      
      p.add(go);
      p.add(back);
      p.add(left);
      p.add(right);
      p.add(stop);
      p.add(exit);

      add(p, BorderLayout.SOUTH);
      add(ta, BorderLayout.CENTER);

      setBounds(500,500,500,400);
      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent ae) {
      String name;
      name=ae.getActionCommand();
      
      //프로토콜
      //go : 10, back : 20, left : 30, right : 40, stop : 50, disconnect : 100
      if(name.equals("go")) {
    	  ta.append("go\n");
    	  SU.sendServer("10");
      }
      else if(name.equals("back")) {
    	  ta.append("back\n");
    	  SU.sendServer("20");
      }
      else if(name.equals("left")) {
    	  ta.append("left\n");
    	  SU.sendServer("30");
      }
      else if(name.equals("right")) {
    	  ta.append("right\n");
    	  SU.sendServer("40");
      }
      else if(name.equals("stop")) {
    	  ta.append("stop\n");
    	  SU.sendServer("50");
      }
      else
      {
    	 ta.setText(null);
         ta.append("프로그램을 종료합니다.\n");
         SU.sendServer("100");
         try {   // 의무적으로 예외처리
            Thread.sleep(2000);
         }catch(Exception e) {}
         
         System.exit(0);
      }
   }

   public static void main(String[] args) {

      new ActionEventEx();
   }
   
	@Override
	public void windowActivated(WindowEvent e) {
		// 윈도우가 액티브 윈도우로 설정될 때(화면 맨 위로 올 때)
		ta.setText(null);//모든 텍스트를 지울 때 사용
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		//윈도우가 닫히면 호출
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// 윈도우를 닫으려 할 때
		ta.append("bye bye~");
		System.exit(0);
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		// 윈도우가 액티브 윈도우가 아닌 경우
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		// 윈도우가 최소화 상태에서 정상 상태로 변경되면 호출
		
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		// 윈도우가 보통상태에서 최소화 상태로 변경 될 때 호출
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// 처음 표시 할 때
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int cnt = 0;
		while(true) {
			ta.append("conneting to server...\n");
			int flag = SU.first_con();
			if (flag == 1) {
				ta.setText(null);
				ta.append("conneted to server\n");
				break;
			}
			else {
				ta.append("connect failed...try again\n\n");
				robot.delay(2000);
				cnt++;
			}
			if (cnt >= 10) {
				System.out.println("강제 종료");
				robot.delay(2000);
				System.exit(0);
			}
				
		}
	}

}