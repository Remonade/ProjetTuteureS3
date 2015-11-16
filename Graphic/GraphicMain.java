/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import Logic.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GraphicMain {
	public static void init(int w,int h) {
		initGL();
		initWindow(w,h);
		initShader();
		initTexture();
		initModel();
		m_camera.setBound(10.0f, -1.0f, -1.5f, 1.5f);
	}
	private static void initGL() {
		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( glfwInit() != GL11.GL_TRUE )
			throw new IllegalStateException("Unable to initialize GLFW");
	}
	private static void initWindow(int w, int h) {
		HEIGHT=h;
		WIDTH=w;
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
		//glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
	}
	private static void initShader() {
		
	}
	private static void initTexture() {
		
	}
	private static void initModel() {
		m_models.put("default",new ModelQuad(1,1,1));
		m_models.put("white",m_models.get("default"));
		m_models.put("red",new ModelQuad(1,0,0));
		m_models.put("green",new ModelQuad(0,1,0));
		m_models.put("blue",new ModelQuad(0,0,1));
	}
	public static Model getModel(String ref) {
		Model temp=m_models.get(ref);
		if(temp==null)
			return m_models.get("default");
		return temp;
	}
	public static void display(ArrayList<Entity> entities) {
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		m_camera.useCamera();
		LAYER=0;
		for(Entity i:entities) {
			i.draw();
			//LAYER++;
		}
		glfwSwapBuffers(window); // swap the color buffers
	}
	public static void clear() {
		m_models.clear();
	}
	public static HashMap<String,Model> m_models=new HashMap();
	public static Camera2D m_camera=new Camera2D();
	public static long window;
	public static int HEIGHT, WIDTH;
	public static int LAYER=-50;
}
