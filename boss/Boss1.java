package boss;

import Monster.BasicMonsterAttack;
import Scenes.Stage1;
import characters.Player1;

public class Boss1 extends Boss{
	private BossAttack ba;
	public Boss1(int x, int y, Stage1 st1, Player1 p1) {
		setX(x);
		setY(y);
		setJlp(st1);
		setP1(p1);
		
		setXy(stage.getXy());
		
		setDmg(50);
		setWidth(64);
		setHeight(64);
		setImg("boss1/boss1_left.png", "boss1/boss1_right.png"
				, "boss1/boss1_move_left.gif", "boss1/boss1_move_right.gif");
		
		ba = new BossAttack(getDmg(),this,p1);
		creator();
	}
	@Override
	public BossAttack getInstance() {
		// TODO Auto-generated method stub
		return ba;
	}

}
