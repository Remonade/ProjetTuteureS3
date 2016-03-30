/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import static Graphic.TextRendering.TextRender.drawStringCentered;

public class GUICheckBox extends GUIButton {
	private Boolean m_value;
	
	public void setValue(boolean value) {
		m_value=value;
	}
	public Boolean getValue() {
		return m_value;
	}
	
	public GUICheckBox() {
		super();
		this.m_value = false;
	}
	@Override
	public void onMouseClick() {
		super.onMouseClick();
		m_value=!m_value;
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
				GUICheckBox temp=(GUICheckBox)this;
				if(temp.getValue())
					drawStringCentered(m_labelText+" : true",getPos(),getLabelSize(),m_labelColor[mode]);
				else
					drawStringCentered(m_labelText+" : false",getPos(),getLabelSize(),m_labelColor[mode]);
			}
			drawHelp();
			return true;
		}
		return false;
	}
}
