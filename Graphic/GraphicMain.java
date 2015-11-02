package Graphic;

import java.nio.FloatBuffer;
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
	
	private FloatBuffer m_modelView;
	private FloatBuffer m_projection;
	private Model m_model;
	private Shader m_shader;
	private int m_VAO;
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
			glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
			glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
			glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); 
			glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
			int WIDTH = 800;
			int HEIGHT = 600;

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
		}
		private void initKeyCallback() {
			// Setup a key callback. It will be called every time a key is pressed, repeated or released.
			glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
				@Override
				public void invoke(long window, int key, int scancode, int action, int mods) {
					if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
						glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
				}
			});
		}
		private void initData() {
			// GL allocation
			m_VAO = glGenVertexArrays();
			glBindVertexArray(m_VAO);
			m_modelView = FloatBuffer.allocate(4*4);
			m_projection = FloatBuffer.allocate(4*4);

			// Data declaration
			m_model=new Model(5);
			m_shader=new Shader();
		}
    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
 
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		float angle=0.0f;
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
			// draw to screen.
			angle+=0.05;
			//if(angle>2.0f)
				//angle=0.00f;
			display(angle);
			
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
		private void display(float angle) {
			glClear(GL_COLOR_BUFFER_BIT); // clear the framebuffer
			m_model.draw(m_shader,m_modelView,m_projection);
			glfwSwapBuffers(window); // swap the color buffers
		}
	private void quit() {
		glDeleteVertexArrays(m_VAO);
		m_shader.destroy();
		m_model.destroy();
	}
    public static void main(String[] args) {
        new GraphicMain().run();
    }
}
