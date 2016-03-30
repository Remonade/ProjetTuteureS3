/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import static Graphic.GraphicMain.HEIGHT;
import static Graphic.GraphicMain.WIDTH;
import Graphic.Model;
import Logic.EntityUnit;
import Maths.Vector2f;
import Maths.Vector4f;

public class GUIPlayerBar extends GUI {

	private EntityUnit m_player;
	
	private Model modelGUI=GraphicMain.getModel("defaultBar");
	private Vector4f colorGUI=new Vector4f(0.1f,0.1f,0.1f,0.85f);
	private Model modelBar=GraphicMain.getModel("defaultBar");
	private Vector4f colorHealth=new Vector4f(0.75f,0f,0f,1);
	private Vector4f colorShield=new Vector4f(0f,0f,0.75f,1f);
	private Vector4f colorEnergy=new Vector4f(0.5f,0f,0.5f,1f);
	
	private float healthRatio=1.5f;
	private float shieldRatio=1.25f;
	private float energyRatio=1.0f;
	public GUIPlayerBar() {
		super();
	}
	
	@Override
	public boolean draw() {
		m_player=Logic.Logic.getPlayer();
		if(m_player!=null) {
			Vector2f pos=new Vector2f(WIDTH*0.01f,-HEIGHT*0.04f).add(getPos());
			float maxRatio=Math.max(healthRatio,Math.max(shieldRatio,energyRatio));
			float minRatio=Math.min(healthRatio,Math.min(shieldRatio,energyRatio));
			
			float percentHealth=m_player.getPercentHealth();
			float percentShield=m_player.getPercentShield();
			float percentEnergy=m_player.getPercentEnergy();
			
			Vector2f healthSize=new Vector2f((getSize().x*healthRatio-5)*percentHealth,getSize().y*healthRatio-5);
			Vector2f shieldSize=new Vector2f((getSize().x*shieldRatio-5)*percentShield,getSize().y*shieldRatio-5);
			Vector2f energySize=new Vector2f((getSize().x*energyRatio-5)*percentEnergy,getSize().y*energyRatio-5);
			
			Vector2f healthPos=new Vector2f((pos.x+5)+healthSize.x,pos.y-(getSize().y*maxRatio+5)*0);
			Vector2f shieldPos=new Vector2f((pos.x+5)+shieldSize.x,pos.y-(getSize().y*maxRatio+5)*2);
			Vector2f energyPos=new Vector2f((pos.x+5)+energySize.x,pos.y-(getSize().y*maxRatio+5)*4);
			
			if(modelGUI!=null) {
				modelGUI.draw(new Vector2f(pos.x+getSize().x*healthRatio,pos.y-(getSize().y*maxRatio+5)*0),getSize().scale(healthRatio),colorGUI);
				modelGUI.draw(new Vector2f(pos.x+getSize().x*shieldRatio,pos.y-(getSize().y*maxRatio+5)*2),getSize().scale(shieldRatio),colorGUI);
				modelGUI.draw(new Vector2f(pos.x+getSize().x*energyRatio,pos.y-(getSize().y*maxRatio+5)*4),getSize().scale(energyRatio),colorGUI);
			}
			
			if(modelBar!=null) {
				modelGUI.draw(healthPos,healthSize,colorHealth);
				modelGUI.draw(shieldPos,shieldSize,colorShield);
				modelGUI.draw(energyPos,energySize,colorEnergy);
			}
			int level=m_player.getOwner().getLevel();
			int XP=(int)m_player.getOwner().getExp();
			int next=Logic.Logic.getNextLevelXP(level+1);
			GraphicMain.drawString("HP: "+Math.round(m_player.getHealth()),new Vector2f((pos.x+5),pos.y-(getSize().y*maxRatio+5)*0), 2, null);
			GraphicMain.drawString("SH: "+Math.round(m_player.getShield()),new Vector2f((pos.x+5),pos.y-(getSize().y*maxRatio+5)*2), 2, null);
			GraphicMain.drawString("EN: "+Math.round(m_player.getEnergy()),new Vector2f((pos.x+5),pos.y-(getSize().y*maxRatio+5)*4), 2, null);
			GraphicMain.drawString("Niveau: "+level+"\nXP:"+XP+"/"+next,new Vector2f((pos.x+5),pos.y-(getSize().y*maxRatio+5)*6), 3, null);
			return true;
		}
		return false;
	}
}
