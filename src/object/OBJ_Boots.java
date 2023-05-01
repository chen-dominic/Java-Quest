package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{

	public static final String objName = "Boots";
	GamePanel gp;
	int speedBoost;
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		name = objName;
		down1 = setup("/objects/boots",gp.tileSize,gp.tileSize);
		price = 100;
		speedBoost = 2;
		type = type_boot;
		description = "[" + name + "]\nLightweight Boots\n+" + speedBoost + " Speed";
		
	}
	
	public void equip(Entity equipper) {
		equipper.speed += speedBoost;
	}
	public void unequip(Entity equipper) {
		equipper.speed -= speedBoost;
	}
}
