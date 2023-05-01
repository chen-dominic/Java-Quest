package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Strength_Potion extends Entity{
	GamePanel gp;
	
	public static final String objName = "Strength Potion";

	public OBJ_Strength_Potion(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		value = 5;
		down1 = setup("/objects/strength_potion",gp.tileSize,gp.tileSize);
		description = "[Strength Potion]\nIncrease strength by " + value;
		price = 530;
		stackable = true;
	}
	public void setDialogue() {
		dialogues[0][0] = "Your strength has been increased by " + value;
	}
	public boolean use(Entity entity) {
		
//		startDialogue(this,0);
		gp.ui.addMessage("Your strength has been increased by " + value);
		entity.strength += value;
		gp.playSE(2);
		entity.attack = entity.getAttack();
		return true;
	}
}
