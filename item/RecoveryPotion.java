package item;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Scenes.Stage1;
import characters.Player1;

public class RecoveryPotion implements Item{
	private int getHp = 30;
	//인젝트 받은 주소 저장
	private Player1 p1;
	private Stage1 st1;
	//스레드
	private Thread th;
	private boolean thFlag = true;
	//사용 여부
	private boolean itemUse = false;
	private boolean num3 = false;
	//포션 이미지
	private JLabel img = new JLabel();
	private ImageIcon imgRight = new ImageIcon("items/recoveryPotion_right.png");
	private ImageIcon imgLeft = new ImageIcon("items/recoveryPotion_left.png");
	//방향
	private boolean flagUp = false;
	private boolean flagDown = false;
	private boolean flagRight = false;
	private boolean flagLeft = false;
	//포션바
	private int potionCnt = 10;
	private JLabel potionCntGui = new JLabel();
	private JPanel potionUseTime = new JPanel();
	private JLabel text = new JLabel("사용 가능");
	private String text1 = "사용 가능";
	private String text2 = "사용 불가능";
	
	public RecoveryPotion(Player1 p1, Stage1 st1) {
		this.p1 = p1;
		this.st1 = st1;
		st1.add(img, new Integer(1), 0);
		img.setIcon(imgRight);
		img.setSize(32, 32);
		img.setVisible(false);
		st1.add(potionCntGui, new Integer(0), 0);
		potionCntGui.setBounds(150, 10, 150, 30);
		potionCntGui.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		potionCntGui.setForeground(Color.white);
		potionCntGui.setVisible(false);
		st1.add(potionUseTime, new Integer(0), 0);
		potionUseTime.setBounds(310, 10, 100, 30);
		potionUseTime.setBackground(Color.blue);
		potionUseTime.add(text);
		text.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		text.setForeground(Color.white);
		potionUseTime.setVisible(false);
		
		//스레드 시작
		th = new Thread(this);
		th.start();
	}
	@Override
	public void run() {
		try {
			while(thFlag) {
				useItem();
				
				potionCntGui.setText("사용 가능 개수 : "+potionCnt);
				potionUseTime.setLocation(310, 10);
				
				if(num3) {
					potionCntGui.setVisible(true);
					potionUseTime.setVisible(true);	
				}
				else {
					potionCntGui.setVisible(false);
					potionUseTime.setVisible(false);					
					
				}
				
				if(flagRight) {
					img.setIcon(imgRight);
				}else if(flagLeft) {
					img.setIcon(imgLeft);
				}
				
				th.sleep(20);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void useItem() {
		if(p1.getThTime().getTime3() >= 1 && potionCnt != 0)
			text.setText(text1);
		else
			text.setText(text2);
		
		if(itemUse && num3 &&  p1.getHp() < 200 && p1.getThTime().getTime3() >= 1 && potionCnt > 0) {
			p1.getThTime().setTime3(0);
			p1.sethp(-getHp);
			potionCnt--;
		}
	}
	public void setItemUse(boolean itemUse) {
		this.itemUse = itemUse;
	}
	public void setNum3(boolean num3) {
		this.num3 = num3;
	}
	public JLabel getImg() {
		return img;
	}
	public void setFlags(boolean flagUp, boolean flagDown, boolean flagRight, boolean flagLeft) {
		this.flagUp = flagUp;   
		this.flagDown = flagDown; 
		this.flagRight = flagRight;
		this.flagLeft = flagLeft; 
	}
}
