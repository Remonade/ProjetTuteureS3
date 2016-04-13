/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import Graphic.Model;
import static Graphic.TextRendering.TextRender.drawStringLeft;
import Maths.Vector2f;
import static Tools.Input.getMouseX;

public class GUIInputSlider extends GUIInput {
	
	protected float m_value;
	protected float m_minRange;
	protected float m_maxRange;
	
	protected String m_cursorName;
	protected Model m_cursorModel;
	
	public GUIInputSlider() {
		super();
		m_value=0.5f;
		m_minRange=0;
		m_maxRange=100;
		m_modelName="sliderBar";
		m_cursorName="sliderCursor";
	}
	@Override
	public void inputHandler() {
		if(m_focus) {
			float mouseX=(float) getMouseX();
			if(mouseX<getPos().x-getSize().x) {
				m_value=0;
			} else if(mouseX>getPos().x+getSize().x) {
				m_value=1;
			} else {
				float dist=Math.abs(mouseX-(getPos().x-getSize().x));
				m_value=dist/(getSize().x*2);
			}
		}
	}
	
	@Override
	public boolean draw() {
		if(super.draw()) {
			int mode=0;
			float output=m_minRange+m_value*(m_maxRange-m_minRange);
			if(m_cursorModel==null && !m_cursorName.equals(""))
				m_cursorModel=GraphicMain.getModel(m_cursorName);
			if(m_cursorModel!=null)
				m_cursorModel.draw(getPos().add(new Vector2f(-getSize().x+m_value*getSize().x*2,0)),new Vector2f(5,getSize().y),m_modelColor[mode]);
			drawStringLeft(" "+(int)output,getPos().add(new Vector2f(getSize().x,0)),getLabelSize(),m_labelColor[mode]);
			return true;
		}
		return false;
	}
	
}
