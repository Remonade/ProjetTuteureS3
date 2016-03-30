/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUITexture;
import static Graphic.GraphicMain.window;
import static Tests.Main.setGameState;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;

public class GameStateLogo  extends GameState {
	
	private float m_time;
	private boolean m_in;
	
	// graphic part
	private float m_inDuration=1f;
	private float m_outDuration=0.75f;
	
	public GameStateLogo() {
		super();
		
		GUITexture t=new GUITexture("quad");
		t.setPos(0.5f,0.5f);
		t.setSize(0.5f,0.5f);
		t.setModelColor(0, 1, 1, 1, 1);
		m_GUI.add(t);
		
		t=new GUITexture("logo");
		t.setPos(0.5f,0.5f);
		t.setSize(300,300);
		m_GUI.add(t);
	}
	@Override
	public void onEnter() {
		glfwSetInputMode(window,GLFW_CURSOR,GLFW_CURSOR_DISABLED);
		Audio.Audio.playSound("GUI/OTSU.ogg");
		m_time=0;
		m_in=true;
	}
	@Override
	public void onLeave() {
		glfwSetInputMode(window,GLFW_CURSOR,GLFW_CURSOR_NORMAL);
	}
	
	@Override
	public void execute() {
		if(m_in) {
			if(m_time==0)
				Logic.Logic.DELTA_TIME=0.01;
			m_time+=Logic.Logic.DELTA_TIME;
			m_GUI.get(1).setModelColor(0, 1, 1, 1, m_time/m_inDuration);
			if(m_time>m_inDuration*1.25) {
				m_in=false;
				m_time=m_outDuration;
			}
		} else {
			m_time-=Logic.Logic.DELTA_TIME;
			m_GUI.get(1).setModelColor(0, 1, 1, 1, Math.max(0,m_time/m_outDuration));
			if(m_time<-0.35)
				setGameState(Main.STATE_MAIN_MENU);
		}
	}
	
	@Override
	public void drawCore() {
	}
	
}
