package Scenes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class MainMenuScene extends JFrame implements ActionListener {
	//게임씬 객체 생성
	private GameScene gs;
	//창 크기 및 위치
	int width = 900;
	int height = 600;
	int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width)/2;
	int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height)/2;
	//계층 패인
	private JLayeredPane jlp = new JLayeredPane();
	//패널
	private JPanel desc = new JPanel();
	//라벨
	private JLabel mainBack =  new JLabel(new ImageIcon("backgrounds/mainBackground.png"));
	//버튼
	private JButton startBtn = new JButton(new ImageIcon("buttons/ButtonStart.png"));
	private JButton descBtn = new JButton(new ImageIcon("buttons/ButtonDesc.png"));
	private JButton exitBtn = new JButton(new ImageIcon("buttons/ButtonExit.png"));
	private JButton backBtn = new JButton(new ImageIcon("buttons/ButtonBack.png"));
	private JButton newGameBtn = new JButton(new ImageIcon("buttons/ButtonNewGameStart.png"));
	//기본 버튼 크기
	private int btnWidth = 208;
	private int btnHeight = 48;
	//게임 설명
	JLabel gameDesc1 = new JLabel("게임 설명");
	JLabel gameDesc2 = new JLabel("부스터는 'shift' 키를 누르시면 됩니다.");
	JLabel gameDesc3 = new JLabel("총알 발사는 'spacebar' 키를 누르시면 됩니다.");
	JLabel gameDesc4 = new JLabel("장전은 'r' 키를 누르시면 됩니다.");
	JLabel gameDesc5 = new JLabel("회복물약 사용은 'e'키를 누르시면 됩니다.");
	JLabel gameDesc6 = new JLabel("게임은 모든 보스를 처치시 클리어됩니다.");
	JLabel gameDesc7 = new JLabel("몬스터 방 입구는 총알이 통과 못 합니다");
	//텍스처 선택용 라디오 버튼
	ButtonGroup bGroup = new ButtonGroup();
	JRadioButton rb1 = new JRadioButton("배경1");
	JRadioButton rb2 = new JRadioButton("배경2");
	//텍스처 선택
	private int backNum = 0;
	private JLabel map1_1 = new JLabel(new ImageIcon("backgrounds/map1-1_image.png"));
	private JLabel map1_2 = new JLabel(new ImageIcon("backgrounds/map1-2_image.png"));
	//타이틀 아이콘
	Image titleIcon = new ImageIcon("backgrounds/mainIcon.png").getImage();
	//싱글턴
	private static MainMenuScene mms = new MainMenuScene();
	//생성자 외부에서 호출 불가능
	private MainMenuScene(){
		
	}
	public static MainMenuScene getInstance() {
		return mms;
	}
	public void mainScene() {
		this.setBounds(x, y, width, height);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("ROM");
		this.setIconImage(titleIcon);
		this.add(jlp);
		jlp.setBounds(0, 0, width, height);	
		
		jlp.add(mainBack, new Integer(0),0);
		mainBack.setBounds(0, 0, width, height);
		
		jlp.add(startBtn, new Integer(1), 0);
		startBtn.setBounds((width - btnWidth)/2, (height - btnHeight)/2-btnHeight-10, btnWidth, btnHeight);
		startBtn.addActionListener(this);
		
		jlp.add(descBtn,new Integer(1), 0);
		descBtn.setBounds((width - btnWidth)/2, (height - btnHeight)/2, btnWidth, btnHeight);
		descBtn.addActionListener(this);
		desc.setVisible(false);
		
		jlp.add(gameDesc1,new Integer(1), 0);
		gameDesc1.setBounds((width-250)/2+100, 110, 250, 30);
		gameDesc1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		gameDesc1.setVisible(false);
		
		jlp.add(gameDesc2,new Integer(1), 0);
		gameDesc2.setBounds((width-300)/2, 180, 300, 30);
		gameDesc2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		gameDesc2.setVisible(false);
		
		jlp.add(gameDesc3,new Integer(1), 0);
		gameDesc3.setBounds((width-300)/2, 210, 300, 30);
		gameDesc3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		gameDesc3.setVisible(false);
		
		jlp.add(gameDesc4,new Integer(1), 0);
		gameDesc4.setBounds((width-300)/2, 240, 300, 30);
		gameDesc4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		gameDesc4.setVisible(false);
		
		jlp.add(gameDesc5,new Integer(1), 0);
		gameDesc5.setBounds((width-300)/2, 270, 300, 30);
		gameDesc5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		gameDesc5.setVisible(false);
		
		jlp.add(gameDesc6,new Integer(1), 0);
		gameDesc6.setBounds((width-300)/2, 300, 300, 30);
		gameDesc6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		gameDesc6.setVisible(false);
		
		jlp.add(gameDesc7,new Integer(1), 0);
		gameDesc7.setBounds((width-300)/2, 330, 300, 30);
		gameDesc7.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		gameDesc7.setVisible(false);
		
		jlp.add(exitBtn,new Integer(1), 0);
		exitBtn.setBounds((width - btnWidth)/2, (height - btnHeight)/2+btnHeight+10, btnWidth, btnHeight);
		exitBtn.addActionListener(this);

		jlp.add(backBtn,new Integer(1), 0);
		backBtn.setBounds((width - btnWidth)/2+200, (height - btnHeight)/2+btnHeight+100, btnWidth, btnHeight);
		backBtn.addActionListener(this);
		backBtn.setVisible(false);
		
		jlp.add(newGameBtn,new Integer(1), 0);
		newGameBtn.setBounds(350, 350, btnWidth, btnHeight);
		newGameBtn.setFocusable(false);
		newGameBtn.addActionListener(this);
		newGameBtn.setVisible(false);
		
		jlp.add(desc,new Integer(1), -1);
		desc.setBounds(133, 95, 630, 390);
	
		//라디오 버튼
		bGroup.add(rb1);
		bGroup.add(rb2);
		
		jlp.add(rb1,new Integer(1), 0);
		rb1.setBounds(290, 300, 80, 20);
		rb1.setFocusable(false);
		rb1.addActionListener(this);
		rb1.setVisible(false);
		
		jlp.add(rb2,new Integer(1), 0);
		rb2.setBounds(530, 300, 80, 20);
		rb2.setFocusable(false);
		rb2.addActionListener(this);
		rb2.setVisible(false);
		
		jlp.add(map1_1, new Integer(1), 0);
		map1_1.setBounds(230, 150, 200, 140);
		map1_1.setVisible(false);
		
		jlp.add(map1_2, new Integer(1), 0);
		map1_2.setBounds(470, 150, 200, 140);
		map1_2.setVisible(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	public void backToMain() {
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rb1) {
			backNum = 1;
		}else if(e.getSource() == rb2) {
			backNum = 2;
		}
		
		if(e.getSource() == descBtn) {
			startBtn.setVisible(false);
			descBtn.setVisible(false);
			exitBtn.setVisible(false);
			backBtn.setVisible(true);
			desc.setVisible(true);
			gameDesc1.setVisible(true);
			gameDesc2.setVisible(true);
			gameDesc3.setVisible(true);
			gameDesc4.setVisible(true);
			gameDesc5.setVisible(true);
			gameDesc6.setVisible(true);
			gameDesc7.setVisible(true);
		}else if(e.getSource() == exitBtn) {
			System.exit(0);
		}else if(e.getSource() == backBtn) {
			startBtn.setVisible(true);
			descBtn.setVisible(true);
			exitBtn.setVisible(true);
			backBtn.setVisible(false);
			desc.setVisible(false);
			gameDesc1.setVisible(false);
			newGameBtn.setVisible(false);
			gameDesc2.setVisible(false);
			gameDesc3.setVisible(false);
			gameDesc4.setVisible(false);
			gameDesc5.setVisible(false);
			gameDesc6.setVisible(false);
			gameDesc7.setVisible(false);
			rb1.setVisible(false);
			rb2.setVisible(false);
			map1_1.setVisible(false);
			map1_2.setVisible(false);
		}else if(e.getSource() == startBtn) {
			startBtn.setVisible(false);
			descBtn.setVisible(false);
			exitBtn.setVisible(false);
			backBtn.setVisible(true);
			newGameBtn.setVisible(true);
			rb1.setVisible(true);
			rb2.setVisible(true);
			map1_1.setVisible(true);
			map1_2.setVisible(true);
		}else if(e.getSource() == newGameBtn && backNum != 0) {
			backBtn.setVisible(false);
			newGameBtn.setVisible(false);
			startBtn.setVisible(true);
			descBtn.setVisible(true);
			exitBtn.setVisible(true);
			rb1.setVisible(false);
			rb2.setVisible(false);
			map1_1.setVisible(false);
			map1_2.setVisible(false);
			this.setVisible(false);
			gs = new GameScene();
			gs.mainScene(mms, backNum);
		}
	}
}
	
