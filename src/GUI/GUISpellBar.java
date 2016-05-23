/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import Graphic.Model;
import Logic.EntityUnit;
import Logic.Spell.Spell;
import Maths.Vector2f;
import Maths.Vector4f;
import java.util.ArrayList;

public class GUISpellBar {
	private static EntityUnit PLAYER;
	private static ArrayList<Spell> spells;
	private static boolean VISIBLE=true;
	
	private static Vector2f size=new Vector2f(35,35);
	private static Vector2f pos=new Vector2f(size.x+10,size.y+10);
	private static float offsetX=size.x*2;
	public static void setPlayer(EntityUnit p) {
		PLAYER=p;
		spells=PLAYER.getSpellList();
	}
	public static void draw() {
		int spellCount=spells.size();
		for(int i=0;i<spellCount;i++) {
			Spell s=spells.get(i);
			
			Vector4f color;
			if(s.getEnergyCost()>PLAYER.getEnergy())
				color=new Vector4f(0.25f,0,0.5f,1f);
			else
				color=new Vector4f(1,1,1,1f);
			
			Vector2f sPos=new Vector2f(pos.x+i*size.x+i*offsetX,pos.y);
			Model iconeModel = s.getIconeModel();
			if(iconeModel != null)
				iconeModel.draw(sPos, size, color);
			GraphicMain.drawString(s.getName(), sPos.subtract(new Vector2f(size.x,-size.y/2)), 1, new Vector4f(0,0,0,1));
			
			int charge=s.getRemainingCharge();
			if(charge>-1)
				GraphicMain.drawString(""+charge, sPos.subtract(new Vector2f(-size.x/2,-size.y/2)), 1, new Vector4f(0,0,0,1));
			
			float cooldownPercent=s.getCooldownPercent();
			if(cooldownPercent>0.0f) {
				float time=((float)(int)(s.getCurrentCooldown()*10))/10;
				GraphicMain.drawString(""+time, sPos.subtract(new Vector2f(size.x/2,size.y/2)), 2, new Vector4f(0,0,0,1));
				
				Vector2f sSize=new Vector2f(size.x,size.y*cooldownPercent);
				sPos=new Vector2f(sPos.x,sPos.y-size.y+sSize.y);
				Model.renderTexture("", sPos, sSize, new Vector4f(0.5f,0f,0f,0.5f));
			}
		}
	}
}
