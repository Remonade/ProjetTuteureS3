/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.GraphicMain.STRING_LAYER;
import static Graphic.TextRendering.TextRender.getRenderMod;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class TextRenderSymbols {
	public static float render_cedil() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		
		glVertex3f(3f,0f, STRING_LAYER);
		glVertex3f(3.5f,-1.5f, STRING_LAYER);
		glVertex3f(3.5f,-1.5f, STRING_LAYER);
		glVertex3f(2f,-3f, STRING_LAYER);
		return 6;
	}
	public static float render_dot() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		return 3;
	}
	public static float render_dot2() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(1f,3f, STRING_LAYER);
		return 3;
	}
	public static float render_excla() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		return 3;
	}
	public static float render_slash() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);
		return 6;
	}
	public static float render_minus() {
		if(!getRenderMod())
			return 4;
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(3f,2f, STRING_LAYER);
		return 4;
	}
	public static float render_plus() {
		if(!getRenderMod())
			return 4;
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(3f,2f, STRING_LAYER);
		glVertex3f(2f,1f, STRING_LAYER);
		glVertex3f(2f,3f, STRING_LAYER);
		return 4;
	}
	public static float render_underscore() {
		if(!getRenderMod())
			return 7;
		glVertex3f(1f,-0.5f, STRING_LAYER);
		glVertex3f(6f,-0.5f, STRING_LAYER);
		return 7;
	}
	public static float render_apostrophe() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(2f,6f, STRING_LAYER);
		return 3;
	}
	public static float render_quote() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(1f,6f, STRING_LAYER);
		
		glVertex3f(2f,7f, STRING_LAYER);
		glVertex3f(2f,6f, STRING_LAYER);
		return 3;
	}

}
