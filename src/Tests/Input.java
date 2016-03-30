/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import Graphic.GraphicMain;
import static Graphic.GraphicMain.window;
import Maths.Vector2f;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.*;

public class Input {
    // public static GLFWKeyCallback   keyCallback;
	private static boolean[] press=new boolean[GLFW_KEY_LAST];
	private static boolean[] push=new boolean[GLFW_KEY_LAST];
	private static boolean[] free=new boolean[GLFW_KEY_LAST];
	private static boolean[] click=new boolean[GLFW_MOUSE_BUTTON_8];
	private static boolean[] down=new boolean[GLFW_MOUSE_BUTTON_8];
	private static boolean[] up=new boolean[GLFW_MOUSE_BUTTON_8];
	
	private static String[] keyName;
	// mouse
	private static double MOUSE_X;
	private static double MOUSE_Y;
	
	public static void updateMouseCoordinate() {
		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		
		glfwGetCursorPos(window, b1, b2);
		
		MOUSE_X=b1.get();
		MOUSE_Y=b2.get();
	}
	public static double getMouseX() {
		return MOUSE_X;
	}
	public static double getMouseY() {
		return MOUSE_Y;
	}
	public static Vector2f getMousePosition() {
		return new Vector2f((float)getMouseX(),(float)getMouseY());
	}
	public static double getWorldMouseX(){
		double x=(getMouseX()/GraphicMain.WIDTH-0.5f)*2*GraphicMain.camera.getZoom();
		return x;
	}
	public static double getWorldMouseY(){
		double y=-(getMouseY()/GraphicMain.HEIGHT-0.5f)*2*GraphicMain.camera.getZoom()/GraphicMain.camera.getRatio();
		return y;
	}
	public static Vector2f getWorldMousePosition() {
		return new Vector2f((float)getWorldMouseX(),(float)getWorldMouseY());
	}
	// binded keys
	public static int BIND_MOUSE=GLFW_MOUSE_BUTTON_LEFT;
	
	public static int BIND_UP=0;
	public static int BIND_DOWN=1;
	public static int BIND_LEFT=2;
	public static int BIND_RIGHT=3;
	
	public static int BIND_JUMP=4;
	public static int BIND_SHOOT=5;
	
	public static int BIND_SPELL_1=6;
	public static int BIND_SPELL_2=7;
	public static int BIND_SPELL_3=8;
	public static int BIND_SPELL_4=9;
	public static int BIND_SPELL_5=10;
	
	public final static int BIND_MENU=11;
	
	public static int[] BIND=new int[BIND_MENU+1];
	
	public static void setBind(int bind, int key) {
		BIND[bind]=key;
	}
	public static void defaultBinding() {
		BIND[BIND_UP]=GLFW_KEY_W;
		BIND[BIND_DOWN]=GLFW_KEY_S;
		BIND[BIND_LEFT]=GLFW_KEY_A;
		BIND[BIND_RIGHT]=GLFW_KEY_D;
		
		BIND[BIND_JUMP]=GLFW_KEY_W;
		BIND[BIND_SHOOT]=GLFW_KEY_SPACE;
		BIND[BIND_SPELL_1]=GLFW_KEY_E;
		BIND[BIND_SPELL_2]=GLFW_KEY_2;
		BIND[BIND_SPELL_3]=GLFW_KEY_3;
		BIND[BIND_SPELL_4]=GLFW_KEY_4;
		BIND[BIND_SPELL_5]=GLFW_KEY_5;
		
		BIND[BIND_MENU]=GLFW_KEY_ESCAPE;
	}
	
	
	public static boolean isPressed(int bind) {
		return press[bind];
	}
	public static boolean isPushed(int bind) {
		return push[bind];
	}
	public static boolean isFreed(int bind) {
		return free[bind];
	}
	public static boolean isClicked(int bind) {
		return click[bind];
	}
	public static boolean isDown(int bind) {
		return down[bind];
	}
	public static boolean isUp(int bind) {
		return up[bind];
	}
	public static int getBind(int bind) {
		return BIND[bind];
	}
	public static void initInput() {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
		/* glfwSetKeyCallback(GraphicMain.window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW_PRESS ) {
					press[key]=true;
					push[key]=true;
				} else if (action == GLFW_RELEASE )
					press[key]=false;
			}
		});*/
	}
	public static void updateInput() {
		for(int i=0;i<GLFW_KEY_LAST;i++) {
			push[i]=false;
			free[i]=false;
		}
		for(int i=0;i<GLFW_MOUSE_BUTTON_8;i++) {
			click[i]=false;
			up[i]=false;
		}
        glfwPollEvents();
		for(int i=0;i<GLFW_KEY_LAST;i++) {
			if(glfwGetKey(window,i)==GLFW_PRESS) {
				if(!press[i]) {
					push[i]=true;
					//System.out.println("Pressed: "+getKeyName(i));
				}
				press[i]=true;
			} else {
				press[i]=false;
				free[i]=true;
			}
		}
		updateMouseCoordinate();
		for(int i=0;i<GLFW_MOUSE_BUTTON_8;i++) {
			if(glfwGetMouseButton(window,i)==GLFW_PRESS) {
				if(!down[i]) {
					click[i]=true;
					//System.out.println("Clicked: "+i);
				}
				down[i]=true;
			} else {
				down[i]=false;
				up[i]=true;
			}
		}
	}
	
