/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import static Graphic.TextRendering.TextRender.drawStringCentered;
import Maths.Vector4f;

public class GUITexture extends GUI {
	
	
	public GUITexture(String model) {
		super();
		// texte
		m_labelColor[1]=new Vector4f(1,1,0,1);
		// model
		m_modelColor[1]=new Vector4f(0.75f,0.75f,0.75f,0.75f);
		m_modelName=model;
	}
	@Override
	public boolean draw() {
		if(m_visible) {
			int mode=0;
			if(m_model==null && !m_modelName.equals(""))
				m_model=GraphicMain.getModel(m_modelName);
			if(m_model!=null)
				m_model.draw(getPos(),getSize(),m_modelColor[mode]);
			if(!m_labelText.equals("")) {
				drawStringCentered(m_labelText,getPos(),m_labelSize,m_labelColor[mode]);
			}
			return true;
		}
		return false;
	}
}
