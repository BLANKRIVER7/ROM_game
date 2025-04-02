package boss;

import Monster.Monster;
import characters.Player1;

public class BossAttack implements Runnable{
	private int dmg;
	private Boss b;
	private int hp;
	private Player1 p1;
	private int scope = 42;
	//스레드 변수 생성
	private Thread th2;
	private boolean th2Flag = true;
	
	public BossAttack(int dmg, Boss b, Player1 p1) {
		this.dmg = dmg;
		this.b = b;
		this.p1 = p1;
		//스레드 생성 및 실행
		th2 = new Thread(this);
		th2.start();
	}
	@Override
	public void run() {
		while(th2Flag) {
			try {
				th2.sleep(20);
				attack();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void attack() {
		int plMiddleX = 0;
		int plMiddleY = 0;
		int bMiddleX = 0;
		int bMiddleY = 0;
		int distanceX = 0;
		int distanceY = 0;
		
		try {
			plMiddleX = p1.getX()+9;
			plMiddleY = p1.getY()+9;
			bMiddleX = b.getX()+22;
			bMiddleY = b.getY()+24;
			
			distanceX = plMiddleX-bMiddleX;
			distanceY = plMiddleY-bMiddleY;
			
			if(distanceX < 0)
				distanceX *= -1;
			if(distanceY < 0)
				distanceY *= -1;
			
			if(distanceX <= scope && distanceY <= scope) {
				p1.sethp(dmg);
				th2.sleep(1500);
				
			}else {
				th2.sleep(1500);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTh2Flag(boolean th2Flag) {
		this.th2Flag = th2Flag;
	}

}
