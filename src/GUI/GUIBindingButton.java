/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import static Graphic.TextRendering.TextRender.drawStringCentered;
import Tests.GameStateBinding;
import Tests.Input;
import static Tests.Input.BIND;
import Tests.Main;

public class GUIBindingButton extends GUIButton {
	private int m_key;
	
	public int getKey() {
		return m_key;
	}
	public String getKeyName() {
		return Input.getKeyName(BIND[getKey()]);
	}
	
	public GUIBindingButton(int key) {
		super();
		m_key=key;
		m_modelName="button";
	}
	@Override
	public void onMouseClick() {
		super.onMouseClick();
		Main.setGameState(6);
		GameStateBinding g=(GameStateBinding)Main.getGameState();
		g.setBinding(m_key);
	}
	@Override
	public boolean draw() {
		if(m_visible) {
			int mode=0;
			if(m_hover)
				mode=1;
			if(m_model==null && !m_modelName.equals(""))
				m_model=GraphicMain.getModel(m_modelName);
			if(m_model!=null)
				m_model.draw(getPos(),getSize(),m_modelColor[mode]);
			if(!m_labelText.equals("")) {
				drawStringCentered(m_labelText+" : "+this.getKeyName(),getPos(),getLabelSize(),m_labelColor[mode]);
				//drawString(this.getKeyName(),getPos(),m_labelSize,m_labelColor[mode]);
			}
			drawHelp();
			return true;
		}
		return false;
	}
}
