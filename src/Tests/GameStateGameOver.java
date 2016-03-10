/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUI;
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
		GUI g=new GUI();
		g.setPos(400,500);
		g.setSize(400,75);
		g.setLabelText("Game Over");
		g.setLabelSize(10);
		m_GUI.add(g);
		
		GUIMenuButton m=new GUIMenuButton(Main.STATE_LEVEL);
		m.setLabelText("recommencer");
		m.setPos(400,400);
		m.setSize(175,30);
		m.setLabelSize(3);
		m_GUI.add(m);
		
		m=new GUIMenuButton(-1);
		m.setLabelText("quitter");
		m.setPos(400,200);
		m.setSize(175,30);
		m.setLabelSize(3);
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
