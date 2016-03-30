/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.GraphicMain.STRING_LAYER;
import static Graphic.TextRendering.TextRender.getRenderMod;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class TextRenderFGHIJ {
	public static float render_f() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(2f,8f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(3f,4f, STRING_LAYER);
		glVertex3f(2f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		return 5;
	}
	public static float render_F() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);
		
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(3f,4f, STRING_LAYER);
		return 6;
	}
	public static float render_g() {
		if(!getRenderMod())
			return 5;
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,-3f, STRING_LAYER);

		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);

		glVertex3f(2f,-3f, STRING_LAYER);
		glVertex3f(5f,-3f, STRING_LAYER);
		return 6;
	}
	public static float render_G() {
		if(!getRenderMod())
			return 6;
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(2f,8f, STRING_LAYER);
		glVertex3f(2f,8f, STRING_LAYER);
		glVertex3f(1f,7f, STRING_LAYER);
		
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		
		glVertex3f(1f,1f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(5f,1f, STRING_LAYER);
		
		glVertex3f(5f,1f, STRING_LAYER);
		glVertex3f(5f,3f, STRING_LAYER);
		glVertex3f(5f,3f, STRING_LAYER);
		glVertex3f(3f,3f, STRING_LAYER);
		return 6;
	}
	public static float render_h() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);

		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		return 6;
	}
	public static float render_H() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);

		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);
		return 6;
	}
	public static float render_i() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,3f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,5f, STRING_LAYER);
		return 3;
	}
	public static float render_I() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,6f, STRING_LAYER);
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		return 3;
	}
	public static float render_j() {
		if(!getRenderMod())
			return 3;
		glVertex3f(1f,-3f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(2f,3f, STRING_LAYER);
		
		glVertex3f(2f,4f, STRING_LAYER);
		glVertex3f(2f,5f, STRING_LAYER);
		return 3;
	}
	public static float render_J() {
		if(!getRenderMod())
			return 6;
		glVertex3f(3f,8f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(4f,1f, STRING_LAYER);
		glVertex3f(4f,1f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		return 6;
	}

}
