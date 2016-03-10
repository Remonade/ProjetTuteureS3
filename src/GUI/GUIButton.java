
package GUI;

import static GUI.GUIHelp.APPARITION_DELAY;
import static Graphic.GraphicMain.HEIGHT;
import static Graphic.GraphicMain.window;
import Maths.Vector2f;
import Maths.Vector4f;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class GUIButton extends GUI {
	private GUIHelp m_help=null;
	
	public GUIButton() {
		super();
		// texte
		m_labelColor[1]=new Vector4f(1,1,0,1);
		// model
		m_modelColor[1]=new Vector4f(0.75f,0.75f,0.75f,0.75f);
		m_modelName="button";
	}
	public void setHelp(GUIHelp help) {
		m_help=help;
	}
	@Override
	public void onClick() {
		Audio.Audio.playSound("GUI/ButtonClick.ogg");
	}
	@Override
	public void onHover() {
		Audio.Audio.playSound("GUI/ButtonHover.ogg");
	}
	public void drawHelp() {
		if(m_help!=null && m_hover && m_hoverTime>APPARITION_DELAY) {
			DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
			DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		
			glfwGetCursorPos(window, b1, b2);
		
			Vector2f pos = new Vector2f((float)b1.get(0),HEIGHT-(float)b2.get(0)).add(m_help.getSize());
			m_help.setPos(pos);
			//m_help.setLabelText(""+m_hoverTime);
			m_help.draw();
		}
	}
}
