
package GUI;

import Graphic.GraphicMain;
import static Graphic.TextRendering.TextRender.drawStringRight;
import Maths.Vector2f;

public class GUIInput extends GUI {
	protected boolean m_focus;
	public GUIInput() {
		super();
		m_focus=false;
		setLabelText("Input");
	}
	@Override
	public void onMouseClick() {
		m_focus=true;
	}
	@Override
	public void tryOnMouseUp() {
		super.tryOnMouseUp();
		m_focus=false;
	}
	public void inputHandler() {
		
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
				drawStringRight(m_labelText,getPos().add(new Vector2f(-getSize().x,0)),getLabelSize(),m_labelColor[mode]);
			}
			return true;
		}
		return false;
	}
}
