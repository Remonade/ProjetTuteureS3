package Graphic;

import Logic.Ennemi;
import Logic.Player;
import math.Vector2f;
import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;


public class GraphicMain {
 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
 
    // The window handle
    private long window;
        
	private Player m_player;
        private Ennemi m_ennemi;
        private Ennemi m_ennemi2;
        
	private Shader m_shader;
	private int m_VAO;
	private float m_mouseX=0;
	private float m_mouseY=0;
	private Camera2D m_camera;
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
            glfwDestroyWindow(window);
            keyCallback.release();
        } finally {
            // Terminate GLFW and release the GLFWErrorCallback
            glfwTerminate();
            errorCallback.release();
        }
    }
    private void init() {
		initGL();
		initWindow();
		initKeyCallback();
		initData();
        // Make the window visible
        glfwShowWindow(window);
		//GLContext.createFromCurrent();
		System.out.println("Init successful");
    }
		private void initGL() {
			// Setup an error callback. The default implementation
			// will print the error message in System.err.
			glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

			// Initialize GLFW. Most GLFW functions will not work before doing this.
			if ( glfwInit() != GL11.GL_TRUE )
				throw new IllegalStateException("Unable to initialize GLFW");
		}
		private void initWindow() {
			// Configure our window
			glfwDefaultWindowHints(); // optional, the current window hints are already the default
			glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
			glfwWindowHint(GLFW_DOUBLE_BUFFER, GL_TRUE);
			glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 1);
			glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
			//glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); 
			//glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

			// Create the window
			window = glfwCreateWindow(WIDTH, HEIGHT, "Shader", NULL, NULL);
			if ( window == NULL )
				throw new RuntimeException("Failed to create the GLFW window");

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			// Center our window
			glfwSetWindowPos(
				window,
				(vidmode.getWidth() - WIDTH) / 2,
				(vidmode.getHeight() - HEIGHT) / 2
			);

			// Make the OpenGL context current
			glfwMakeContextCurrent(window);
			//GLContext.createFromCurrent();
			// Enable v-sync
			glfwSwapInterval(1);
			GL.createCapabilities();
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			//glEnable(GL_DEPTH_TEST);
			glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		}
		private void initKeyCallback() {
			// Setup a key callback. It will be called every time a key is pressed, repeated or released.
			glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
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
				}
			});
		}
		private void initData() {
			// GL allocation
			m_VAO = glGenVertexArrays();
			glBindVertexArray(m_VAO);
			// Camera setting
			m_camera=new Camera2D();
			m_camera.setBound(1.5f, -1.5f, -1.5f, 1.5f);
			// matrix setting
			// Data declaration
			m_player=new Player(new ModelPlayer());
                        
                        m_ennemi=new Ennemi(new ModelEnnemi(0.6f, 0.4f, 0.2f), 0, 1);
                        m_ennemi2=new Ennemi(new ModelEnnemi(0.2f, 0.4f, 0.6f), 0, -1);
                        
			m_shader=new Shader();
		}
    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
 
        // Set the clear color
        glClearColor(0.25f, 0.25f, 0.25f, 0.0f);
		
		float angle=0.0f;
        while(glfwWindowShouldClose(window) == GL_FALSE) {
			// draw to screen.
			angle+=0.05;
			display(angle);
			
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
			if(UP) m_mouseY+=8.0f/(float)HEIGHT;
			if(DOWN) m_mouseY-=8.0f/(float)HEIGHT;
			if(LEFT) m_mouseX-=8.0f/(float)WIDTH;
			if(RIGHT) m_mouseX+=8.0f/(float)WIDTH;
        }
    }
		private void display(float angle) {
			glClear(GL_COLOR_BUFFER_BIT); // clear the framebuffer
			m_camera.setPos(m_mouseX,m_mouseY);
			m_camera.useCamera();
                        
                        Vector2f current = new Vector2f(m_mouseX,m_mouseY);
                        Vector2f size = new Vector2f(1,1);
                        
                        m_player.m_coordinates = current;
			m_player.m_model.draw(m_player.m_coordinates, size);
                        
                        m_ennemi.walk(m_player);
                        m_ennemi.rotate();
                        m_ennemi.m_model.draw(m_ennemi.m_coordinates, size);
                        m_ennemi2.walk(m_player);
                        m_ennemi2.rotate();
                        m_ennemi2.m_model.draw(m_ennemi2.m_coordinates, size);
                        
			//m_model.draw(m_shader,m_modelView,m_projection);
			glfwSwapBuffers(window); // swap the color buffers
		}
	private void quit() {
		glDeleteVertexArrays(m_VAO);
		m_shader.destroy();
	}
    public static void main(String[] args) {
        new GraphicMain().run();
    }
}
