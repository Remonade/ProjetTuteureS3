/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.GraphicMain.STRING_LAYER;
import static Graphic.TextRendering.TextRender.getRenderMod;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class TextRenderPQRST {
	public static float render_p() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,-4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_P() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		return 6;
	}
	public static float render_q() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,-4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_Q() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,1f, STRING_LAYER);
		glVertex3f(1f,7f, STRING_LAYER);
		
		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(2f,8f, STRING_LAYER);
		glVertex3f(2f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(5f,1f, STRING_LAYER);
		
		glVertex3f(3.5f,1.5f, STRING_LAYER);
		glVertex3f(5f,-0.5f, STRING_LAYER);
		
		glVertex3f(5f,1f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		return 6;
	}
	public static float render_r() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,3f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_R() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(4f,8f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(5f,7f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		
		glVertex3f(2f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_s() {
		if(!getRenderMod())
			return 5;
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(4f,2f, STRING_LAYER);
		glVertex3f(4f,2f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_S() {
		if(!getRenderMod())
			return 6;
		glVertex3f(5f,8f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_t() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		glVertex3f(1f,1f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(2f,0f, STRING_LAYER);
		glVertex3f(3f,0f, STRING_LAYER);
		glVertex3f(1f,5f, STRING_LAYER);
		glVertex3f(3f,5f, STRING_LAYER);
		return 5;
	}
	public static float render_T() {
		if(!getRenderMod())
			return 6;
		glVertex3f(3f,8f, STRING_LAYER);
		glVertex3f(3f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);
		return 6;
	}
}
