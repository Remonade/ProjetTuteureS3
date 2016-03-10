/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUIHelp;
import GUI.GUILabel;
import GUI.GUIMenuButton;
import static Graphic.GraphicMain.window;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_3;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class GameStateMainMenu extends GameState {
	public GameStateMainMenu() {
		super();
		GUILabel l=new GUILabel(GUILabel.ALIGN_CENTER);
		l.setLabelText("Main Menu");
		l.setPos(0.5f,0.8f);
		l.setSize(0,0.1f);
		l.setLabelSize(0.2f);
		m_GUI.add(l);
		
		GUIHelp h;
		
		h=new GUIHelp();
		h.setLabelText("Will send you back to the game.");
		h.setLabelSize(0.1f);
		h.setSize(0.5f,0.05f);
		h.setPos(0,200f);
		h.setModelColor(0,0.5f,1,0.5f,1);
		
		GUIMenuButton m=new GUIMenuButton(Main.STATE_LEVEL);
		m.setLabelText("Play");
		m.setPos(0.5f,0.6f);
		m.setSize(0.15f,0.05f);
		m.setLabelSize(0.1f);
		m.setHelp(h);
		m_GUI.add(m);
		
		h=new GUIHelp();
		h.setLabelText("Will open the settings menu.");
		h.setLabelSize(0.1f);
		h.setSize(0.5f,0.05f);
		h.setPos(0,200);
		h.setModelColor(0,0.5f,1,0.5f,1);
		
		m=new GUIMenuButton(Main.STATE_SETTING_MENU);
		m.setLabelText("Settings");
		m.setPos(0.5f,0.4f);
		m.setSize(0.15f,0.05f);
		m.setLabelSize(0.1f);
		m.setHelp(h);
		m_GUI.add(m);
		
		h=new GUIHelp();
		h.setLabelText("Will close the game.");
		h.setLabelSize(0.1f);
		h.setSize(0.5f,0.05f);
		h.setPos(0,200);
		h.setModelColor(0,0.5f,1,0.5f,1);
		
		m=new GUIMenuButton(-1);
		m.setLabelText("Quit");
		m.setPos(0.5f,0.2f);
		m.setSize(0.15f,0.05f);
		m.setLabelSize(0.1f);
		m.setHelp(h);
		m_GUI.add(m);
	}
	@Override
	public void inputHandler() {
		super.inputHandler();
		if(Input.isPushed(GLFW_KEY_1))
			Main.changeGameState(Main.STATE_LEVEL);
		if(Input.isPushed(GLFW_KEY_2))
			Main.changeGameState(Main.STATE_SETTING_MENU);
		if(Input.isPushed(GLFW_KEY_3))
			glfwSetWindowShouldClose(window, GL_TRUE);
	}
}
