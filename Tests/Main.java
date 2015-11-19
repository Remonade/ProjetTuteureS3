package Tests;

import Logic.Entity;
import Logic.EntityDataDynamic;
import Logic.EntityDynamic;
import Logic.EntityParticle;
import Logic.Logic;
import Maths.Vector2f;
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
    private long window;
	private float m_mouseX=0;
	private float m_mouseY=0;
	//private Camera2D camera;
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
		Logic.init();
		initKeyCallback();
        // Make the window visible
        glfwShowWindow(GraphicMain.window);
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
						GraphicMain.camera.setZoom(GraphicMain.camera.getZoom()-1);
					if ( key == GLFW_KEY_KP_SUBTRACT && action == GLFW_PRESS )
						GraphicMain.camera.setZoom(GraphicMain.camera.getZoom()+1);
					
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
					temp.setModel(GraphicMain.getModel("fume"));
					GraphicMain.particle.add(temp);
					temp=null;
				}
			}*/
			
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
				if(UP) {
					if(Logic.getPlayer().getContact(EntityDynamic.CONTACT_LEFT) && LEFT) {
						Logic.getPlayer().setSpeed(-0.1f,0.075f);
						EntityParticle temp;
						for(int i=0;i<5;i++) {
							temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+225);
							temp.setData(EntityDataDynamic.get("particle"));
							temp.setPos(Logic.getPlayer().getPos().x+Logic.getPlayer().getSize().x/2,Logic.getPlayer().getPos().y-Logic.getPlayer().getSize().y);
							GraphicMain.particle.add(temp);
						}
					}
					else if(Logic.getPlayer().getContact(EntityDynamic.CONTACT_RIGHT) && RIGHT) {
						Logic.getPlayer().setSpeed(0.1f,0.075f);
						EntityParticle temp;
						for(int i=0;i<5;i++) {
							temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+315);
							temp.setData(EntityDataDynamic.get("particle"));
							temp.setPos(Logic.getPlayer().getPos().x+Logic.getPlayer().getSize().x/2,Logic.getPlayer().getPos().y+Logic.getPlayer().getSize().y);
							GraphicMain.particle.add(temp);
						}
					} else if(Logic.getPlayer().getContact(EntityDynamic.CONTACT_DOWN)){
						Logic.getPlayer().jump();
					}
				}
				if(LEFT && !RIGHT) {
					Logic.getPlayer().addSpeed(-0.010f,0);
					EntityParticle temp;
					if(Logic.getPlayer().getContact(EntityDynamic.CONTACT_DOWN) && Math.random()<0.2) {
						temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+0);
							temp.setData(EntityDataDynamic.get("particle"));
						temp.setPos(Logic.getPlayer().getPos().x+Logic.getPlayer().getSize().x/2,Logic.getPlayer().getPos().y-Logic.getPlayer().getSize().y);
						GraphicMain.particle.add(temp);
					}
				}
				else if(RIGHT && !LEFT) {
					Logic.getPlayer().addSpeed(0.010f,0);
					EntityParticle temp;
					if(Logic.getPlayer().getContact(EntityDynamic.CONTACT_DOWN) && Math.random()<0.2) {
						temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+180);
							temp.setData(EntityDataDynamic.get("particle"));
						temp.setPos(Logic.getPlayer().getPos().x-Logic.getPlayer().getSize().x/2,Logic.getPlayer().getPos().y-Logic.getPlayer().getSize().y);
						GraphicMain.particle.add(temp);
					}
				} else Logic.getPlayer().setSpeed(Logic.getPlayer().getSpeed().x*0.8f,Logic.getPlayer().getSpeed().y);
				if(DOWN) {
					Logic.getPlayer().shoot(Logic.getPlayer().getPos().add(new Vector2f(-1,0)));
					Logic.getPlayer().shoot(Logic.getPlayer().getPos().add(new Vector2f(1,0)));
				}
			// draw to screen.
			try {
				Logic.update();
				PhysicMain.update();
				Logic.execute();
				GraphicMain.camera.setPos(Logic.getPlayer().getPos());
				//GraphicMain.camera.move(0,0.01f);
				GraphicMain.display();
			} catch (Exception e) {
				
			}
        }
    }
	private void quit() {
		GraphicMain.clear();
	}
    public static void main(String[] args) {
        new Main().run();
    }
}
