package characters;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Scenes.GameScene;
import Scenes.Stage1;
import Timer.CustomTimer;
import item.Gun;
import item.Item;
import item.RecoveryPotion;

public class Player1 implements Runnable {
	private int hp = 200;
	private int stamina = 200;
	private int x,y;
	//캐릭터 사이즈
	int cSizeX = 32;
	int cSizeY = 32;
	//맵의 벽을 제외한 왼쪽 끝 좌표
	int leftTopX =-11;
	int leftTopY = 31;
	//사이즈
	private int cWidth = 32;
	private int cHeight = 32;
	//이동속도
	private int speed = 1;
	private int speedBooster = 2;
	//맵의 타일과 연동
	private char[][] xy = new char[22][40];
	//타일의 사이즈
	private int sizeX = 32;
	private int sizeY = 32;
	private JLabel character = new JLabel(new ImageIcon("ch1/ch1_default_right.png"));
	//메인 게임화면 객체 주소 저장
	private GameScene gs;
	//추가할 곳
	public Stage1 st1;
	//스레드 변수 생성
	private Thread th1;
	private boolean th1Flag = true;
	private PlayerRun pr;
	private Thread th2;
	private boolean th2Flag = true;
	//움직임 플래그
	private boolean flagUp = false;
	private boolean flagDown = false;
	private boolean flagLeft = false;
	private boolean flagRight = false;
	private boolean flagBoost = false;
	private String leftOrRight = "right";
	private String upOrDown = "down";
	//체력바와 스테미너
	private JPanel infoGui = new JPanel();
		//체력바
		private JPanel currentHpBar = new JPanel();
		private JLabel currentHp = new JLabel("HP");
		private JLabel currentHpNum = new JLabel(hp+"");
		//스테미너
		private JPanel currentStaminaBar = new JPanel();
		private JLabel currentStamina = new JLabel("체력");
	//상호작용
	private boolean fire;
	private boolean rFlag;
	//소지 가능한 모든 아이템
	private Item[] item = new Item[5];
	//현재 아이템
	private Item currentItem;
	//총
	private Gun gun;
	//아이템 사용
	private RecoveryPotion rp;
	private boolean itemUse = false;
	//방향
	private boolean num1 = false;
	private boolean num2 = false;
	private boolean num3 = false;
	private boolean num4 = false;
	private boolean num5 = false;
	//시간 간격
	private CustomTimer tm = new CustomTimer();
	private Thread thTime;
	
