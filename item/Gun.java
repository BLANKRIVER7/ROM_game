package item;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Scenes.Stage1;
import Timer.CustomTimer;
import characters.Player1;

public class Gun implements Item{
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	private ArrayList<Bullet> forDeleteBullet = new ArrayList<Bullet>();
	private Player1 p1;
	private Stage1 st1;
	//총 이미지s
	private ImageIcon right = new ImageIcon("items/gun_right.png");
	private ImageIcon left = new ImageIcon("items/gun_left.png");
	private JLabel gunImage = new JLabel(right);
	//스래드
	private Thread th;
	private boolean thFlag = true;
	//방향
	private boolean flagUp = false;
	private boolean flagDown = false;
	private boolean flagRight = false;
	private boolean flagLeft = false;
	private String direction = "";
	private boolean fire;
	private boolean rFlag;
	//탑 배경
	private JPanel topBackgroung = new JPanel();
	//남은 탄창
	private JLabel remainedBullets = new JLabel();
	private int bulletSize = 0;
	//장전 가능 여부
	private JPanel reloadPanel = new JPanel();
	private JLabel reloadText = new JLabel();
	
	public Gun(Player1 p1, Stage1 st1) {
		this.p1 = p1;
		this.st1 = st1;
		st1.add(gunImage, new Integer(1), 0);
		gunImage.setBounds(p1.getX(), p1.getY(), 32, 32);
		
		for(int i = 0; i < 10; i++) {
			bullet.add(new Bullet(this, st1, p1));
		}
		bulletSize = bullet.size();
		
		st1.add(topBackgroung, new Integer(0), -1);
		topBackgroung.setBackground(Color.darkGray);
		topBackgroung.setBounds(100, 0, 1200, 40);
		
		st1.add(remainedBullets, new Integer(0), 0);
		remainedBullets.setBounds(150, 10, 100, 30);
		remainedBullets.setText(""+bullet.size());
		remainedBullets.setForeground(Color.white);
		remainedBullets.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		st1.add(reloadPanel, new Integer(0), 0);
		reloadPanel.setBounds(260, 10, 100, 30);
		reloadPanel.setBackground(Color.blue);
		reloadPanel.add(reloadText);
		reloadText.setText("장전 가능");
		reloadText.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		reloadText.setForeground(Color.white);
		
		//스레드 시작
		th = new Thread(this);
		th.start();
	}
	@Override
	public void useItem() {	
		if(flagUp)
			direction = "up";
		else if(flagDown)
			direction = "down";
		else if(flagRight)
			direction = "right";
		else if(flagLeft)
			direction = "left";
		
		if(flagRight) {
			gunImage.setIcon(right);
		}else if(flagLeft) {
			gunImage.setIcon(left);
		}
		
		if(fire && bulletSize > 0 && p1.getThTime().getTime() >= 1) {
			p1.getThTime().setTime(0);
			if(flagRight && flagUp) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(1, -1);
						break;
					}
			}else if(flagRight && flagDown) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(1, 1);
						break;
					}
			}else if(flagRight) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(1, 0);
						break;
					}
			}else if(flagLeft && flagUp) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(-1, -1);
						break;
					}
			}else if(flagLeft && flagDown) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(-1, 1);
						break;
					}
			}else if(flagLeft) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(-1, 0);
						break;
					}
			}else if(flagUp) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(0, -1);
						break;
					}
			}else if(flagDown) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(0, 1);
						break;
					}
			}else if(direction.equals("up")) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(0, -1);
						break;
					}
			}else if(direction.equals("down")) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(0, 1);
						break;
					}
			}else if(direction.equals("right")) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(1, 0);
						break;
					}
			}else if(direction.equals("left")) {
				for(Bullet b : bullet)
					if(b.getSkip() == false) {
						bulletSize -= 1;
						b.setFlags(flagUp, flagDown, flagRight, flagLeft);
						b.start(-1, 0);
						break;
					}
			}		

		}
		if(p1.getThTime().getTime2() >= 1)
			reloadText.setText("장전 가능");
		else
			reloadText.setText("장전 불가능");
		if(rFlag && p1.getThTime().getTime2() >= 1) {
			p1.getThTime().setTime2(0);
			
			if(bulletSize < 10) {
				for(int i = 0; i < 10-bulletSize; i++) {
					bullet.add(new Bullet(this, st1, p1));
				}			
			}
		}
		bulletSize = 0;
		for(Bullet b : bullet) {
			if(b.getSkip() != true) {
				bulletSize++;
			}
		}
	}
	@Override
	public void run() {

		try {
			while(thFlag) {
				useItem();
				deleteBullet();
				
				for(Bullet b : bullet) {
					b.getBullet().setLocation(b.getX(), b.getY());
				}
				if(st1.getNum1()) {
					remainedBullets.setVisible(true);
					reloadPanel.setVisible(true);
				}else {
					remainedBullets.setVisible(false);
					reloadPanel.setVisible(false);
				}
				
				remainedBullets.setText("남은 탄약: "+bulletSize);
//				remainedBullets.setLocation(300,10);
				th.sleep(15);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setThFlag(boolean thFlag) {
		this.thFlag = thFlag;
	}
	public void setGun(boolean fire, boolean rFlag) {
		this.fire = fire;
		this.rFlag = rFlag;
	}
	public void setFlags(boolean flagUp, boolean flagDown, boolean flagRight, boolean flagLeft) {
		this.flagUp = flagUp;   
		this.flagDown = flagDown; 
		this.flagRight = flagRight;
		this.flagLeft = flagLeft; 
	}
	public JLabel getGunImage() {
		return gunImage;
	}
	public void deleteBullet(Bullet b) {
		forDeleteBullet.add(b);
	}
	public void deleteBullet() {
		for(Bullet b : forDeleteBullet) {
			bullet.remove(b);
		}
	}
	public boolean getFlagUp() {
		return flagUp;
	}
	public boolean getFlagDown() {
		return flagDown;
	}
	public boolean getFlagLeft() {
		return flagLeft;
	}
	public boolean getFlagRight() {
		return flagRight;
	}
	public String getDirection() {
		return direction;
	}
}
