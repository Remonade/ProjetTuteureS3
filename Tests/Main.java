package Graphic;

import Logic.Entity;
import Logic.EntityDynamic;
import Physic.PhysicMain;
import org.lwjgl.Sys;
import org.lwjgl.glfw.*;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

public class Main {
 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
 
    // The window handle
	private ArrayList<Entity> m_entities=new ArrayList();
	private EntityDynamic m_player;
    private long window;
	private float m_mouseX=0;
	private float m_mouseY=0;
	//private Camera2D m_camera;
	private int WIDTH = 800;
	private int HEIGHT = 600;
	
	private boolean LEFT;
	private boolean RIGHT;
	private boolean UP;
	private boolean DOWN;
	
    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
        try {
            init();
            loop();
			quit();
            // Release window and window callbacks
            glfwDestroyWindow(GraphicMain.window);
            keyCallback.release();
        } finally {
            // Terminate GLFW and release the GLFWErrorCallback
            glfwTerminate();
            errorCallback.release();
        }
    }
    private void init() {
		initGL();
		GraphicMain.init(WIDTH,HEIGHT);
		initKeyCallback();
		initData();
        // Make the window visible
        glfwShowWindow(GraphicMain.window);
		//GLContext.createFromCurrent();
		System.out.println("Init successful");
    }
		private void initGL() {
			// Setup an error callback. The default implementation
			// will print the error message in System.err.
			glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		}
		private void initKeyCallback() {
			// Setup a key callback. It will be called every time a key is pressed, repeated or released.
			glfwSetKeyCallback(GraphicMain.window, keyCallback = new GLFWKeyCallback() {
				@Override
				public void invoke(long window, int key, int scancode, int action, int mods) {
					if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
						glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
					if ( key == GLFW_KEY_LEFT && action == GLFW_PRESS )
						LEFT=true;
					if ( key == GLFW_KEY_LEFT && action == GLFW_RELEASE )
						LEFT=false;
					if ( key == GLFW_KEY_RIGHT && action == GLFW_PRESS )
						RIGHT=true;
					if ( key == GLFW_KEY_RIGHT && action == GLFW_RELEASE )
						RIGHT=false;
					if ( key == GLFW_KEY_UP && action == GLFW_PRESS )
						UP=true;
					if ( key == GLFW_KEY_UP && action == GLFW_RELEASE )
						UP=false;
					if ( key == GLFW_KEY_DOWN && action == GLFW_PRESS )
						DOWN=true;
					if ( key == GLFW_KEY_DOWN && action == GLFW_RELEASE )
						DOWN=false;
					if ( key == GLFW_KEY_KP_ADD && action == GLFW_PRESS )
						GraphicMain.m_camera.setZoom(GraphicMain.m_camera.getZoom()-1);
					if ( key == GLFW_KEY_KP_SUBTRACT && action == GLFW_PRESS )
						GraphicMain.m_camera.setZoom(GraphicMain.m_camera.getZoom()+1);
					
					/*if ( key == GLFW_KEY_SPACE && action == GLFW_REPEAT ){
						EntityParticle temp;
						for(int i=0;i<1;i++) {
							temp=new EntityParticle(1.0f,1.0f,(float)Math.random()*0);
							temp.setModel(m_particle);
							m_entityParticle.add(temp);
						}
					}*/
				}
			});
		}
		
		private void initData() {
			// Camera setting
			//m_camera=new Camera2D();
			//m_camera.setBound(1.5f, -1.5f, -1.5f, 1.5f);
			// matrix setting
			// Data declaration
			/*FloatBuffer temp=FloatBuffer.allocate(4*2);
			temp.put(0.25f);temp.put(0.25f);
			temp.put(-0.25f);temp.put(0.25f);
			temp.put(-0.25f);temp.put(-0.25f);
			temp.put(0.25f);temp.put(-0.25f);
			temp.flip();*/
			
			Entity temp;
			m_player=new EntityDynamic(0.1f,0.2f);
			temp=m_player;
			temp.setPos(0.5f,0.5f);
			temp.setModel(GraphicMain.getModel("blue"));
			m_entities.add(temp);
			// ground
			temp=new Entity(3f,0.5f);
			temp.setPos(0,-0.5f);
			temp.setModel(GraphicMain.getModel("green"));
			m_entities.add(temp);
			// left wall
			temp=new Entity(0.5f,5f);
			temp.setPos(-2.5f,5);
			temp.setModel(GraphicMain.getModel("green"));
			m_entities.add(temp);
			// right wall
			temp=new Entity(0.5f,5f);
			temp.setPos(2.5f,5);
			temp.setModel(GraphicMain.getModel("green"));
			m_entities.add(temp);
			// blocks
			temp=new Entity(0.3f,0.1f);
			temp.setPos(0f,0.75f);
			temp.setModel(GraphicMain.getModel("red"));
			m_entities.add(temp);
			
			temp=new Entity(0.3f,0.1f);
			temp.setPos(1f,1.5f);
			temp.setModel(GraphicMain.getModel("red"));
			m_entities.add(temp);
			
			temp=new Entity(0.3f,0.1f);
			temp.setPos(2f,2.25f);
			temp.setModel(GraphicMain.getModel("red"));
			m_entities.add(temp);
			
			temp=new Entity(1.5f,0.1f);
			temp.setPos(-0.75f,3f);
			temp.setModel(GraphicMain.getModel("red"));
			m_entities.add(temp);
		}
    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
 
        // Set the clear color
        glClearColor(0.20f, 0.15f, 0.20f, 1.0f);
		
		float angle=0.0f;
        while(glfwWindowShouldClose(GraphicMain.window) == GL_FALSE) {
			/*if(Math.random()<0.75) {
				EntityParticle temp;
				for(int i=0;i<3;i++) {
					temp=new EntityParticle(1.0f,1.0f,(float)Math.random()*360);
					temp.setModel(m_particle);
					m_entityParticle.add(temp);
					temp=null;
				}
			}*/
			
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
			if(UP) {
				if(m_player.getContact(EntityDynamic.CONTACT_LEFT) && LEFT) {
					m_player.setSpeed(-0.3f,0.075f);
				}
				else if(m_player.getContact(EntityDynamic.CONTACT_RIGHT) && RIGHT) {
					m_player.setSpeed(0.3f,0.075f);
				} else if(m_player.getContact(EntityDynamic.CONTACT_DOWN)){
					m_player.setSpeed(m_player.getSpeed().x,0.075f);
				}
			}
			if(LEFT) {
				m_player.addSpeed(-0.010f,0);
			}
			else if(RIGHT) {
				m_player.addSpeed(0.010f,0);
			}
			m_player.setSpeed(m_player.getSpeed().x*0.8f,m_player.getSpeed().y);
			
			// draw to screen.
			PhysicMain.update(m_entities);
			GraphicMain.m_camera.setPos(m_player.getPos());
			//GraphicMain.m_camera.move(0,0.01f);
			GraphicMain.display(m_entities);
        }
    }
	private void quit() {
		GraphicMain.clear();
	}
    public static void main(String[] args) {
        new Main().run();
    }
}
