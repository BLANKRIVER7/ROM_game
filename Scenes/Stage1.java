package Scenes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Monster.BasicMonster;
import Monster.Monster;
import boss.Boss;
import boss.Boss1;
import characters.Player1;

public class Stage1 extends Stage {

		@Override
		public void connectingArrayWithMap() {
			//0 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				xy[0][i] = 'W';			
			}
			//1 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 11 || i == 16 || i == 24 || i == 39)
					xy[1][i] = 'W';			
				else
					xy[1][i] = 'A';
			}
			//2 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 11 || i == 13 || i == 14 || (i >= 18 && i <= 22) || i == 24 
						|| (i >= 28 && i <= 31) || i == 33 || (i >= 35 && i <= 37) || i == 39)				
					xy[2][i] = 'W';
				else
					xy[2][i] = 'A';
			}
			//3 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if((i == 0 || i >= 1 && i <= 8) || i == 11 || (i >= 14 && i <= 18) || i == 22 
						|| i == 24 || i == 28 || i == 33 || i == 35 || i == 39)				
					xy[3][i] = 'W';
				else
					xy[3][i] = 'A';
			}
			//4 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 8 || i == 11 || i == 12 || i == 16 || i == 22 || i == 24 
						|| i == 28 || i == 30 || i == 33 || i == 39)				
					xy[4][i] = 'W';
				else
					xy[4][i] = 'A';
			}
			//5 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 1 || i == 2 || (i >= 4 && i <= 6) || i == 8 
						|| (i >= 12 && i <= 14) || (i >= 16 && i <= 20) || i == 22 
						|| (i >= 24 && i <= 28) || (i >= 30 && i <= 33) || (i >= 35 && i <= 37) 
						|| i == 39)				
					xy[5][i] = 'W';
				else
					xy[5][i] = 'A';
			}
			//6 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 2 || i == 4 || i == 6 || i == 31 || i == 35 || i == 39)				
					xy[6][i] = 'W';
				else
					xy[6][i] = 'A';
			}
			//7 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 2 || i == 4 || (i >= 6 && i <= 14) || (i >= 16 && i <= 20) 
						|| (i >= 22 && i <= 29) || i == 31 || i == 34 || i == 35 || i == 39)				
					xy[7][i] = 'W';
				else
					xy[7][i] = 'A';
			}
			//8 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 7 || i == 14 || i == 16 || i == 20 || i == 22 || i == 31 
						|| i == 39)				
					xy[8][i] = 'W';
				else
					xy[8][i] = 'A';
			}
			//9 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 7 || i == 14 
						|| i == 16 || i == 20 || i == 22 || (i >= 24 && i <= 31) 
						|| (i >= 33 && i <= 36) || i == 38 || i == 39)				
					xy[9][i] = 'W';
				else
					xy[9][i] = 'A';
			}
			//10 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 4 || i == 7 || i == 14 || i == 16 || i == 22 || i == 24 
						|| i == 33 || i == 39)				
					xy[10][i] = 'W';
				else
					xy[10][i] = 'A';
			}
			//11 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || (i >= 2 && i <= 4) || i == 7 || (i >= 16 && i <= 22) 
						|| i == 24 || (i >= 26 && i <= 30) || i == 32 
						|| i == 33 || (i >= 35 && i <= 37) || i == 39)				
					xy[11][i] = 'W';
				else
					xy[11][i] = 'A';
			}
			//12 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0|| i == 2 || i == 4 || (i >= 7 && i <= 14) || i == 24 || i == 26 
						|| i == 30 || i == 32 || i == 35 || i == 39)				
					xy[12][i] = 'W';
				else
					xy[12][i] = 'A';
			}
			//13 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 2 || i == 16 || (i >= 18 && i <= 22) || i == 24 || i == 26 
						|| i == 30 || i == 32 || i == 34 || i == 35 || i == 37
						|| i == 38 || i == 39)				
					xy[13][i] = 'W';
				else
					xy[13][i] = 'A';
			}
			//14 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 2 || (i >= 4 && i <= 6) || (i >= 8 && i <= 10) 
						|| (i >= 12 && i <= 16) || i == 18 || i == 24 
						|| i == 26 || i == 30 || i == 32 || i == 39)				
					xy[14][i] = 'W';
				else
					xy[14][i] = 'A';
			}
			//15 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 4 || (i >= 6 && i <= 8) || i == 12 || i == 18 
						|| (i >= 20 && i<= 24) || i == 26 || i == 27 
						|| i == 30 || i == 32 || i == 39)				
					xy[15][i] = 'W';
				else
					xy[15][i] = 'A';
			}
			//16 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if((i == 0 || i >= 1 && i <= 4) || i == 8 ||  (i >= 10 && i <= 12) 
						|| (i >= 14 && i <= 18) || i == 20 || i == 27 
						|| i == 29 || i == 30 || i == 32 || i == 39)				
					xy[16][i] = 'W';
				else
					xy[16][i] = 'A';
			}
			//17 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 8 || i == 10 || i == 14 || i == 20 || i == 27 || i == 29 
						|| i == 32 || i == 37 || i == 39)				
					xy[17][i] = 'W';
				else
					xy[17][i] = 'A';
			}
			//18 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || (i >= 1 && i <= 3) || (i >= 12 && i <= 14) || i == 17 || i == 20 
						|| i == 27 || i == 29 || (i >= 31 && i <= 37) || i == 39)				
					xy[18][i] = 'W';
				else
					xy[18][i] = 'A';
			}
			//19 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if((i == 0 || i >= 3 && i <= 7) || (i >= 9 && i <= 12) || (i >= 16 && i <= 27) 
						|| (i >= 29 && i <= 31) || i == 39)				
					xy[19][i] = 'W';
				else
					xy[19][i] = 'A';
			}
			//20 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				if(i == 0 || i == 39)
					xy[20][i] = 'W';
				else
					xy[20][i] = 'A';
			}
			//21 번째 행
			for(int i = 0; i < xy[0].length; i++) {
				xy[21][i] = 'W';
			}
		}
		
		public Stage1(MainMenuScene mms, GameScene gs, int backNum) {
			this.mms = mms;
			this.gs = gs;
			
			for(int i = 0; i < 5; i++) {
				if(i == 0)
					num[i] = "gun";
				num[i] = "";
			}
			
			if(backNum == 1)
				mainBack = new JLabel(new ImageIcon("backgrounds/gameMap1-1.png"));
			else if(backNum == 2)
				mainBack = new JLabel(new ImageIcon("backgrounds/gameMap1-2.png"));

			this.setBounds(0,-10,width,height);
			connectingArrayWithMap();
		    
		    p1 = new Player1(21,63,this);
		    
		    bmList.add(new BasicMonster(116,680,this,p1));
		    bmList.add(new BasicMonster(500,680,this,p1));
		    bmList.add(new BasicMonster(800,680,this,p1));
		    bmList.add(new BasicMonster(120,600,this,p1));
		    bmList.add(new BasicMonster(500,600,this,p1));
		    bmList.add(new BasicMonster(250,300,this,p1));
		    bmList.add(new BasicMonster(250,340,this,p1));
		    bmList.add(new BasicMonster(250,380,this,p1));
		    bmList.add(new BasicMonster(370,300,this,p1));
		    bmList.add(new BasicMonster(370,340,this,p1));
		    bmList.add(new BasicMonster(370,380,this,p1));
		    bmList.add(new BasicMonster(680,560,this,p1));
		    bmList.add(new BasicMonster(680,590,this,p1));
		    bmList.add(new BasicMonster(680,615,this,p1));
		    bmList.add(new BasicMonster(710,560,this,p1));
		    bmList.add(new BasicMonster(710,590,this,p1));
		    bmList.add(new BasicMonster(710,615,this,p1));
		    bmList.add(new BasicMonster(790,560,this,p1));
		    bmList.add(new BasicMonster(790,590,this,p1));
		    bmList.add(new BasicMonster(790,615,this,p1));
		    bmList.add(new BasicMonster(820,560,this,p1));
		    bmList.add(new BasicMonster(820,590,this,p1));
		    bmList.add(new BasicMonster(820,615,this,p1));
		  
		    bbList.add(new Boss1(290,330,this,p1));
		    bbList.add(new Boss1(730,570,this,p1));
			
		    this.add(itemGui_1, new Integer(2), 0);
		    itemGui_1.setBounds(300,770,96,96);
		    itemGui_1.setBorder(boldOutLine);
		    this.add(itemGui_2, new Integer(2), 0);
		    itemGui_2.setBounds(405,770,96,96);
		    this.add(itemGui_3, new Integer(2), 0);
		    itemGui_3.setBounds(510,770,96,96);
		    this.add(itemGui_4, new Integer(2), 0);
		    itemGui_4.setBounds(615,770,96,96);
		    this.add(itemGui_5, new Integer(2), 0);
		    itemGui_5.setBounds(720,770,96,96);
		    
		    this.add(mainBack, new Integer(0), 0);
		    mainBack.setBounds(-10,-50,width,height);		    
		    
			this.add(topBtn, new Integer(2), 0);
			topBtn.addActionListener(this);
			topBtn.setBounds(0, 10, 100, 30);
			
			this.add(restartBtn, new Integer(2), 0);
			restartBtn.setBounds((width-400)/2, (height-100)/2-60, 400, 100);
			restartBtn.addActionListener(this);
			restartBtn.setVisible(false);
			
			this.add(backToMainBtn, new Integer(2), 0);
			backToMainBtn.addActionListener(this);
			backToMainBtn.setBounds((width-400)/2, (height-100)/2+60, 400, 100);
			backToMainBtn.addActionListener(this);
			backToMainBtn.setVisible(false);
			//게이트 - (공사중)
			this.add(gate1, new Integer(2), 0);
			gate1.setBounds(1243, 648, 32, 32);
			this.add(gate2, new Integer(2), 0);
			gate2.setBounds(1243, 680, 32, 32);
			
			//게임오버
			this.add(gameOver, new Integer(2), 0);
			gameOver.setBounds((width-700)/2, 150, 700, 150);
			gameOver.setVisible(false);
			//게임클리어
			this.add(gameClear, new Integer(2), 0);
			gameClear.setBounds((width-700)/2, 150, 700, 150);
			gameClear.setVisible(false);
			//스레드 시작
			th = new Thread(this);
			th.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == topBtn) {
				gs.setTh1Flag(false);
				th1Flag = false;
				p1.setTh1Flag(false);
				
				for(Monster bm : bmList) {					
					bm.setTh1Flag(false);
					bm.getInstance().setTh2Flag(false);
				}
				for(Boss bb : bbList) {					
					bb.setTh1Flag(false);
					bb.getInstance().setTh2Flag(false);
				}
				
				gs.setVisible(false);
				mms.backToMain();
			}else if(e.getSource() == restartBtn) {
				gs.mainScene();
			}else if(e.getSource() == backToMainBtn) {
				gs.setTh1Flag(false);
				gs.setVisible(false);
				mms.backToMain();
			}
		}
		@Override
		public void run() {
			try {
				while(th1Flag) {
					if(p1.getHp() <= 0) {
						th1Flag = false;
						p1.setTh1Flag(false);

						for(Monster bm : bmList) {					
							bm.setTh1Flag(false);
							bm.getInstance().setTh2Flag(false);
						}
						for(Boss bb : bbList) {					
							bb.setTh1Flag(false);
							bb.getInstance().setTh2Flag(false);
						}
						
						restartBtn.setVisible(true);
						backToMainBtn.setVisible(true);
						gameOver.setVisible(true);
					}
					if(bbList.size() == 0) {
						th1Flag = false;
						p1.setTh1Flag(false);

						for(Monster bm : bmList) {					
							bm.setTh1Flag(false);
							bm.getInstance().setTh2Flag(false);
						}
						for(Boss bb : bbList) {					
							bb.setTh1Flag(false);
							bb.getInstance().setTh2Flag(false);
						}
						
						restartBtn.setVisible(true);
						backToMainBtn.setVisible(true);
						gameClear.setVisible(true);
					}
					if(num1) {
						itemGui_1.setBorder(boldOutLine);
						itemGui_2.setBorder(null);
						itemGui_3.setBorder(null);
						itemGui_4.setBorder(null);
						itemGui_5.setBorder(null);	
					}else if(num2) {
						itemGui_1.setBorder(null);
						itemGui_2.setBorder(boldOutLine);
						itemGui_3.setBorder(null);
						itemGui_4.setBorder(null);
						itemGui_5.setBorder(null);	
					}else if(num3) {
						itemGui_1.setBorder(null);
						itemGui_2.setBorder(null);
						itemGui_3.setBorder(boldOutLine);
						itemGui_4.setBorder(null);
						itemGui_5.setBorder(null);	
					}else if(num4) {
						itemGui_1.setBorder(null);
						itemGui_2.setBorder(null);
						itemGui_3.setBorder(null);
						itemGui_4.setBorder(boldOutLine);
						itemGui_5.setBorder(null);	
					}else if(num5) {
						itemGui_1.setBorder(null);
						itemGui_2.setBorder(null);
						itemGui_3.setBorder(null);
						itemGui_4.setBorder(null);
						itemGui_5.setBorder(boldOutLine);	
					}
					th.sleep(30);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//setter와 getter
		@Override
		public char[][] getXy() {
			return xy;
		}
		@Override
		public Player1 getPlayer1() {
			return p1;
		}
		@Override
		public void setMoveFLags(boolean flagUp,boolean flagDown,boolean 
				flagRight,boolean flagLeft,boolean flagBoost) {
			p1.setFlags(flagUp, flagDown, flagRight, flagLeft, flagBoost);
		}
		@Override
		public void setItemHighlightFLags(boolean num1,boolean num2,boolean 
				num3,boolean num4,boolean num5) {
			this.num1 = num1;  
			this.num2 = num2;  
			this.num3 = num3;  
			this.num4 = num4;  
			this.num5 = num5;  
		}
		@Override
		public ArrayList<Monster> getMonsters() {
			return bmList;
		}
		public ArrayList<Boss> getBosses(){
			return bbList;
		}
		public boolean getNum1() {
			return num1;
		}
		public boolean getNum2() {
			return num2;
		}
		public boolean getNum3() {
			return num3;
		}
		public boolean getNum4() {
			return num4;
		}
		public boolean getNum5() {
			return num5;
		}
		@Override
		public void deleteMonster(int i) {
			bmList.remove(i);
		}
		@Override
		public boolean getTh1Flag() {
			return th1Flag;
		}
}
