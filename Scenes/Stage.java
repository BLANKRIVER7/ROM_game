package Scenes;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Monster.Monster;
import boss.Boss;
import characters.Player1;

public abstract class Stage extends JLayeredPane implements Runnable, ActionListener {
	protected int width = 1290;
	protected int height = 900;
	//맵에서 벽을 제외한 왼쪽 끝 좌표
	protected int leftTopX = 21;
	protected int leftTopY = 63;
	//돌아가기
	protected JButton topBtn = new JButton(new ImageIcon("buttons/ButtonBackToMain.png"));
	//맵 배경
	protected JLabel mainBack;
	//인벤토리
	ImageIcon emptyGui_1 = new ImageIcon("items/GunItem.png");
	ImageIcon emptyGui_2 = new ImageIcon("items/emptyItemGui.png");
	ImageIcon emptyGui_3 = new ImageIcon("items/recoveryPotion.png");
	ImageIcon emptyGui_4 = new ImageIcon("items/emptyItemGui.png");
	ImageIcon emptyGui_5 = new ImageIcon("items/emptyItemGui.png");
	JLabel itemGui_1 = new JLabel(emptyGui_1);
	JLabel itemGui_2 = new JLabel(emptyGui_2);
	JLabel itemGui_3 = new JLabel(emptyGui_3);
	JLabel itemGui_4 = new JLabel(emptyGui_4);
	JLabel itemGui_5 = new JLabel(emptyGui_5);
	boolean Gui_1Flag = false;
	boolean Gui_2Flag = false;
	boolean Gui_3Flag = false;
	boolean Gui_4Flag = false;
	boolean Gui_5Flag = false;
	//인젝트된 객체의 주소
	protected MainMenuScene mms;
	protected GameScene gs;
	//스레드 생성
	protected Thread th;
	protected boolean th1Flag = true;
	//상호작용 플래그
	protected boolean fire = false;
	protected boolean rFlag = false;
	//몬스터
	protected ArrayList<Monster> bmList = new ArrayList<>();
	//보스
	protected ArrayList<Boss> bbList = new ArrayList<>();
	//맵의 타일과 연동
	protected char[][] xy = new char[22][40];
	//타일의 사이즈
	protected int sizeX = 32;
	protected int sizeY = 32;
	//아이템 선택창 하이라이트 플래그
	protected boolean num1 = false;
	protected boolean num2 = false;
	protected boolean num3 = false;
	protected boolean num4 = false;
	protected boolean num5 = false;
	protected String[] num = new String[5];
	//강조하는데 사용할 객체
	protected LineBorder boldOutLine = new LineBorder(Color.blue, 5, true);
	//게임오버
	protected JLabel gameOver = new JLabel(new ImageIcon("gameOver/gameOver.png"));
	protected JLabel gameClear = new JLabel(new ImageIcon("gameClear/gameClear.png"));
	protected JButton restartBtn = new JButton(new  ImageIcon("gameOver/restart.png"));
	protected JButton backToMainBtn = new JButton(new  ImageIcon("gameOver/back.png"));
	//게이트
	protected JLabel gate1 = new JLabel(new ImageIcon("gates/gate1.png"));
	protected JLabel gate2 = new JLabel(new ImageIcon("gates/gate1.png"));
	//player 객체 주소
	protected Player1 p1;
	//멥과 배열 xy[][] 연결
	public abstract void connectingArrayWithMap();
	public abstract void setMoveFLags(boolean flagUp,boolean flagDown,boolean 
			flagLeft,boolean flagRight,boolean flagBoost);
	public abstract void setItemHighlightFLags(boolean num1,boolean num2,boolean num3
			,boolean num4,boolean num5);
	public abstract char[][] getXy();	
	public abstract Player1 getPlayer1();
	public abstract ArrayList<Monster> getMonsters();
	public abstract void deleteMonster(int i);
	public abstract boolean getTh1Flag();
}