	public static void initKeyName() {
		keyName=new String[GLFW_KEY_LAST];
		keyName[GLFW_KEY_SPACE]="Espace";
		keyName[GLFW_KEY_APOSTROPHE]="'";
		keyName[GLFW_KEY_COMMA]=",";
		keyName[GLFW_KEY_MINUS]="-";
		keyName[GLFW_KEY_PERIOD]="";
		keyName[GLFW_KEY_SLASH]="/";
		keyName[GLFW_KEY_0]="0";
		keyName[GLFW_KEY_1]="1";
		keyName[GLFW_KEY_2]="2";
		keyName[GLFW_KEY_3]="3";
		keyName[GLFW_KEY_4]="4";
		keyName[GLFW_KEY_5]="5";
		keyName[GLFW_KEY_6]="6";
		keyName[GLFW_KEY_7]="7";
		keyName[GLFW_KEY_8]="8";
		keyName[GLFW_KEY_9]="9";
		keyName[GLFW_KEY_SEMICOLON]="|";
		keyName[GLFW_KEY_EQUAL]="=";
		keyName[GLFW_KEY_A]="A";
		keyName[GLFW_KEY_B]="B";
		keyName[GLFW_KEY_C]="C";
		keyName[GLFW_KEY_D]="D";
		keyName[GLFW_KEY_E]="E";
		keyName[GLFW_KEY_F]="F";
		keyName[GLFW_KEY_G]="G";
		keyName[GLFW_KEY_H]="H";
		keyName[GLFW_KEY_I]="I";
		keyName[GLFW_KEY_J]="J";
		keyName[GLFW_KEY_K]="K";
		keyName[GLFW_KEY_L]="L";
		keyName[GLFW_KEY_M]="M";
		keyName[GLFW_KEY_N]="N";
		keyName[GLFW_KEY_O]="O";
		keyName[GLFW_KEY_P]="P";
		keyName[GLFW_KEY_Q]="Q";
		keyName[GLFW_KEY_R]="R";
		keyName[GLFW_KEY_S]="S";
		keyName[GLFW_KEY_T]="T";
		keyName[GLFW_KEY_U]="U";
		keyName[GLFW_KEY_V]="V";
		keyName[GLFW_KEY_W]="W";
		keyName[GLFW_KEY_X]="X";
		keyName[GLFW_KEY_Y]="Y";
		keyName[GLFW_KEY_Z]="Z";
		keyName[GLFW_KEY_LEFT_BRACKET]="[";
		keyName[GLFW_KEY_BACKSLASH]="\\";
		keyName[GLFW_KEY_RIGHT_BRACKET]="]";
		keyName[GLFW_KEY_GRAVE_ACCENT]="`";
		keyName[GLFW_KEY_WORLD_1]="World 1";
		keyName[GLFW_KEY_WORLD_2]="World 2";
		
		keyName[GLFW_KEY_ESCAPE]="Escape";
		keyName[GLFW_KEY_ENTER]="Enter";
		keyName[GLFW_KEY_TAB]="Tab";
		keyName[GLFW_KEY_BACKSPACE]="Backspace";
		keyName[GLFW_KEY_INSERT]="Insert";
		keyName[GLFW_KEY_DELETE]="Delete";
		keyName[GLFW_KEY_RIGHT]="Right";
		keyName[GLFW_KEY_LEFT]="Left";
		keyName[GLFW_KEY_DOWN]="Down";
		keyName[GLFW_KEY_UP]="Up";
		keyName[GLFW_KEY_PAGE_UP]="Page Up";
		keyName[GLFW_KEY_PAGE_DOWN]="Page Down";
		keyName[GLFW_KEY_HOME]="Home";
		keyName[GLFW_KEY_END]="End";
		keyName[GLFW_KEY_CAPS_LOCK]="Caps Lock";
		keyName[GLFW_KEY_SCROLL_LOCK]="Scroll Lock";
		keyName[GLFW_KEY_NUM_LOCK]="Num Lock";
		keyName[GLFW_KEY_PRINT_SCREEN]="Print Screen";
		keyName[GLFW_KEY_PAUSE]="Pause";
		keyName[GLFW_KEY_F1]="F1";
		keyName[GLFW_KEY_F2]="F2";
		keyName[GLFW_KEY_F3]="F3";
		keyName[GLFW_KEY_F4]="F4";
		keyName[GLFW_KEY_F5]="F5";
		keyName[GLFW_KEY_F6]="F6";
		keyName[GLFW_KEY_F7]="F7";
		keyName[GLFW_KEY_F8]="F8";
		keyName[GLFW_KEY_F9]="F9";
		keyName[GLFW_KEY_F10]="F10";
		keyName[GLFW_KEY_F11]="F11";
		keyName[GLFW_KEY_F12]="F12";
		keyName[GLFW_KEY_F13]="F13";
		keyName[GLFW_KEY_F14]="F14";
		keyName[GLFW_KEY_F15]="F15";
		keyName[GLFW_KEY_F16]="F16";
		keyName[GLFW_KEY_F17]="F17";
		keyName[GLFW_KEY_F18]="F18";
		keyName[GLFW_KEY_F19]="F19";
		keyName[GLFW_KEY_F20]="F20";
		keyName[GLFW_KEY_F21]="F21";
		keyName[GLFW_KEY_F22]="F22";
		keyName[GLFW_KEY_F23]="F23";
		keyName[GLFW_KEY_F24]="F24";
		keyName[GLFW_KEY_F25]="F25";
		keyName[GLFW_KEY_KP_0]="KeyPad 0";
		keyName[GLFW_KEY_KP_1]="KeyPad 1";
		keyName[GLFW_KEY_KP_2]="KeyPad 2";
		keyName[GLFW_KEY_KP_3]="KeyPad 3";
		keyName[GLFW_KEY_KP_4]="KeyPad 4";
		keyName[GLFW_KEY_KP_5]="KeyPad 5";
		keyName[GLFW_KEY_KP_6]="KeyPad 6";
		keyName[GLFW_KEY_KP_7]="KeyPad 7";
		keyName[GLFW_KEY_KP_8]="KeyPad 8";
		keyName[GLFW_KEY_KP_9]="KeyPad 9";
		keyName[GLFW_KEY_KP_DECIMAL]="KeyPad Decimal";
		keyName[GLFW_KEY_KP_DIVIDE]="KeyPad /";
		keyName[GLFW_KEY_KP_MULTIPLY]="KeyPad *";
		keyName[GLFW_KEY_KP_SUBTRACT]="KeyPad -";
		keyName[GLFW_KEY_KP_ADD]="KeyPad +";
		keyName[GLFW_KEY_KP_ENTER]="KeyPad Enter";
		keyName[GLFW_KEY_KP_EQUAL]="KeyPad Equal";
		keyName[GLFW_KEY_LEFT_SHIFT]="Left Shift";
		keyName[GLFW_KEY_LEFT_CONTROL]="Left Ctrl";
		keyName[GLFW_KEY_LEFT_ALT]="Left Alt";
		keyName[GLFW_KEY_LEFT_SUPER]="Left Super";
		keyName[GLFW_KEY_RIGHT_SHIFT]="Right Shift";
		keyName[GLFW_KEY_RIGHT_CONTROL]="Right Ctrl";
		keyName[GLFW_KEY_RIGHT_ALT]="Right Alt";
		keyName[GLFW_KEY_RIGHT_SUPER]="Right Super";
	}
	public static String getKeyName(int key) {
		return keyName[key];
	}
}
