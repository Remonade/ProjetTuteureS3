package Tests;

import Graphic.GraphicMain;
import static Graphic.GraphicMain.resetScreen;
import static Graphic.GraphicMain.updateScreen;
import Logic.Logic;
import static Logic.Logic.updateTime;

import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Main { 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
 
    // The window handle
    private long window;
    //private Camera2D camera;
    private final int WIDTH = 1024;
    private final int HEIGHT = 768;
	
	public static final int STATE_MAIN_MENU=0;
	public static final int STATE_LOADING_MENU=1;
	public static final int STATE_LEVEL=2;
	public static final int STATE_IN_GAME_MENU=3;
	public static final int STATE_SAVING_MENU=4;
	public static final int STATE_SETTING_MENU=5;
	public static final int STATE_BINDING=6;
	public static final int STATE_GAME_OVER=7;
	public static final int STATE_TRANSITION=8;
	public static final int STATE_LOGO=9;

	public static int GAME_STATE=-1;
	public static void setGameState(int state) {
		System.out.println("Set GameState: "+state);
		if(GAME_STATE!=-1)
			m_gameState[GAME_STATE].onLeave();
		GAME_STATE=state;
		m_gameState[GAME_STATE].onEnter();
	}
	public static void changeGameState(int state) {
		System.out.println("Set previous GameState: "+GAME_STATE);
		((GameStateTransition)m_gameState[STATE_TRANSITION]).setPrev(GAME_STATE);
		System.out.println("Set next GameState: "+state);
		((GameStateTransition)m_gameState[STATE_TRANSITION]).setNext(state);
		setGameState(STATE_TRANSITION);
	}
	
	private static GameState[] m_gameState=new GameState[10];
	
	public static GameState getGameState(int state) {
		return m_gameState[state];
	}
	public static GameState getGameState() {
		return m_gameState[GAME_STATE];
	}
	
    public void run() {
        try {
            init();
            loop();
            // Release window and window callbacks
        } catch(Exception e) {
		} finally {
            quit();
            // Terminate GLFW and release the GLFWErrorCallback
            glfwTerminate();
            errorCallback.release();
        }
    }
   
    private void init() {
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		m_gameState[STATE_LEVEL]=new GameStateLevel();
		m_gameState[STATE_MAIN_MENU]=new GameStateMainMenu();
		m_gameState[STATE_SETTING_MENU]=new GameStateSettingMenu();
		m_gameState[STATE_BINDING]=new GameStateBinding();
		m_gameState[STATE_GAME_OVER]=new GameStateGameOver();
		m_gameState[STATE_TRANSITION]=new GameStateTransition();
		m_gameState[STATE_LOGO]=new GameStateLogo();
        GraphicMain.init(WIDTH,HEIGHT);
        glfwShowWindow(GraphicMain.window);
		Input.defaultBinding();
		Input.initKeyName();
        Input.initInput();
		Audio.Audio.init();
        Logic.init();
        // Make the window visible
		setGameState(STATE_LOGO);
        System.out.println("Init successful");
    }
    
    private void loop() {
        while(glfwWindowShouldClose(GraphicMain.window) == GL_FALSE) {
            try {
				updateTime();
				if(m_gameState[GAME_STATE]!=null) {
					execute();
					resetScreen();
					draw();
					updateScreen();
				} else setGameState(STATE_MAIN_MENU);
            } catch (Exception e) {
				e.printStackTrace();
            }
        }
    }
    private void execute() {
		m_gameState[GAME_STATE].execute();
	}
    private void draw() {
		m_gameState[GAME_STATE].draw();
	}
    private void quit() {
		try {
			glfwDestroyWindow(GraphicMain.window);
			// Input.keyCallback.release();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Audio.Audio.clear();
			GraphicMain.clear();
			Logic.clear();
		}
    }
    
    public static void main(String[] args) {
        new Main().run();
    }
}
