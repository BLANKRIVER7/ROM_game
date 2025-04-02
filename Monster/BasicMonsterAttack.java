package Monster;

import characters.Player1;

public class BasicMonsterAttack implements Runnable{
	private int dmg;
	Monster m;
	int hp;
	Player1 p1;
	int scope = 32;
	//스레드 변수 생성
	Thread th2;
	boolean th2Flag = true;
	
	public BasicMonsterAttack(int dmg, Monster m, Player1 p1) {
		this.dmg = dmg;
		this.m = m;
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
		int distanceX;
		int distanceY;
		
		try {
			distanceX = p1.getX()-m.getX();
			distanceY = p1.getY()-m.getY();
			
			if(distanceX < 0)
				distanceX *= -1;
			if(distanceY < 0)
				distanceY *= -1;
			if(distanceX <= scope && distanceY <= scope) {
				th2.sleep(1000);
				distanceX = p1.getX()-m.getX();
				distanceY = p1.getY()-m.getY();
				
				if(distanceX < 0)
					distanceX *= -1;
				if(distanceY < 0)
					distanceY *= -1;
				
				if(distanceX <= scope && distanceY <= scope)  {
					p1.sethp(dmg);
					th2.sleep(1300);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTh2Flag(boolean th2Flag) {
		this.th2Flag = th2Flag;
	}

}
