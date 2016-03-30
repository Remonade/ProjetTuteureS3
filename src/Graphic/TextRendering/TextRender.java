/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic.TextRendering;

import static Graphic.TextRendering.TextRenderABCDE.*;
import static Graphic.TextRendering.TextRenderFGHIJ.*;
import static Graphic.TextRendering.TextRenderKLMNO.*;
import static Graphic.TextRendering.TextRenderNumbers.*;
import static Graphic.TextRendering.TextRenderPQRST.*;
import static Graphic.TextRendering.TextRenderSymbols.*;
import static Graphic.TextRendering.TextRenderUVWXYZ.*;
import Maths.Vector2f;
import Maths.Vector4f;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;

public class TextRender {
	private static boolean RENDER_MOD=true;
	public static float LINE_HEIGHT=10;
	private static final float WHITE_WIDTH_DEFAULT=4;
	private static float WHITE_WIDTH=4;
	
	public static void setRenderMod(boolean mod) {
		RENDER_MOD=mod;
	}
	public static boolean getRenderMod() {
		return RENDER_MOD;
	}
	public static String getFormatedLine(String str, float size, float maxWidth) {
		String out="";
		ArrayList<Object[]> lines=getLines(str,size,maxWidth);
		while(lines.size()>1) {
			Object[] o=lines.get(0);
			lines.remove(0);
			String s=(String)o[0];
			out+=s+"\n";
		}
		Object[] o=lines.get(0);
		lines.remove(0);
		String s=(String)o[0];
		out+=s;
		return out;
	}
	public static ArrayList<Object[]> getLines(String str, float size, float maxWidth) {
		ArrayList<Object[]> out=new ArrayList<>();
		if(str.equals(""))
			return out;
			
		ArrayList<String> words=new ArrayList<>();
		for(String s:str.split(" "))
			words.add(s);
		
		ArrayList<Float> width=new ArrayList<>();
		for(String s:words)
			width.add(getRenderedSize(s,size).x);
		
		float currentWidth=0;
		String currentLine="";
		float spaceWidth=getRenderedSize(" ",size).x;
		
		while(!words.isEmpty()) {
			String s=words.get(0);
			words.remove(0);
			
			float w=width.get(0);
			width.remove(0);
			
			if(currentWidth+spaceWidth+w>=maxWidth) {
				Object[] o=new Object[2];
				o[0]=currentLine;
				o[1]=currentWidth;
				out.add(o);
				currentLine=s;
				currentWidth=w;
			} else {
				if(currentLine.equals("")) {
					currentLine+=s;
					currentWidth+=w;
				} else {
					currentLine+=" "+s;
					currentWidth+=spaceWidth+w;
				}
			}
		}
		Object[] o=new Object[2];
		o[0]=currentLine;
		o[1]=currentWidth;
		out.add(o);
		return out;
	}
	public static Vector2f getRenderedSize(String s, float size) {
		if(s.equals(""))
			return new Vector2f(0,0);
		
		setRenderMod(false);
		Vector2f out=new Vector2f(0,0);
        float offset=0;
		char[] array=s.toCharArray();
		
		int i=0;
		for (i=0;i<array.length;i++) {
			if(array[i]>='a' && array[i]<='z')
				offset+=renderLetter(array[i]);
			else if(array[i]>='A' && array[i]<='Z')
				offset+=renderCapital(array[i]);
			else if(array[i]>='0' && array[i]<='9')
				offset+=renderNumber(array[i]);
			else if(array[i]=='\n') {
				out.x=Math.max(out.x,offset);
				out.y+=LINE_HEIGHT;
				offset=0;
			} else offset+=renderSymbol(array[i]);
		}
		
		if(array[array.length-1]!='\n') {
			out.x=Math.max(out.x,offset);
			out.y+=LINE_HEIGHT;
		}
		
		setRenderMod(true);
		out=out.scale(size);
		return out;
	}
    public static void drawString(String s, Vector2f pos, float size, Vector4f color) {
        glMatrixMode(GL_MODELVIEW);
        float line=0;
        glLoadIdentity();
        glTranslatef(pos.x,pos.y-LINE_HEIGHT/2,0);
        glScalef(size,size,1f);
		glLineWidth((float) Math.sqrt(size)+0.5f);
		if(color==null)
			glColor4f(1,1,1,1);
		else
			glColor4f(color.x,color.y,color.z,color.w);
		
		for(String l:s.split("\n")) {
				
			if(color==null)
				glColor4f(1,1,1,1);
			else
				glColor4f(color.x,color.y,color.z,color.w);
			
			for (int c : l.toCharArray()) {
				glBegin(GL_LINES);
				float offset=0;
				if(c>='a' && c<='z')
					offset=renderLetter(c);
				else if(c>='A' && c<='Z')
					offset=renderCapital(c);
				else if(c>='0' && c<='9')
					offset=renderNumber(c);
				else offset=renderSymbol(c);

				glEnd();
				glTranslatef(offset,0,0);
			}
				
			line--;
			glLoadIdentity();
			glTranslatef(pos.x,pos.y-LINE_HEIGHT/2,0);
			glScalef(size,size,1f);
			glTranslatef(0,line*LINE_HEIGHT,0);
		}
    }
    public static void drawStringCentered(String s, Vector2f pos, float size, Vector4f color) {
		Vector2f renderSize=getRenderedSize(s,size);
		Vector2f finalPos=new Vector2f(pos.x-renderSize.x/2,pos.y);
		drawString(s,finalPos,size,color);
    }
    public static void drawStringLeft(String s, Vector2f pos, float size, Vector4f color) {
		Vector2f renderSize=getRenderedSize(s,size);
		Vector2f finalPos=new Vector2f(pos.x,pos.y);
		drawString(s,finalPos,size,color);
    }
    public static void drawStringRight(String s, Vector2f pos, float size, Vector4f color) {
		Vector2f renderSize=getRenderedSize(s,size);
		Vector2f finalPos=new Vector2f(pos.x-renderSize.x,pos.y);
		drawString(s,finalPos,size,color);
    }
    public static void drawStringJustify(String s, Vector2f pos, float size, Vector4f color, float width) {
		ArrayList<Object[]> lines=getLines(s,size,width);
		String text="";
		for(int i=0;i<lines.size()-1;i++) {
			Object[] o=lines.get(i);
			String line=(String)o[0];
			text+=line+"\n";
		}
		Vector2f renderSizeMax=getRenderedSize(text,size);
		
		Vector2f finalPos=new Vector2f(pos.x-width/2,pos.y+renderSizeMax.y/2);
		
		for(int i=0;i<lines.size()-1;i++) {
			Object[] o=lines.get(i);
			String line=(String)o[0];
			float lineW=(Float)o[1];
			
			String[] temp=line.split(" ");
			int spaceCount=temp.length;
			
			WHITE_WIDTH=Math.max(WHITE_WIDTH_DEFAULT,(width-lineW)/spaceCount);
			finalPos.y-=(size*LINE_HEIGHT);
			drawString(line,finalPos,size,color);
			WHITE_WIDTH=WHITE_WIDTH_DEFAULT;
		}
		if(lines.size()-1>-1) {
			Object[] o=lines.get(lines.size()-1);
			String line=(String)o[0];
		
			finalPos.y-=(size*LINE_HEIGHT);
			Vector2f labelPos=finalPos.add(new Vector2f(0,0));
			drawStringLeft(line,labelPos,size,color);
		}
    }
	private static float renderLetter(int c) {
		float offset;
		switch (c) {
			case 'a':
				offset=render_a();
				break;
			case 'b':
				offset=render_b();
				break;
			case 'c':
				offset=render_c();
				break;
			case 'd':
				offset=render_d();
				break;
			case 'e':
				offset=render_e();
				break;
			case 'f':
				offset=render_f();
				break;
			case 'g':
				offset=render_g();
				break;
			case 'h':
				offset=render_h();
				break;
			case 'i':
				offset=render_i();
				break;
			case 'j':
				offset=render_j();
				break;
			case 'k':
				offset=render_k();
				break;
			case 'l':
				offset=render_l();
				break;
			case 'm':
				offset=render_m();
				break;
			case 'n':
				offset=render_n();
				break;
			case 'o':
				offset=render_o();
				break;
			case 'p':
				offset=render_p();
				break;
			case 'q':
				offset=render_q();
				break;
			case 'r':
				offset=render_r();
				break;
			case 's':
				offset=render_s();
				break;
			case 't':
				offset=render_t();
				break;
			case 'u':
				offset=render_u();
				break;
			case 'v':
				offset=render_v();
				break;
			case 'w':
				offset=render_w();
				break;
			case 'x':
				offset=render_x();
				break;
			case 'y':
				offset=render_y();
				break;
			case 'z':
				offset=render_z();
				break;
			default:
				offset=0;
		}
		return offset;
	}
	private static float renderCapital(int c) {
		float offset;
		switch (c) {
			case 'A':
				offset=render_A();
				break;
			case 'B':
				offset=render_B();
				break;
			case 'C':
				offset=render_C();
				break;
			case 'D':
				offset=render_D();
				break;
			case 'E':
				offset=render_E();
				break;
			case 'F':
				offset=render_F();
				break;
			case 'G':
				offset=render_G();
				break;
			case 'H':
				offset=render_H();
				break;
			case 'I':
				offset=render_I();
				break;
			case 'J':
				offset=render_J();
				break;
			case 'K':
				offset=render_K();
				break;
			case 'L':
				offset=render_L();
				break;
			case 'M':
				offset=render_M();
				break;
			case 'N':
				offset=render_N();
				break;
			case 'O':
				offset=render_O();
				break;
			case 'P':
				offset=render_P();
				break;
			case 'Q':
				offset=render_Q();
				break;
			case 'R':
				offset=render_R();
				break;
			case 'S':
				offset=render_S();
				break;
			case 'T':
				offset=render_T();
				break;
			case 'U':
				offset=render_U();
				break;
			case 'V':
				offset=render_V();
				break;
			case 'W':
				offset=render_W();
				break;
			case 'X':
				offset=render_X();
				break;
			case 'Y':
				offset=render_Y();
				break;
			case 'Z':
				offset=render_Z();
				break;
			default:
				offset=0;
		}
		return offset;
	}
	
