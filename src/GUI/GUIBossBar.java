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

public class GUIBossBar extends GUI {

	private EntityUnit m_boss;
	private boolean m_visible=true;

	
	private Model modelGUI=GraphicMain.getModel("defaultBar");
	private Vector4f colorGUI=new Vector4f(0.5f,0.5f,0.5f,1);
	private Model modelBar=GraphicMain.getModel("defaultBar");
	private Vector4f colorHealth=new Vector4f(0.75f,0f,0f,1);
	private Vector4f colorShield=new Vector4f(0f,0f,0.75f,0.75f);
	
	public void setBoss(EntityUnit boss) {
		m_boss=boss;
	}
	public GUIBossBar() {
		super();
	}
	@Override
	public boolean draw() {
		if(m_visible && m_boss!=null && m_boss.getHealth()>0) {
			if(modelGUI!=null)
				modelGUI.draw(getPos(),getSize(),colorGUI);
			
			float percentHealth=m_boss.getPercentHealth();
			float percentShield=m_boss.getPercentShield();
			
			Vector2f healthSize=new Vector2f((getSize().x-5)*percentHealth,getSize().y-5);
			Vector2f shieldSize=new Vector2f((getSize().x-5)*percentShield,getSize().y-5);
			
			if(modelBar!=null) {
				modelGUI.draw(getPos(),healthSize,colorHealth);
				modelGUI.draw(getPos(),shieldSize,colorShield);
			}
			return true;
		}
		return false;
	}
}
