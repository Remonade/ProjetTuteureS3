/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import Graphic.Model;
import Logic.Buff.Buff;
import Logic.EntityUnit;
import Logic.Spell.Spell;
import Maths.Vector2f;
import Maths.Vector4f;
import java.util.ArrayList;

public class GUIBuffBar {

	private static EntityUnit PLAYER;
	private static ArrayList<Buff> buffs;
	private static boolean VISIBLE=true;
	
	private static Vector2f size=new Vector2f(20,20);
	private static Vector2f pos=new Vector2f(800-(size.x+10),600-(size.y+10));
	private static float offsetY=size.y*2;
	
	public static void setPlayer(EntityUnit p) {
		PLAYER=p;
		buffs=PLAYER.getBuffList();
	}
	public static void draw() {
		int spellCount=buffs.size();
		for(int i=0;i<spellCount;i++) {
			Buff b=buffs.get(i);
			
			Vector4f color=new Vector4f(1,1,1,0.75f);
			
			Vector2f bPos=new Vector2f(pos.x,pos.y-i*(offsetY+5));
			Model.renderTexture(b.getIcone(), bPos, size, color);
			GraphicMain.drawString(b.getName(), bPos.subtract(new Vector2f(size.x,-size.y/2)), 0.8f, new Vector4f(0,0,0,1));
			float duration=((float)(int)(b.getRemainingDuration()*10))/10;
			GraphicMain.drawString(""+duration, bPos.subtract(new Vector2f(size.x/2,size.y/2)), 1.5f, new Vector4f(0,0,0,1));
		}
	}
}
