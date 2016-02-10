/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.GraphicMain.STRING_LAYER;
import static Graphic.TextRendering.TextRender.getRenderMod;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class TextRenderKLMNO {
	public static float render_k() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_K() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_l() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		return 3;
	}
	public static float render_L() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		return 3;
	}
	public static float render_m() {
		if(!getRenderMod())
			return 8;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,3f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);

		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(7f,3f, STRING_LAYER);
		glVertex3f(7f,0f, STRING_LAYER);
		glVertex3f(7f,3f, STRING_LAYER);
		return 8;
	}
	public static float render_M() {
		if(!getRenderMod())
			return 8;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,3f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);

		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(7f,3f, STRING_LAYER);
		glVertex3f(7f,0f, STRING_LAYER);
		glVertex3f(7f,3f, STRING_LAYER);
		return 8;
	}
	public static float render_n() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,3f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_N() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,3f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_o() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_O() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		return 5;
	}

}
