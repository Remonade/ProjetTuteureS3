/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import static Graphic.TextRendering.TextRender.drawStringRight;
import Maths.Vector2f;
import Maths.Vector4f;

public class GUICheckBox extends GUIButton {
	private Boolean m_value;
	
	public Boolean getValue() {
		return m_value;
	}
	
	public GUICheckBox() {
		super();
		this.m_value = false;
	}
	@Override
	public void onClick() {
		super.onClick();
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
				m_model.draw(m_position,m_size,m_modelColor[mode]);
			if(!m_labelText.equals("")) {
				GUICheckBox temp=(GUICheckBox)this;
				drawStringRight(m_labelText,getPosLabel(-1),m_labelSize,m_labelColor[mode]);
				if(temp.getValue())
					drawStringLeft("true",getPosLabel(1),m_labelSize,m_labelColor[mode]);
				else
					drawStringLeft("false",getPosLabel(1),m_labelSize,m_labelColor[mode]);
			}
			drawHelp();
			return true;
		}
		return false;
	}

	private void drawStringLeft(String atrue, Vector2f posLabel, float m_labelSize, Vector4f m_labelColor) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