	private static float renderNumber(int c) {
		float offset;
		switch (c) {
			case '0':
				offset=render_0();
				break;
			case '1':
				offset=render_1();
				break;
			case '2':
				offset=render_2();
				break;
			case '3':
				offset=render_3();
				break;
			case '4':
				offset=render_4();
				break;
			case '5':
				offset=render_5();
				break;
			case '6':
				offset=render_6();
				break;
			case '7':
				offset=render_7();
				break;
			case '8':
				offset=render_8();
				break;
			case '9':
				offset=render_9();
				break;
			default:
				offset=0;
		}
		return offset;
	}
	
	private static float renderSymbol(int c) {
		float offset;
		switch (c) {
			case 'ç':
				offset=render_cedil();
				break;
			case '.':
				offset=render_dot();
				break;
			case ':':
				offset=render_dot2();
				break;
			case '!':
				offset=render_excla();
				break;
			case '/':
				offset=render_slash();
				break;
			case '-':
				offset=render_minus();
				break;
			case '+':
				offset=render_plus();
				break;
			case '_':
				offset=render_underscore();
				break;
			case '\'':
				offset=render_apostrophe();
				break;
			case '\"':
				offset=render_quote();
				break;
			case ' ':
				offset=WHITE_WIDTH;
				break;
			default:
				offset=0;
				break;
		}
		return offset;
	}
}
