package characters;

import Scenes.GameScene;

public class PlayerRun implements Runnable {
	private Player1 p1;
	private boolean flagBoost = false;
	private boolean th2Flag = true;
	
	public PlayerRun(Player1 p1) {
		this.p1 = p1;
	}
	@Override
	public void run() {
		while(th2Flag) {
			if(flagBoost) {
				p1.setStamina();
			}else {							
				p1.setStaminaManager();
			}
			p1.setThreadSleep();
		}
	}
	public void setFlagBoost(boolean flagBoost){
		this.flagBoost = flagBoost;
	}
	public void setTh2Flag(boolean th2Flag) {
		this.th2Flag = th2Flag;
	}
}
