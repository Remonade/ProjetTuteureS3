/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUILabel;
import GUI.GUIMenuButton;
import static Graphic.GraphicMain.window;
import Logic.Logic;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class GameStateGameOver  extends GameState {
	
	public GameStateGameOver() {
		super();
		GUILabel g=new GUILabel(GUILabel.ALIGN_CENTER);
		g.setLabelText("Game Over");
		g.setPos(0.5f,0.8f);
		g.setSize(0,0.1f);
		g.setLabelSize(0.2f);
		m_GUI.add(g);
		
		GUIMenuButton m=new GUIMenuButton(Main.STATE_LEVEL);
		m.setLabelText("recommencer");
		m.setPos(0.5f,0.5f);
		m.setSize(0.2f,0.05f);
		m.setLabelSize(0.1f);
		m_GUI.add(m);
		
		m=new GUIMenuButton(-1);
		m.setLabelText("quitter");
		m.setPos(0.5f,0.3f);
		m.setSize(0.2f,0.05f);
		m.setLabelSize(0.1f);
		m_GUI.add(m);
	}
	
	@Override
	public void onEnter() {
		Logic.generateRun(15);
	}
	@Override
	public void inputHandler() {
		super.inputHandler();
		if(Input.isPushed(GLFW_KEY_1))
			Main.changeGameState(Main.STATE_LEVEL);
		if(Input.isPushed(GLFW_KEY_2))
			glfwSetWindowShouldClose(window, GL_TRUE);
	}
}