	public Player1(int x, int y, Stage1 st1) {
		this.x = x;
		this.y = y;
		this.st1 = st1;
		this.xy = st1.getXy();
		gun = new Gun(this, st1);
		rp = new RecoveryPotion(this, st1);
		
		main();
	}
	private void main() {
		character.setBounds(x,y,cWidth,cHeight);
		st1.add(character, new Integer(1), -1);
		st1.add(infoGui, new Integer(1),-1);
		infoGui.setLayout(null);
		infoGui.setBounds(5, 765, 290, 100);
		infoGui.setBackground(Color.darkGray);
		infoGui.add(currentHp);
		currentHp.setBounds(14,12,50,30);
		currentHp.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		currentHp.setForeground(Color.red);
		infoGui.add(currentHpBar);
		currentHpBar.setBackground(Color.red);
		currentHpBar.setBounds(50,20,hp,20);
		currentHpBar.add(currentHpNum);
		currentHpNum.setBackground(Color.white);
		currentHpNum.setBounds(hp/2,0,30,20);
		infoGui.add(currentStaminaBar);
		currentStaminaBar.setBounds(50,50,stamina,20);
		currentStaminaBar.setBackground(Color.blue);
		infoGui.add(currentStamina);
		currentStamina.setBounds(10,44,50,30);
		currentStamina.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		currentStamina.setForeground(Color.blue);
		//스레드 생성 및 실행
		th1 = new Thread(this);
		th1.start();
		
		pr = new PlayerRun(this);
		th2 = new Thread(pr);
		th2.start();
		
		thTime = new Thread(tm);
		tm.setTh(thTime);
		thTime.start();
	}
	private void move() {
		int cnt = 0;
		//이동 관련 좌표 저장
		int mx = x;
		int my = y;
		//문제가 있을시 이전 위치로 다시 바꾸기 위한 변수들
		int xx = x; 
		int yy = y;
		//캐릭터 꼭짓점의 인덱스 위치
		int[] c4IndexX = new int[4];
		int[] c4IndexY = new int[4];
		
		if(flagUp) {
			if(flagBoost && stamina > 0)
				my -= (speed+speedBooster);
			else
				my -= speed;
		}
		if(flagDown) {
			if(flagBoost && stamina > 0)
				my += (speed+speedBooster);
			else
				my += speed;					
		}
		if(flagLeft) {
			if(flagBoost && stamina > 0)
				mx -= (speed+speedBooster);
			else
				mx -= speed;
		}
		if(flagRight) {
			if(flagBoost && stamina > 0)
				mx += (speed+speedBooster);
			else
				mx += speed;
		}
		
		c4IndexX[0] = (mx - leftTopX)/sizeX;
		c4IndexY[0] = (my - leftTopY)/sizeY;
		c4IndexX[1] = (mx - leftTopX+18)/sizeX;
		c4IndexY[1] = (my - leftTopY)/sizeY;
		c4IndexX[2] = (mx - leftTopX)/sizeX;
		c4IndexY[2] = (my - leftTopY+18)/sizeY;
		c4IndexX[3] = (mx - leftTopX+18)/sizeX;
		c4IndexY[3] = (my - leftTopY+18)/sizeY;
		
		for(int i = 0; i < c4IndexX.length; i++) {
			if(xy[c4IndexY[i]][c4IndexX[i]] == 'A') {
				cnt++;					
			}
		}	
		if(cnt == 4) {
			x = mx;
			y = my;			
		}else if((xy[(y-leftTopY)/sizeY][c4IndexX[0]] == 'A' 
				&& xy[(y-leftTopY+18)/sizeY][c4IndexX[0]] == 'A')
				&& (xy[(y-leftTopY)/sizeY][c4IndexX[1]] == 'A' 
				&& xy[(y-leftTopY+18)/sizeY][c4IndexX[1]] == 'A')) {
			x = mx;
		}else if((xy[c4IndexY[0]][(x-leftTopX)/sizeX] == 'A' 
				&& xy[c4IndexY[0]][(x-leftTopX+18)/sizeX] == 'A')
				&& (xy[c4IndexY[1]][(x-leftTopX)/sizeX] == 'A'
				&& xy[c4IndexY[1]][(x-leftTopX+18)/sizeX] == 'A')) {
			y = my;
		}
//		System.out.println("cIndexX : "+cIndexX+"  cIndexY : "+cIndexY);
		
		c4IndexX[0] = (x - leftTopX)/sizeX;
		c4IndexY[0] = (y - leftTopY)/sizeY;
		c4IndexX[1] = (x - leftTopX+18)/sizeX;
		c4IndexY[1] = (y - leftTopY)/sizeX;
		c4IndexX[2] = (x - leftTopX)/sizeX;
		c4IndexY[2] = (y - leftTopY+18)/sizeX;
		c4IndexX[3] = (x - leftTopX+18)/sizeX;
		c4IndexY[3] = (y - leftTopY+18)/sizeX;
		
		cnt = 0;
		
		for(int i = 0; i < c4IndexX.length; i++) {
			if(xy[c4IndexY[i]][c4IndexX[i]] == 'A') {
				cnt++;					
			}
		}	
		if(cnt != 4) {	
			x = xx;
			y = yy;			
		}
		
		if(flagRight && flagUp) {
			upOrDown = "up";
			leftOrRight = "right";
		}else if(flagRight && flagDown) {
			upOrDown = "down";
			leftOrRight = "right";
		}else if(flagLeft && flagUp) {
			upOrDown = "up";
			leftOrRight = "left";
		}else if(flagLeft && flagDown) {
			upOrDown = "down";
			leftOrRight = "left";
		}else if(flagUp) {
			upOrDown = "up";
		}else if(flagDown) {
			upOrDown = "down";
		}else if(flagRight) {
			leftOrRight = "right";
		}else if(flagLeft) {
			leftOrRight = "left";
		}
		if(flagUp || flagDown || flagRight || flagLeft) {
			if((flagUp && flagRight) || (flagUp && flagLeft) || flagUp) {
				if(flagBoost) {
					character.setIcon(new ImageIcon("ch1/ch1_run_up.gif"));
				}else {
					character.setIcon(new ImageIcon("ch1/ch1_walk_up.gif"));
				}
				upOrDown = "up";
			}else if((flagDown && flagRight) || flagRight || (flagDown && leftOrRight == "right")) {
				if(flagBoost) {
					character.setIcon(new ImageIcon("ch1/ch1_run_right.gif"));
				}else {
					character.setIcon(new ImageIcon("ch1/ch1_walk_right.gif"));
				}
				upOrDown = "down";
			}else if((flagDown && flagLeft) || flagLeft || (flagDown && leftOrRight == "left")) {
				if(flagBoost) {
					character.setIcon(new ImageIcon("ch1/ch1_run_left.gif"));
				}else {
					character.setIcon(new ImageIcon("ch1/ch1_walk_left.gif"));
				}
				upOrDown = "down";
			}
		}else {
			if(upOrDown.equals("up"))
				character.setIcon(new ImageIcon("ch1/ch1_default_up.png"));
			else if(leftOrRight.equals("right"))
				character.setIcon(new ImageIcon("ch1/ch1_default_right.png"));
			else if(leftOrRight.equals("left"))
				character.setIcon(new ImageIcon("ch1/ch1_default_left.png"));
		}
			
//		System.out.println("cIndexX : "+cIndexX+"  cIndexY : "+cIndexY);
		character.setLocation(x,y);
		gun.getGunImage().setLocation(x, y);
		rp.getImg().setLocation(x,y);
		
		if(num1)
			gun.getGunImage().setVisible(true);
		else
			gun.getGunImage().setVisible(false);
		
		if(num3)
			rp.getImg().setVisible(true);
		else
			rp.getImg().setVisible(false);
	}
	@Override
	public void run() {
		try {
			while(th1Flag) {		
				move();
//				System.out.println("X좌표 : "+x+"  Y좌표 : "+y);
				gun.setFlags(flagUp, flagDown, flagRight, flagLeft);
				gun.setGun(fire, rFlag);
				rp.setFlags(flagUp, flagDown, flagRight, flagLeft);
				rp.setItemUse(itemUse);
				rp.setNum3(num3);
				th1.sleep(15);
			}
			if(th1Flag == false) {
				pr.setTh2Flag(false);
				gun.setThFlag(false);
				tm.setThFlag(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//setter와 getter
	public void setStaminaManager() {
		if(stamina < 200)
			stamina += 3;
		if(stamina < -10)
			stamina = -8;
		currentStaminaBar.setSize(stamina,20);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void sethp(int dmg) {
		hp -= dmg;
		if(hp > 200)
			hp = 200;
		currentHpBar.setSize(hp,20);
		currentHpNum.setText(hp+"");;
	}
	public int getHp() {
		return hp;
	}
	public boolean getFlagBoost() {
		return flagBoost;
	}
	public void setStamina() {
		stamina -= 7;
		currentStaminaBar.setSize(stamina,20);
	}
	public void setThreadSleep() {
		try {
			th2.sleep(100);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setFlags(boolean flagUp, boolean flagDown, boolean flagRight, boolean flagLeft, boolean flagBoost) {
		this.flagUp = flagUp;   
		this.flagDown = flagDown; 
		this.flagLeft = flagLeft; 
		this.flagRight = flagRight;
		this.flagBoost = flagBoost;
		
		pr.setFlagBoost(flagBoost);
	}
	public void setTh1Flag(boolean th1Flag) {
		this.th1Flag = th1Flag; 
	}
	public PlayerRun getPr() {
		return pr;
	}
	public void setGun(boolean fire, boolean rFlag) {
		this.fire = fire;
		this.rFlag = rFlag;
	}
	public void setItemUse(boolean itemUse) {
		this.itemUse = itemUse;
	}
	public void setItemHighlightFLags(boolean num1,boolean num2,boolean 
			num3,boolean num4,boolean num5) {
		this.num1 = num1;  
		this.num2 = num2;  
		this.num3 = num3;  
		this.num4 = num4;  
		this.num5 = num5;  
	}
	public CustomTimer getThTime() {
		return tm;
	}
}
