package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity{
	
	GamePanel gp;
	public static final String objName = "Blue Heart";
	
	public OBJ_BlueHeart(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		down1 = setup("/objects/blueheart",gp.tileSize,gp.tileSize);
		
		setDialogues();
	}
	
	public void setDialogues() {
		dialogues[0][0] = "What is this...?";
		dialogues[0][1] = "You found the legendary Blue Heart!";
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.cutSceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
		return true;
	}

}
