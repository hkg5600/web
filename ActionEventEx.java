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
//				extends는 원하는 걸 가져다 쓰는 것이고 implements는 다 가져온후 자신에게 맞게 다듬어 쓴는 것
public class ActionEventEx extends Frame implements ActionListener,WindowListener {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;	//왠지는 모르지만 얘를 추가하지 않으면 워닝
		
	Panel p; //판넬
	Button input, exit, go, back, left, right, stop; //버튼
	TextArea ta; //텍스트 아리아
	ServerUI SU = new ServerUI(); //서버와의 연결에 사용될 클래스 객체
	
	public ActionEventEx() {//생성자
      
      super("User Interface"); //상단에 뜰 메세지
      p=new Panel(); //판넬 객체
      go = new Button("go"); //버튼들 객체
      back = new Button("back");
      left = new Button("left");
      right = new Button("right");
      stop = new Button("stop");
      exit=new Button("exit");
      ta=new TextArea();
      
      go.addActionListener(this); //액션리스너에 추가하는 작업
      back.addActionListener(this);
      left.addActionListener(this);
      right.addActionListener(this);   // 리스너 연결
      stop.addActionListener(this);
      exit.addActionListener(this);
      addWindowListener(this); //윈도우를 다룰 때 쓰는 리스너
      
      p.add(go); //판넬에 추가하는 작업
      p.add(back);
      p.add(left);
      p.add(right);
      p.add(stop);
      p.add(exit);

      add(p, BorderLayout.SOUTH); //남쪽(아래)에 배치
      add(ta, BorderLayout.CENTER); //텍스트 아리아의 위치

      setBounds(500,500,500,400);//처음 창이 열릴 위치와 창의 크기
      setVisible(true); //창을 보이게 하는 것이다
   }
   //액션리스너에서 사용할 수 있는 것들
   @Override
   public void actionPerformed(ActionEvent ae) {
      String name;
      name=ae.getActionCommand(); //어떤 행동을 했는지 기록하여 저장
      
      //프로토콜
      //go : 10, back : 20, left : 30, right : 40, stop : 50, disconnect : 100
      if(name.equals("go")) {
    	  ta.append("go\n");
    	  SU.sendServer("10"); //10이란 값을 서버에 전송하기 위한 작업
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
   //윈도우 리스너에서 사용할 수 있는 것들
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
		Robot robot = null; //딜레이를 위한 객체
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		int cnt = 0; // 연결에 연속적으로 실패할 때마다 증가됨 cnt가 10이상이 되면 프로그램 강제 종료
		while(true) {
			ta.append("conneting to server...\n");
			int flag = SU.first_con(); //서버와의 연결시도
			if (flag == 1) { //성공
				ta.setText(null);
				ta.append("conneted to server\n");
				break;
			}
			else { //실패후 2초 딜레이 cnt증가
				ta.append("connect failed...try again\n\n");
				robot.delay(2000);
				cnt++;
			}
			if (cnt >= 10) { //cnt가 10 이상이면 강제 종료
				System.out.println("강제 종료");
				robot.delay(2000);
				System.exit(0);
			}
				
		}
	}

}
