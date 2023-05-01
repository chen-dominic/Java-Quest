package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escPressed, kPressed, shotKeyPressed, mute = false, shiftPressed;
	
	// DEBUG
	boolean checkDrawTime = false;
	public boolean godModeOn = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutSceneState) {
			dialogueState(code);
		}
		// CHARACTER STATE
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		// OPTIONS STATE
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		// GAME OVER STATE
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		// TRADE STATE
		else if(gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		// MAP STATE
		else if(gp.gameState == gp.mapState) {
			mapState(code);
		}
		
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = true;
		}
	}
	
	public void titleState(int code) {
		if(gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				gp.playSE(9);
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				gp.playSE(9);
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_SPACE) {
				if(gp.ui.commandNum == 0) {
					gp.ui.titleScreenState = 1;
				}
				if(gp.ui.commandNum == 1) {
					gp.saveLoad.load();
					gp.gameState = gp.playState;
					gp.playMusic(0);
					//gp.ui.titleScreenState = 2;
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		else if(gp.ui.titleScreenState == 1) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_SPACE) {
				if(gp.ui.commandNum == 0) {
					//System.out.println("FIGHTER");
					gp.gameState = gp.playState;
					gp.player.maxLife -= 6;
					gp.player.life -= 6;
					gp.player.strength += 4;
					gp.player.attack = gp.player.getAttack();
					gp.player.classSel = "Fighter";
					gp.playMusic(0);
					gp.ui.commandNum = 0;
					gp.ui.titleScreenState = 0;
				}
				if(gp.ui.commandNum == 1) {
					//System.out.println("THIEF");
					gp.gameState = gp.playState;
					gp.player.speed = gp.player.defaultSpeed + 3;
					gp.player.classSel = "Thief";
					gp.playMusic(0);
					gp.ui.commandNum = 0;
					gp.ui.titleScreenState = 0;
				}
				if(gp.ui.commandNum == 2) {
					//System.out.println("SORCERER");
					gp.gameState = gp.playState;
					gp.player.attackArea.width = 72;
					gp.player.attackArea.height = 72;
					gp.player.strength += 2;
					gp.player.maxLife -= 4;
					gp.player.life -= 4;
					gp.player.maxMana += 8;
					gp.player.mana = gp.player.maxMana;
					gp.player.classSel = "Sorcerer";
					gp.playMusic(0);
					gp.ui.commandNum = 0;
					gp.ui.titleScreenState = 0;
				}
				if(gp.ui.commandNum == 3) {
					gp.ui.titleScreenState = 0;
					gp.ui.commandNum = 0;
				}
			}
		}
		if(gp.ui.titleScreenState == 2) {
			gp.ui.commandNum = 0;
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.titleScreenState = 0;
				gp.ui.commandNum = 1;
			}
		}
	}
	
	public void playState(int code) {
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = true;
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		if(code == KeyEvent.VK_K) {
			kPressed = true;
			if(!gp.player.guarding) {
				gp.player.attacking = true;
			}
		}
		if(code == KeyEvent.VK_E) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
//		if(code == KeyEvent.VK_M) {
//			if(mute) {
//				mute = false;
//				gp.playMusic(code);
//			}
//			if(!mute) {
//				mute = true;
//				gp.stopMusic();
//			}
//		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.mapState;
		}
		if(code == KeyEvent.VK_X) {
			if(!gp.map.miniMapOn) {
				gp.map.miniMapOn = true;
			}
			else {
				gp.map.miniMapOn = false;
			}
		}
		if(code == KeyEvent.VK_SHIFT) {
			shiftPressed = true;
		}
		
		// DEBUG
		if(code == KeyEvent.VK_T) {
			if(!checkDrawTime) {
				checkDrawTime = true;
				gp.tileM.drawPath = true;
			}
			else if(checkDrawTime) {
				checkDrawTime = false;
				gp.tileM.drawPath = false;
			}
		}
		if(code == KeyEvent.VK_R) {
			switch(gp.currentMap) {
			case 0: gp.tileM.loadMap("/maps/worldV3.txt", 0); break;
			case 1: gp.tileM.loadMap("/maps/interior01.txt", 0); break;
			}
		}
		if(code == KeyEvent.VK_G) {
			
			if(!godModeOn) {
				godModeOn = true;
				gp.player.strength += 15;
				gp.player.speed += 2;
				gp.player.dexterity += 100;
				gp.player.getAttack();
				gp.player.getDefense();
			}
			else if(godModeOn) {
				godModeOn = false;
				gp.player.strength -= 15;
				gp.player.speed -= 2;
				gp.player.dexterity -= 100;
				gp.player.getAttack();
				gp.player.getDefense();
			}
		}
	}
	
	public void pauseState(int code) {
		
		if(!escPressed && code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
	}
	public void characterState(int code) {
		if(code == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_SPACE) {
			gp.player.selectItem();
		}
		playerInventory(code);
	}
	public void optionsState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(9);
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(9);
				}
			}
		}
		
		if(code == KeyEvent.VK_SPACE) {
			
		}
	}
	public void gameOverState(int code) {
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 0;
			}
			else {
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 1;
			}
			else {
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_SPACE) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.resetGame(false);
				gp.playMusic(0);
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.resetGame(true);
			}
		}
	}
	public void tradeState(int code) {
		
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(9);
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
				gp.ui.commandNum = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
				gp.ui.commandNum = 0;
			}
		}
	}
	public void mapState(int code) {
		
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.playState;
		}
	}
	public void playerInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSE(9);
			}
		}
	}
	public void npcInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSE(9);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = false;
		}
		if(code == KeyEvent.VK_K) {
			kPressed = false;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
		if(code == KeyEvent.VK_SHIFT) {
			shiftPressed = false;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = false;
		}
		
	}

}
