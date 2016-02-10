/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Graphic.GraphicMain;
import static Graphic.GraphicMain.window;
import static Graphic.TextRendering.TextRender.drawStringCentered;
import Tests.Main;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 *
 * @author Zekiel
 */
public class GUIMenuButton extends GUIButton {
	private int m_gameState;
    public GUIMenuButton(int gamestate){
        super();
		m_gameState=gamestate;
    }
    public void setGameState(int gamestate) {
		m_gameState=gamestate;
	}
	public int getGameState() {
		return m_gameState;
	}
    @Override
	public void onClick() {
		super.onClick();
		if(m_gameState==-1)
			glfwSetWindowShouldClose(window, GL_TRUE);
		else
			Main.setGameState(m_gameState);
	}
	@Override
	public boolean draw() {
		if(super.draw()) {
			int mode=0;
			if(m_hover)
				mode=1;
			if(m_model==null && !m_modelName.equals(""))
				m_model=GraphicMain.getModel(m_modelName);
			if(m_model!=null)
				m_model.draw(m_position,m_size,m_modelColor[mode]);
			if(!m_labelText.equals("")) {
				drawStringCentered(m_labelText,m_position,m_labelSize,m_labelColor[mode]);
			}
			drawHelp();
			return true;
		}
		return false;
	}
}
