package Tests;

import Graphic.GraphicMain;
import Logic.Logic;
import static Logic.Logic.updateTime;

import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Main { 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
 
    // The window handle
    private long window;
    //private Camera2D camera;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

	public static int GAME_STATE=0;
	public static void setGameState(int state) {
		m_gameState[GAME_STATE].onLeave();
		GAME_STATE=state;
		m_gameState[GAME_STATE].onEnter();
	}
	public static final int STATE_MAIN_MENU=0;
	public static final int STATE_LOADING_MENU=1;
	public static final int STATE_LEVEL=2;
	public static final int STATE_IN_GAME_MENU=3;
	public static final int STATE_SAVING_MENU=4;
	public static final int STATE_SETTING_MENU=5;
	public static final int STATE_BINDING=6;
	public static final int STATE_GAME_OVER=7;
	
	private static GameState[] m_gameState=new GameState[8];
	
	public static GameState getGameState(int state) {
		return m_gameState[state];
	}
	public static GameState getGameState() {
		return m_gameState[GAME_STATE];
	}
	
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        try {
            init();
			//Sound.startSound("data/audio/backgroundMusic.ogg", true);
			//Audio.Audio.playMusic("backgroundMusic.ogg", true);
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
		Input.defaultBinding();
		Input.initKeyName();
		Audio.Audio.init();
        GraphicMain.init(WIDTH,HEIGHT);
        Logic.init();
        Input.initInput();
        // Make the window visible
        glfwShowWindow(GraphicMain.window);
        System.out.println("Init successful");
    }
    
    private void loop() {
        while(glfwWindowShouldClose(GraphicMain.window) == GL_FALSE) {
            try {
				updateTime();
				//Audio.Audio.setGeneralVolume(Audio.Audio.getGeneralVolume()-0.001f);
				execute();
            } catch (Exception e) {
				e.printStackTrace();
            }
        }
    }
    private void execute() {
		if(m_gameState[GAME_STATE]!=null) {
			m_gameState[GAME_STATE].execute();
		} else setGameState(STATE_MAIN_MENU);
	}
    private void quit() {
		try {
			glfwDestroyWindow(GraphicMain.window);
			Input.keyCallback.release();
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
