/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import GUI.GUILabel;
import Tools.Input;
import static Tools.Input.BIND;
import static Tests.Main.setGameState;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;

public class GameStateBinding extends GameState {
	private int m_binding;
	
	public GameStateBinding(){
		super();
		//m_showCursor=true;
		GUILabel l=new GUILabel(GUILabel.ALIGN_CENTER);
		l.setPos(400,400);
		l.setSize(100,100);
		l.setLabelSize(3);
		l.setLabelText("Press the key you want to bind.");
		m_GUI.add(l);
	}
	public void setBinding(int bind) {
		m_binding=bind;
	}
	public void bind() {
		int bind=-1;
		for(int i=0;i<GLFW_KEY_LAST;i++) {
			if(Input.isPushed(i)) {
				bind=i;
				break;
			}
		}
		if(bind!=-1) {
			BIND[m_binding]=bind;
			setGameState(5);
		}
	}
	@Override
	public void inputHandler() {
		super.inputHandler();
		bind();
	}
}