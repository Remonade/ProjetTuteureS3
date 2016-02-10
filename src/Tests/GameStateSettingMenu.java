/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUIBindingButton;
import GUI.GUIMenuButton;
import static Graphic.GraphicMain.resetScreen;
import static Graphic.GraphicMain.setCameraGUI;
import static Graphic.GraphicMain.updateScreen;
import static Tests.Input.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class GameStateSettingMenu extends GameState {
	public GameStateSettingMenu() {
		super();
		m_showCursor=true;
		
		/*GUICheckBox cb;
		cb=new GUICheckBox();
		cb.setPos(600,350);
		cb.setSize(150,15);
		cb.setLabelSize(2);
		//cb.setLabelColor(0,0,1,0,1);
		cb.setLabelText("Particle: ");
		cb.setRef("GUI_CB_PARTICLES");
		m_GUI.add(cb);*/
		
		int h=20;
		int w=150;
		int x=200;
		int y=600;
		GUIBindingButton b;
		
		b=new GUIBindingButton(BIND_UP);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Up Key");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_DOWN);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Down Key");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_LEFT);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Left Key");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_RIGHT);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Right Key");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_JUMP);
		b.setPos(x,y-=75);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Jump Key");
		//b.setModelColor(0,1,1,1,1);
		//b.setModelColor(1,1,1,1,1);
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_SHOOT);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Shoot Key");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_SPELL_1);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Spell 1");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_SPELL_2);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Spell 2");
		m_GUI.add(b);
		
		b=new GUIBindingButton(BIND_SPELL_3);
		b.setPos(x,y-=50);
		b.setSize(w,h);
		b.setLabelSize(2);
		//b.setLabelColor(0,0,1,0,1);
		b.setLabelText("Spell 3");
		m_GUI.add(b);
		
		GUIMenuButton m=new GUIMenuButton(Main.STATE_MAIN_MENU);
		m.setLabelText("retour");
		m.setPos(600,50);
		m.setSize(100,40);
		m.setLabelSize(4);
		m_GUI.add(m);
	}
	
	@Override
	public void execute() {
		inputHandler();
		resetScreen();
		setCameraGUI();
		
		drawGUI();
		
		updateScreen();
	}
	@Override
	public void inputHandler() {
		super.inputHandler();
		if(Input.isPushed(GLFW_KEY_ESCAPE))
			Main.setGameState(Main.STATE_MAIN_MENU);
	}
}