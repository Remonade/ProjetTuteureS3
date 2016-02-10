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

public class GUIBossBar {

	private static EntityUnit BOSS;
	private static boolean VISIBLE=true;
	
	
	private static Vector2f pose=new Vector2f(400,550);
	private static Vector2f size=new Vector2f(200,15);
	
	private static Model modelGUI=GraphicMain.getModel("defaultBar");
	private static Vector4f colorGUI=new Vector4f(0.5f,0.5f,0.5f,1);
	private static Model modelBar=GraphicMain.getModel("defaultBar");
	private static Vector4f colorHealth=new Vector4f(0.75f,0f,0f,1);
	private static Vector4f colorShield=new Vector4f(0f,0f,0.75f,0.75f);
	
	public static void setBoss(EntityUnit boss) {
		BOSS=boss;
	}
	
	public static void draw() {
		if(VISIBLE && BOSS!=null && BOSS.getHealth()>0) {
			if(modelGUI!=null)
				modelGUI.draw(pose,size,colorGUI);
			
			float percentHealth=BOSS.getPercentHealth();
			float percentShield=BOSS.getPercentShield();
			
			Vector2f healthSize=new Vector2f((size.x-5)*percentHealth,size.y-5);
			Vector2f shieldSize=new Vector2f((size.x-5)*percentShield,size.y-5);
			
			if(modelBar!=null) {
				modelGUI.draw(pose,healthSize,colorHealth);
				modelGUI.draw(pose,shieldSize,colorShield);
			}
		}
	}
}
