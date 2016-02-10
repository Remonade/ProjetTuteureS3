/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import Graphic.TextRendering.TextRender;
import Maths.Vector2f;
import java.util.ArrayList;

public class GUIHelp extends GUI{
	protected int m_lineCount=0;
	
	public GUIHelp() {
		super();
		m_modelName="button";
	}
	@Override
	public void setLabelText(String text) {
		super.setLabelText(text);
		ArrayList<Object[]> lines=TextRender.getLines(m_labelText,m_labelSize,m_size.x*2);
		if(lines!=null) {
			m_lineCount=lines.size();
			m_size.y=m_lineCount*m_labelSize*TextRender.LINE_HEIGHT;
		}
	}
	@Override
	public void setSize(float w, float h) {
		super.setSize(w,h);
		ArrayList<Object[]> lines=TextRender.getLines(m_labelText,m_labelSize,m_size.x*2);
		if(lines!=null) {
			m_lineCount=lines.size();
			m_size.y=m_lineCount*m_labelSize*TextRender.LINE_HEIGHT;
		}
	}
	@Override
	public void setLabelSize(float size) {
		super.setLabelSize(size);
		ArrayList<Object[]> lines=TextRender.getLines(m_labelText,m_labelSize,m_size.x*2);
		if(lines!=null) {
			m_lineCount=lines.size();
			m_size.y=m_lineCount*m_labelSize*TextRender.LINE_HEIGHT;
		}
	}
	@Override
	public boolean draw() {
		if(m_visible) {
			int mode=0;
			if(m_model==null && !m_modelName.equals(""))
				m_model=GraphicMain.getModel(m_modelName);
			if(m_model!=null) {
				Vector2f modelSize=m_size.add(new Vector2f(m_padding,0));
				m_model.draw(m_position,modelSize,m_modelColor[mode]);
			}
			if(m_lineCount!=0) {
				Vector2f labelPos=m_position.add(new Vector2f(0,m_size.y/2));
				TextRender.drawStringJustify(getLabelText(),labelPos,m_labelSize,m_labelColor[mode],m_size.x*2);
			}
			return true;
		}
		return false;
	}

	public static float APPARITION_DELAY=0.65f;
}
