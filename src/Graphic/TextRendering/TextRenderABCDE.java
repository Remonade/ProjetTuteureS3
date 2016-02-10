/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.GraphicMain.STRING_LAYER;
import static Graphic.TextRendering.TextRender.getRenderMod;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class TextRenderABCDE {
	public static float render_a() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,5f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(1f,3f, STRING_LAYER);
		glVertex3f(5f,3f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);

		glVertex3f(1f,3f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_A() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,5f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(1f,3f, STRING_LAYER);
		glVertex3f(5f,3f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);

		glVertex3f(1f,3f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,5f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_b() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);

		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_B() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,8f, STRING_LAYER);
		
		glVertex3f(1f,8f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);

		glVertex3f(1f,7f, STRING_LAYER);
		glVertex3f(5f,3f, STRING_LAYER);
		
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		
		glVertex3f(5f,2f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_c() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);

		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_C() {
		if(!getRenderMod())
			return 6;
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);

		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_d() {
		if(!getRenderMod())
			return 6;
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);

		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_D() {
		if(!getRenderMod())
			return 6;
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(5f,8f, STRING_LAYER);

		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,0f, STRING_LAYER);
		return 6;
	}
	public static float render_e() {
		if(!getRenderMod())
			return 6;
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,2f, STRING_LAYER);
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);

		glVertex3f(5f,2f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		return 6;
	}
	public static float render_E() {
		if(!getRenderMod())
			return 6;
		glVertex3f(5f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(5f,2f, STRING_LAYER);
		glVertex3f(1f,2f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);

		glVertex3f(5f,2f, STRING_LAYER);
		glVertex3f(5f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		return 6;
	}
}
