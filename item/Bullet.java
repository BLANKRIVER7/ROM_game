package item;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import Monster.Monster;
import Scenes.Stage1;
import boss.Boss;
import characters.Player1;

public class Bullet implements Runnable {
	private int x,y;
	private int dmg = 7;
	private int speed = 10;
	private JPanel bullet = new JPanel();
	private Thread th;
	private boolean thFlag  = true;
	//너비 및 높이
	private int width = 15;
	private int height = 5;
	//몬스터
	private ArrayList<Monster> bmList;
	//보스
	private ArrayList<Boss> bbList;
	//객체 인젝트
	private Gun gun;
	private Stage1 st1;
	//방향
	private boolean flagUp = false;
	private boolean flagDown = false;
	private boolean flagLeft = false;
	private boolean flagRight = false;
	//이동 방향
	private int plusX;
	private int plusY;
	int leftTopX =-11;
	int leftTopY = 31;
	private int sizeX = 32;
	private int sizeY = 32;
	//맵의 타일과 연동
	private char[][] xy = new char[22][40];
	private Player1 p1;
	private boolean skip = false;	
	
	public Bullet(Gun gun, Stage1 st1, Player1 p1) {
		this.gun = gun;
		this.st1 = st1;
		this.p1 = p1;
		this.xy = st1.getXy();
	}
	public void start(int plusX, int plusY) {
		this.plusX = plusX*speed;
		this.plusY = plusY*speed;
		skip = true;

		st1.add(bullet, new Integer(2), 0);
		bullet.setBackground(Color.red);

		if(flagLeft) {
			x = p1.getX()+7;
			y = p1.getY()+20;
		}else if(flagRight) {
			x = p1.getX()+25;
			y = p1.getY()+20;			
		}else if(flagUp) {
			x = p1.getX()+17;
			y = p1.getY()+20;
		}else if(flagDown) {
			x = p1.getX()+17;
			y = p1.getY()+20;
		}else if(gun.getDirection().equals("up")) {
			x = p1.getX()+17;
			y = p1.getY()+20;
		}else if(gun.getDirection().equals("down")) {
			x = p1.getX()+17;
			y = p1.getY()+20;
		}else if(gun.getDirection().equals("left")) {
			x = p1.getX()+7;
			y = p1.getY()+20;
		}else if(gun.getDirection().equals("right")) {
			x = p1.getX()+25;
			y = p1.getY()+20;
		}
		

		bullet.setBounds(x, y, 5, 3);
		
		//스레드 생성 및 시작
		th = new Thread(this);
		th.start();
		
	}
	@Override
	public void run() {
		int cnt = 0;
		//총알 꼭짓점의 인덱스 위치
		int[] b4IndexX = new int[4];
		int[] b4IndexY = new int[4];

		try {
			
			while(thFlag) {
					x += plusX;
					y += plusY;						
				
				bmList = st1.getMonsters();
				bbList = st1.getBosses();
				
				if(x < 21 || x > 1240 || y < 65 || y > 710) {
					bullet.setVisible(false);
					st1.remove(bullet);
					gun.deleteBullet(this);
					thFlag = false;
				}else if(x >= 445 && x <= 468 && y >= 389 && y <= 418) {
					bullet.setVisible(false);
					st1.remove(bullet);
					gun.deleteBullet(this);
					thFlag = false;
				}else if(x >= 789 && x <= 820 && y >= 520 && y <= 540) {
					bullet.setVisible(false);
					st1.remove(bullet);
					gun.deleteBullet(this);
					thFlag = false;
				}else if((p1.getX() - x) >= 200 || (p1.getX() - x) <= -200 
						|| (p1.getY() - y) >= 200 || (p1.getY() - y) <= -200) {
					bullet.setVisible(false);
					st1.remove(bullet);
					gun.deleteBullet(this);
					thFlag = false;
				}
				
				b4IndexX[0] = (x-leftTopX-3)/sizeX;
				b4IndexY[0] = (y-leftTopY-9)/sizeY;
				b4IndexX[1] = (x-leftTopX-3+3)/sizeX;
				b4IndexY[1] = (y-leftTopY-9)/sizeY;
				b4IndexX[2] = (x-leftTopX-3)/sizeX;
				b4IndexY[2] = (y-leftTopY-9+3)/sizeY;
				b4IndexX[3] = (x-leftTopX-3+3)/sizeX;
				b4IndexY[3] = (y-leftTopY-9+3)/sizeY;				
				
				for(int i = 0; i < 4; i++) {
					if(xy[b4IndexY[i]][b4IndexX[i]] == 'A')
						cnt++;
				}
				if(cnt != 4) {
					bullet.setVisible(false);
					st1.remove(bullet);
					gun.deleteBullet(this);
					thFlag = false;
				}
				cnt = 0;
				
				
				
				
				for(int i = 0; i < bmList.size(); i++) {
					if(y + height >= bmList.get(i).getY() && y <= bmList.get(i).getY() 
					+ bmList.get(i).getHeight()+30 && (x+width) >= bmList.get(i).getX() 
							&& (x+width) >= bmList.get(i).getX() && x <= (bmList.get(i).getX()
									+ bmList.get(i).gettWidth())
					 )  {
						
						bmList.get(i).setHp(dmg);
						bullet.setVisible(false);
						st1.remove(bullet);
						gun.deleteBullet(this);
						thFlag = false;
						
						break;
					}
				}
				for(int i = 0; i < bbList.size(); i++) {
					if(y + height >= bbList.get(i).getY() && y <= bbList.get(i).getY() 
					+ bbList.get(i).getHeight() && (x+width) >= bbList.get(i).getX() 
							&& (x+width) >= bbList.get(i).getX() && x <= (bbList.get(i).getX()
									+ bbList.get(i).gettWidth()-10)
					 )  {
						
						bbList.get(i).setHp(dmg);
						bullet.setVisible(false);
						st1.remove(bullet);
						gun.deleteBullet(this);
						thFlag = false;
					}
				}
				for(int i = 0; i < bmList.size(); i++) {
					if(bmList.get(i).getHp() < 0) {
						bmList.get(i).setTh1Flag(false);
						bmList.get(i).getInstance().setTh2Flag(false);
						bmList.get(i).getCurrentHpBar().setVisible(false);
						bmList.get(i).getImg().setVisible(false);
						bmList.remove(i);
					}
				}
				for(int i = 0; i < bbList.size(); i++) {
					if(bbList.get(i).getHp() < 0) {
						bbList.get(i).setTh1Flag(false);
						bbList.get(i).getInstance().setTh2Flag(false);
						bbList.get(i).getCurrentHpBar().setVisible(false);
						bbList.get(i).getImg().setVisible(false);
						bbList.remove(i);
					}
				}
				
				th.sleep(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setFlags(boolean flagUp, boolean flagDown, boolean flagRight, boolean flagLeft) {
		this.flagUp = flagUp;   
		this.flagDown = flagDown; 
		this.flagLeft = flagLeft; 
		this.flagRight = flagRight;
	}
	public JPanel getBullet() {
		return bullet;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int plusX() {
		return plusX;
	}
	public int plusY() {
		return plusY;
	}
	public boolean getSkip() {
		return skip;
	}
}