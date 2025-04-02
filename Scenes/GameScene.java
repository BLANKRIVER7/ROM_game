package Scenes;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import characters.Player1;

public class GameScene extends JFrame implements KeyListener, Runnable {
	//창 크기 및 위치
	private int width = 1290;
	private int height = 900;
	private int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width)/2;
	private int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height)/2;
	//맵의 벽을 제외한 왼쪽 끝 좌표
	int leftTopX = 21;
	int leftTopY = 63;
	//인젝트된 객체의 주소
	private MainMenuScene mms = null;
	private Player1 p1;
	//스레드 생성
	private Thread th;
	private boolean th1Flag = true;
	//현재 스테이지
	private Stage currentStage;
	//이동 플래그
	private boolean flagUp = false;
	private boolean flagDown = false;
	private boolean flagLeft = false;
	private boolean flagRight = false;
	private boolean flagBoost = false;
	//아이템 선택박스 하이라이트 플래그
	private boolean num1 = true;
	private boolean num2 = false;
	private boolean num3 = false;
	private boolean num4 = false;
	private boolean num5 = false;
	//총 발사 및 장전
	private boolean fire = false;
	private boolean rFlag = false;
	//회복 물약사용
	private boolean itemUse = false;
	//배경 선택
	private int backNum;
	//타이틀 아이콘
	Image titleIcon = new ImageIcon("backgrounds/mainIcon.png").getImage();
	
	public void mainScene(MainMenuScene mms, int backNum) {
		this.backNum = backNum;
		this.mms = mms;
		this.setBounds(x, y, width, height);	
		
		this.setTitle("ROM");
		this.setIconImage(titleIcon);
	    this.setLayout(null);
	    this.setResizable(false);
	    currentStage = new Stage1(mms, this, backNum);
	    this.add(currentStage);
	    p1 = currentStage.getPlayer1();

		this.setFocusable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		addKeyListener(this);
		
		//스레드 시작
		th = new Thread(this);
		th.start();
	}
	public void mainScene() {
		currentStage.setVisible(false);
		this.remove(currentStage);
	    currentStage = new Stage1(mms, this, backNum);
	    this.add(currentStage);
	    p1 = currentStage.getPlayer1();
	    this.setVisible(true);
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void run() {
		try {
			while(th1Flag) {
				if(currentStage.getTh1Flag()) {
					currentStage.setMoveFLags(flagUp,flagDown,flagRight,flagLeft,flagBoost);
					currentStage.setItemHighlightFLags(num1,num2,num3,num4,num5);
					p1.setItemHighlightFLags(num1,num2,num3,num4,num5);
					p1.setGun(fire, rFlag);
					p1.setItemUse(itemUse);		
				}
				th.sleep(10);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			flagBoost = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			flagUp = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			flagDown = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			flagRight = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			flagLeft = true;
		}else if(e.getKeyCode() == KeyEvent.VK_1) {
			num1 = true;
			num2 = false;
			num3 = false;
			num4 = false;
			num5 = false;
		}else if(e.getKeyCode() == KeyEvent.VK_2) {
			num1 = false;
			num2 = true;
			num3 = false;
			num4 = false;
			num5 = false;
		}else if(e.getKeyCode() == KeyEvent.VK_3) {
			num1 = false;
			num2 = false;
			num3 = true;
			num4 = false;
			num5 = false;
		}else if(e.getKeyCode() == KeyEvent.VK_4) {
			num1 = false;
			num2 = false;
			num3 = false;
			num4 = true;
			num5 = false;
		}else if(e.getKeyCode() == KeyEvent.VK_5) {
			num1 = false;
			num2 = false;
			num3 = false;
			num4 = false;
			num5 = true;
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE && num1) {
			fire = true;
		}else if(e.getKeyCode() == KeyEvent.VK_R && num1){
			rFlag = true;
		}else if(e.getKeyCode() == KeyEvent.VK_E && (num2 || num3 || num4 || num5)) {
			itemUse = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			flagBoost = false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			flagUp = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			flagDown = false;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			flagRight = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			flagLeft = false;
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			fire = false;
		}else if(e.getKeyCode() == KeyEvent.VK_R){
			rFlag = false;
		}else if(e.getKeyCode() == KeyEvent.VK_E) {
			itemUse = false;
		}
	}
	//setter와 getter
	public void setTh1Flag(boolean th1Flag) {
		this.th1Flag = th1Flag;
	}
	public boolean getFlagBoost() {
		return flagBoost;
	}
	public Thread getTh() {
		return th;
	}
}
