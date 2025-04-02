package Monster;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Scenes.Stage;
import Scenes.Stage1;
import characters.Player1;

public abstract class Monster implements Runnable{
	private int hp = 30;
	private int beforeHp = 30;
	private int dmg;
	private int x;
	private int y;
	private int width = 18;
	private int height = 18;
	private int speed = 1;
	//몬스터 이미지
	private JLabel img;
	private ImageIcon imgLeft;
	private ImageIcon imgRight;
	private ImageIcon imgLeftMove;
	private ImageIcon imgRightMove;
	//스레드 생성
	private Thread th;
	private boolean th1Flag = true;
	//추가할 곳
	private Stage1 st1;
	//맵의 벽을 제외한 왼쪽 끝 좌표
	private int leftTopX =-11;
	private int leftTopY = 31;
	//맵의 타일과 연동
	private char[][] xy = new char[22][40];
	//타일의 사이즈
	private int sizeX = 32;
	private int sizeY = 32;
	//Player1 객체 주소
	private Player1 p1;
	//몬스터의 체력바
	private JPanel currentHpBar = new JPanel();
	//이전 방향 저장
	private String leftOrRight = "";
	//몬스터끼리 겹치지 않도록
	private ArrayList<Monster> bmList = new ArrayList<>();

	protected void creator() {
		img = new JLabel(imgRight);
		img.setBounds(x,y,width,height);
		st1.add(img, new Integer(2), -1);
		st1.add(currentHpBar, new Integer(2), -1);
		currentHpBar.setBackground(Color.green);
		currentHpBar.setBounds(x+2, y, hp, 5);
		//스레드 시작
		th = new Thread(this);
		th.start();
	}
	@Override
	public void run() {		
		try {
			while(th1Flag) {
				setMList();
				move();
				if(hp < 20)
					currentHpBar.setBackground(Color.yellow);
				if(hp < 10)
					currentHpBar.setBackground(Color.red);
				currentHpBar.setBounds(x+2, y, hp, 5);
				th.sleep(30);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void move() {
		int cnt = 0;
		//이동 관련 좌표 저장
		int mx = x;
		int my = y;
		//문제가 있을시 이전 위치로 다시 바꾸기 위한 변수들
		int xx = x;
		int yy = y;
		int xDistance = 0;
		int yDistance = 0;
		int xDistance2 = 0;
		int yDistance2 = 0;
		boolean flagX = true;
		boolean flagY = true;
		//몬스터 꼭짓점의 인덱스 위치
		int[] m4IndexX = new int[4];
		int[] m4IndexY = new int[4];
		
		try {
			flagX = true;
			flagY = true;
			xDistance = p1.getX()-x;
			
			if(xDistance < 0)
				xDistance *= -1;
			else if(xDistance == 0) {
				flagX = false;
			}						
			
			yDistance = p1.getY()-y;
			
			if(yDistance < 0)
				yDistance *= -1;
			else if(yDistance == 0) {
				flagY = false;
			}
			if(xDistance >= 200 || yDistance >= 200) {
				if(leftOrRight.equals("right")) {
					img.setIcon(imgRight);
				}else if(leftOrRight.equals("left")) {
					img.setIcon(imgLeft);
				}
				return;
			}else if(xDistance <= 25 && yDistance <= 25) {
				if(leftOrRight.equals("right")) {
					img.setIcon(imgRight);
				}else if(leftOrRight.equals("left")) {
					img.setIcon(imgLeft);
				}
				return;
			}
			
			if(flagX) {
				if(xDistance < speed) {
					mx = p1.getX();
				}else {
					xDistance /= p1.getX()-x; 
					mx += xDistance*speed;					
				}
			}
			if(flagY) {
				if(yDistance < speed) {
					my = p1.getY();
				}else {
					yDistance /= p1.getY()-y;
					my += yDistance*speed;					
				}
			}
			
			m4IndexX[0] = (mx - leftTopX)/sizeX;
			m4IndexY[0] = (my - leftTopY)/sizeY;
			m4IndexX[1] = (mx - leftTopX+18)/sizeX;
			m4IndexY[1] = (my - leftTopY)/sizeY;
			m4IndexX[2] = (mx - leftTopX)/sizeX;
			m4IndexY[2] = (my - leftTopY+18)/sizeY;
			m4IndexX[3] = (mx - leftTopX+18)/sizeX;
			m4IndexY[3] = (my - leftTopY+18)/sizeY;

			for(int i = 0; i < m4IndexX.length; i++) {
				if(xy[m4IndexY[i]][m4IndexX[i]] == 'A') {
					cnt++;					
				}
			}	
			if(cnt == 4) {
				x = mx;
				y = my;
			}else if((xy[(y-leftTopY)/sizeY][m4IndexX[0]] == 'A' 
					&& xy[(y-leftTopY+18)/sizeY][m4IndexX[0]] == 'A')
					&& (xy[(y-leftTopY)/sizeY][m4IndexX[1]] == 'A' 
					&& xy[(y-leftTopY+18)/sizeY][m4IndexX[1]] == 'A')) {
				x = mx;
			}else if((xy[m4IndexY[0]][(x-leftTopX)/sizeX] == 'A' 
					&& xy[m4IndexY[0]][(x-leftTopX+18)/sizeX] == 'A')
					&& (xy[m4IndexY[1]][(x-leftTopX)/sizeX] == 'A'
					&& xy[m4IndexY[1]][(x-leftTopX+18)/sizeX] == 'A')) {
				y = my;
			}
			
			m4IndexX[0] = (x - leftTopX)/sizeX;
			m4IndexY[0] = (y - leftTopY)/sizeY;
			m4IndexX[1] = (x - leftTopX+18)/sizeX;
			m4IndexY[1] = (y - leftTopY)/sizeX;
			m4IndexX[2] = (x - leftTopX)/sizeX;
			m4IndexY[2] = (y - leftTopY+18)/sizeX;
			m4IndexX[3] = (x - leftTopX+18)/sizeX;
			m4IndexY[3] = (y - leftTopY+18)/sizeX;

			cnt = 0;
			
			if(my >= 63 && mx >= 21 && m4IndexX[3] < 38 && m4IndexY[3] < 20) {
				for(int i = 0; i < m4IndexX.length; i++) {
					if(xy[m4IndexY[i]][m4IndexX[i]] == 'A') {
						cnt++;					
					}
				}	
				if(cnt != 4) {
					x = xx;
					y = yy;	
				}
			}
			if(x-xx > 0) {
				img.setIcon(imgRightMove);
				leftOrRight = "right";
			}else if(x-xx < 0) {
				img.setIcon(imgLeftMove);
				leftOrRight = "left";				
			}else if((y-yy > 0 || y-yy < 0) && leftOrRight.equals("right")) {
				img.setIcon(imgRightMove);
			}else if((y-yy > 0 || y-yy < 0) && leftOrRight.equals("left")) {
				img.setIcon(imgLeftMove);
			} 
			
			img.setLocation(x, y);
			
			if(beforeHp != hp) {
				st1.setLayer(img, new Integer(2), 0);
				for(Monster m : bmList)
					if(m != this)
						st1.setLayer(m.getImg(), new Integer(2), -1);
			}
			
			beforeHp = hp;
		} catch (Exception e) {
//		e.printStackTrace();
		}
		
		
	}
	//setter와 getter
	public void setHp(int dmg) {
		this.hp -= dmg;
	}
	public int getHp() {
		return hp;
	}
	public void setTh1Flag(boolean flag) {
		this.th1Flag = flag;
	}
	public JPanel getCurrentHpBar() {
		return currentHpBar;
	}
	public JLabel getImg() {
		return img;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setJlp(Stage1 st1) {
		this.st1 = st1;
	}
	public void setP1(Player1 p1) {
		this.p1 = p1;
	}
	public void setXy(char[][] xy) {
		this.xy = xy;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	public int getDmg() {
		return dmg;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int gettWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setImg(String left, String right, String leftMove, String rightMove) {
		imgLeft = new ImageIcon(left);
		imgRight = new ImageIcon(right); 
		imgLeftMove = new ImageIcon(leftMove); 
		imgRightMove = new ImageIcon(rightMove); 
	}
	public void setMList() {
		this.bmList = st1.getMonsters();
	}
	public abstract BasicMonsterAttack getInstance();
}
