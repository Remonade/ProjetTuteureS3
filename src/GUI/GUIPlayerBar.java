/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import Graphic.Model;
import Logic.EntityUnit;
import Maths.Vector2f;
import Maths.Vector4f;

public class GUIPlayerBar {

	private static EntityUnit PLAYER;
	private static boolean VISIBLE=true;
	
	private static Vector2f pos=new Vector2f(0,550);
	private static Vector2f size=new Vector2f(60,10);
	
	private static Model modelGUI=GraphicMain.getModel("defaultBar");
	private static Vector4f colorGUI=new Vector4f(0.1f,0.1f,0.1f,0.85f);
	private static Model modelBar=GraphicMain.getModel("defaultBar");
	private static Vector4f colorHealth=new Vector4f(0.75f,0f,0f,1);
	private static Vector4f colorShield=new Vector4f(0f,0f,0.75f,1f);
	private static Vector4f colorEnergy=new Vector4f(0.5f,0f,0.5f,1f);
	
	private static float healthRatio=1.5f;
	private static float shieldRatio=1.25f;
	private static float energyRatio=1.0f;
	
	public static void setPlayer(EntityUnit player) {
		PLAYER=player;
	}
	
	public static void draw() {
		if(VISIBLE && PLAYER!=null && PLAYER.getHealth()>0) {
			float maxRatio=Math.max(healthRatio,Math.max(shieldRatio,energyRatio));
			float minRatio=Math.min(healthRatio,Math.min(shieldRatio,energyRatio));
			if(modelGUI!=null) {
				modelGUI.draw(new Vector2f(pos.x+size.x*healthRatio,pos.y),size.scale(healthRatio),colorGUI);
				modelGUI.draw(new Vector2f(pos.x+size.x*shieldRatio,pos.y-(size.y*maxRatio+5)*2),size.scale(shieldRatio),colorGUI);
				modelGUI.draw(new Vector2f(pos.x+size.x*energyRatio,pos.y-(size.y*maxRatio+5)*4),size.scale(energyRatio),colorGUI);
			}
			
			float percentHealth=PLAYER.getPercentHealth();
			float percentShield=PLAYER.getPercentShield();
			float percentEnergy=PLAYER.getPercentEnergy();
			
			Vector2f healthSize=new Vector2f((size.x*healthRatio-5)*percentHealth,size.y*healthRatio-5);
			Vector2f shieldSize=new Vector2f((size.x*shieldRatio-5)*percentShield,size.y*shieldRatio-5);
			Vector2f energySize=new Vector2f((size.x*energyRatio-5)*percentEnergy,size.y*energyRatio-5);
			
			Vector2f healthPos=new Vector2f((pos.x+5)+healthSize.x,pos.y);
			Vector2f shieldPos=new Vector2f((pos.x+5)+shieldSize.x,pos.y-(size.y*maxRatio+5)*2);
			Vector2f energyPos=new Vector2f((pos.x+5)+energySize.x,pos.y-(size.y*maxRatio+5)*4);
			
			if(modelBar!=null) {
				modelGUI.draw(healthPos,healthSize,colorHealth);
				modelGUI.draw(shieldPos,shieldSize,colorShield);
				modelGUI.draw(energyPos,energySize,colorEnergy);
			}
			int level=PLAYER.getOwner().getLevel();
			int XP=(int)PLAYER.getOwner().getExp();
			int next=Logic.Logic.getNextLevelXP(level+1);
			GraphicMain.drawString("HP: "+Math.round(PLAYER.getHealth()), new Vector2f(0,550), 2, null);
			GraphicMain.drawString("SH: "+Math.round(PLAYER.getShield()), new Vector2f(0,500), 2, null);
			GraphicMain.drawString("EN: "+Math.round(PLAYER.getEnergy()), new Vector2f(0,460), 2, null);
			GraphicMain.drawString("Niveau: "+level+"\nXP:"+XP+"/"+next, new Vector2f(0,430), 3, null);
		}
	}
}
