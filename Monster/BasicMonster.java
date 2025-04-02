package Monster;

import Scenes.Stage1;
import characters.Player1;

public class BasicMonster extends Monster {
	private BasicMonsterAttack ba;
	public BasicMonster(int x, int y, Stage1 st1, Player1 p1) {
		setX(x);
		setY(y);
		setJlp(st1);
		setP1(p1);
		
		setXy(st1.getXy());
		
		setDmg(20);
		setWidth(32);
		setHeight(32);
		setImg("monsters/basicMonster_left.png","monsters/basicMonster_right.png"
				,"monsters/basicMonster_move_left.gif","monsters/basicMonster_move_right.gif");
		
		ba = new BasicMonsterAttack(getDmg(),this,p1);
		creator();
	}
	@Override
	public BasicMonsterAttack getInstance() {
		return ba;
	}
}
