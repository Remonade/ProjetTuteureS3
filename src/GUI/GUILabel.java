/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import static Graphic.TextRendering.TextRender.drawStringCentered;
import static Graphic.TextRendering.TextRender.drawStringJustify;
import static Graphic.TextRendering.TextRender.drawStringLeft;
import static Graphic.TextRendering.TextRender.drawStringRight;

public class GUILabel extends GUI{
	public final static int ALIGN_LEFT=0;
	public final static int ALIGN_RIGHT=1;
	public final static int ALIGN_CENTER=2;
	public final static int ALIGN_JUSTIFY=3;
	
	private int m_textAlign=ALIGN_CENTER;
	
	public GUILabel(int align) {
		super();
		m_textAlign=align;
	}
	
	@Override
	public boolean draw() {
		if(super.draw()) {
			int mode=0;
			if(m_model==null && !m_modelName.equals(""))
				m_model=GraphicMain.getModel(m_modelName);
			if(m_model!=null)
				m_model.draw(m_position,m_size,m_modelColor[mode]);
			if(!m_labelText.equals("")) {
				switch(m_textAlign) {
					case ALIGN_RIGHT:
						drawStringRight(m_labelText,m_position,m_labelSize,m_labelColor[mode]);
						break;
					case ALIGN_CENTER:
						drawStringCentered(m_labelText,m_position,m_labelSize,m_labelColor[mode]);
						break;
					case ALIGN_JUSTIFY:
						drawStringJustify(m_labelText,m_position,m_labelSize,m_labelColor[mode],m_size.x*2);
						break;
					default:
						drawStringLeft(m_labelText,m_position,m_labelSize,m_labelColor[mode]);
						break;
				}
			}
			return true;
		}
		return false;
	}
}
