/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUI;
import static Graphic.GraphicMain.setCameraGUI;
import static Graphic.GraphicMain.window;
import static Tests.Input.BIND_MOUSE;
import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F4;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_ALT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_ALT;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class GameState {
	public ArrayList<GUI> getGUI() {
		return m_GUI;
	}
	public GUI getGUI(String ref) {
		for(GUI g:m_GUI)
			if(g.getRef().equals(ref))
				return g;
		return null;
	}
	public GameState() {
		m_GUI=new ArrayList<>();
	}
	public void execute() {
		inputHandler();
	}
	public void draw() {
		drawCore();
		drawGUI();
	}
	public void drawCore() {
	}
	public void inputHandler() {
		Input.updateInput();
		if((Input.isPressed(GLFW_KEY_RIGHT_ALT) || Input.isPressed(GLFW_KEY_LEFT_ALT)) && Input.isPushed(GLFW_KEY_F4))
			glfwSetWindowShouldClose(window, GL_TRUE);
		for(GUI g:m_GUI)
			g.tryHover();
		if(Input.isClicked(BIND_MOUSE)) {
			for(GUI g:m_GUI)
				g.tryClick();
		}
	}
	public void destroy() {
		
	}
	public void onEnter() {
	
	}
	public void onLeave() {
	
	}
	public void drawGUI() {
		setCameraGUI();
		for(GUI g:m_GUI)
			g.draw();
	}
	protected ArrayList<GUI> m_GUI;
}
