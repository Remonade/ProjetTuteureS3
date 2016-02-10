/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.GraphicMain.STRING_LAYER;
import static Graphic.TextRendering.TextRender.getRenderMod;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class TextRenderUVWXYZ {
	public static float render_u() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_U() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_v() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_V() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_w() {
		if(!getRenderMod())
			return 8;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(5.5f,0f, STRING_LAYER);
		glVertex3f(5.5f,0f, STRING_LAYER);
		glVertex3f(7f,4f, STRING_LAYER);
		return 8;
	}
	public static float render_W() {
		if(!getRenderMod())
			return 8;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(5.5f,0f, STRING_LAYER);
		glVertex3f(5.5f,0f, STRING_LAYER);
		glVertex3f(7f,4f, STRING_LAYER);
		return 8;
	}
	public static float render_x() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_X() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_y() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(1f,-4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_Y() {
		if(!getRenderMod())
			return 5;
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(2.5f,0f, STRING_LAYER);
		glVertex3f(1f,-4f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		return 5;
	}
	public static float render_z() {
		if(!getRenderMod())
			return 5;
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 5;
	}
	public static float render_Z() {
		if(!getRenderMod())
			return 5;
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(1f,4f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		glVertex3f(4f,4f, STRING_LAYER);
		glVertex3f(4f,0f, STRING_LAYER);
		glVertex3f(1f,0f, STRING_LAYER);
		return 5;
	}

}
