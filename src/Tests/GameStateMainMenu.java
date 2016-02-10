/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUIHelp;
import GUI.GUILabel;
import GUI.GUIMenuButton;
import static Graphic.GraphicMain.resetScreen;
import static Graphic.GraphicMain.setCameraGUI;
import static Graphic.GraphicMain.updateScreen;
import static Graphic.GraphicMain.window;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_3;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class GameStateMainMenu extends GameState {
	public GameStateMainMenu() {
		super();
		m_showCursor=false;
		GUILabel l=new GUILabel(GUILabel.ALIGN_CENTER);
		l.setLabelText("Menu Principal");
		l.setPos(400,500);
		l.setLabelSize(7);
		m_GUI.add(l);
		
		GUIHelp h;
		
		l=new GUILabel(GUILabel.ALIGN_LEFT);
		l.setLabelText("abcdefghijklmnopqrstuvwxyz");
		l.setPos(0,100);
		l.setLabelSize(3);
		m_GUI.add(l);
		
		l=new GUILabel(GUILabel.ALIGN_LEFT);
		l.setLabelText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		l.setPos(0,50);
		l.setLabelSize(3);
		m_GUI.add(l);
		
		h=new GUIHelp();
		h.setLabelText("Will send you back to the game.");
		h.setLabelSize(2);
		h.setSize(125,0);
		h.setPos(0,200);
		h.setModelColor(0,0.5f,1,0.5f,1);
		
		GUIMenuButton m=new GUIMenuButton(Main.STATE_LEVEL);
		m.setLabelText("play");
		m.setPos(400,400);
		m.setSize(125,30);
		m.setLabelSize(3);
		m.setHelp(h);
		m_GUI.add(m);
		
		h=new GUIHelp();
		h.setLabelText("Will open the settings menu.");
		h.setLabelSize(2);
		h.setSize(125,0);
		h.setPos(0,200);
		h.setModelColor(0,0.5f,1,0.5f,1);
		
		m=new GUIMenuButton(Main.STATE_SETTING_MENU);
		m.setLabelText("settings");
		m.setPos(400,300);
		m.setSize(125,30);
		m.setLabelSize(3);
		m.setHelp(h);
		m_GUI.add(m);
		
		h=new GUIHelp();
		h.setLabelText("Will close the game.");
		h.setLabelSize(2);
		h.setSize(125,0);
		h.setPos(0,200);
		h.setModelColor(0,0.5f,1,0.5f,1);
		
		m=new GUIMenuButton(-1);
		m.setLabelText("quit");
		m.setPos(400,200);
		m.setSize(125,30);
		m.setLabelSize(3);
		m.setHelp(h);
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
		if(Input.isPushed(GLFW_KEY_1))
			Main.setGameState(Main.STATE_LEVEL);
		if(Input.isPushed(GLFW_KEY_2))
			Main.setGameState(Main.STATE_SETTING_MENU);
		if(Input.isPushed(GLFW_KEY_3))
			glfwSetWindowShouldClose(window, GL_TRUE);
	}
}
