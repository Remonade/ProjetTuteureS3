/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Graphic;

import Graphic.TextRendering.TextRender;
import GUI.GUI;
import GUI.GUICheckBox;
import Logic.Entity;
import Logic.EntityMissile;
import Logic.EntityParticle;
import Logic.EntityUnit;
import Logic.Logic;
import Logic.Realm;
import java.util.ArrayList;
import java.util.HashMap;
import Maths.Vector2f;
import Maths.Vector4f;
import Tests.Main;
import static Tests.Main.STATE_SETTING_MENU;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GraphicMain {
    public static void init(int w,int h) {
		try {
			initGL();
			initWindow(w,h);
			initShader();
			//initTexture();
			//initModel();
			loadGraphicData("data/graphic.data");
			//camera.setCameraBound(20.0f, -1.0f, -10f, 10f);
		} catch(Exception e) {
			e.printStackTrace();
		}
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
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        glfwWindowHint(GLFW_DECORATED, GL_FALSE);
        glfwWindowHint(GLFW_DOUBLE_BUFFER, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 1);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        //glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "GameEngine", glfwGetPrimaryMonitor(), NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );
        
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        //GLContext.createFromCurrent();
        // Enable v-sync
        glfwSwapInterval(1);
        GL.createCapabilities();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
        //glEnable(GL_DEPTH_TEST);
        //glDepthFunc(GL_LESS);
        //glDepthMask(true);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
        //glDisable(GL_DEPTH_TEST);
		
        glClearColor(0.20f, 0.10f, 0.20f, 1.0f);
    }
    private static void initShader() {
        // no shader yet...
		try {
			shaders.put("default",new Shader());
			//glUseProgram(shaders.get("default").getProgramID());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
    }
	public static void loadGraphicData(String path) throws IOException {
		System.out.println("Clear Current Graphic Data");
		//clear();
		Charset encoding=StandardCharsets.UTF_8;
		System.out.println("Attempting to load "+path);
		List<String> lines = Files.readAllLines(Paths.get(path), encoding);
		System.out.println("Setting New Graphic Data");
		createGraphicData(lines);
		Model m=new Model();
		models.put("defaultBar",m);
	}
	private static void createGraphicData(List<String> lines) {
		Model m=null;
		ModelAnim a=null;
		Particle p=null;
		Texture t=null;
		TextureCliped c=null;
		Animation A=null;
		for(String l:lines) {
			if(l.charAt(0)=='+')
				continue;
			String[] data = l.split(" ");
			String action=data[0];
			if("new".equals(action)) { // create new thing
				String object=data[1];
					String name=data[2];
				if("Model".equals(object)) {
					a=null;
					m=new Model();
					models.put(name,m);
					System.out.println("new model "+name);
				} else if("ModelRepeat".equals(object)) {
					a=null;
					m=new ModelRepeat();
					models.put(name,m);
					System.out.println("new modelRepeat "+name);
				} else if("ModelAnim".equals(object)) {
					a=new ModelAnim();
					m=null;
					models.put(name,a);
					System.out.println("new modelAnim "+name);
				} else if("Particle".equals(object)) {
					p=new Particle();
					a=null;
					m=null;
					models.put(name,p);
					System.out.println("new particle "+name);
				} else if("Texture".equals(object)) {
					t=Texture.loadTexture(name);
					c=null;
					textures.put(name,t);
					System.out.println("new texture "+name);
				} else if("TextureCliped".equals(object)) {
					c=TextureCliped.loadTextureCliped(name);
					textures.put(name,c);
					System.out.println("new textureCliped "+name);
				} else if("Animation".equals(object)) {
					A=new Animation();
					if(a!=null)
						a.setAnimation(name, A);
					System.out.println("new animation "+name);
				}
			} else if("set".equals(action)) { // set stuff
				String attribute=data[1];
				
				if(m!=null) {
					if("Texture".equals(attribute)) {
						String path=data[2];
						m.setTexture(getTexture(path));
					}
				} else if(A!=null) {
					if("Texture".equals(attribute)) {
						String path=data[2];
						A.setTexture(getTexture(path));
					} else if("Duration".equals(attribute)) {
						String duration=data[2];
						A.setDuration(Float.valueOf(duration));
					} else if("FrameCount".equals(attribute)) {
						String count=data[2];
						A.setFrameCount(Integer.valueOf(count));
					} else if("Frame".equals(attribute)) {
						String frame=data[2];
						String clipID=data[3];
						A.setFrame(Integer.valueOf(frame),Integer.valueOf(clipID));
					}
				} else if(c!=null) {
					if("FrameCount".equals(attribute)) {
						String x=data[2];
						String y=data[3];
						c.setFrameCount(Integer.valueOf(x),Integer.valueOf(y));
					}
				}
			}
		}
	}
    public static Model getModel(String ref) {
        Model temp=models.get(ref);
        if(temp==null)
            temp=models.get("default");
        if(temp==null)
			temp=new Model();
        return temp;
    }
    public static Texture getTexture(String ref) {
        Texture temp=textures.get(ref);
        if(temp==null)
            return textures.get("default.png");
        return temp;
    }
	public static void resetScreen() {
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	}
    public static void displayRealm(Realm r) {
		if(Logic.getPlayer()!=null) {
			LAYER=BACKGROUND_LAYER;
			setCameraBackground();
			camera.setCameraBound(r.getCameraBound());
			Model background=r.getBackground();
			if(background!=null) {
				background.draw(null, null, null);
			}
			camera.setPos(Logic.getPlayer().getPos());
			setCameraLevel();

			LAYER=STATIC_LAYER;
			for(Entity i:r.getEntities())
				i.draw();
			for(Entity i:r.getWayPoints()) {
				i.draw();
			}

			LAYER=UNIT_LAYER;
			for(EntityUnit i:r.getUnits())
				i.draw();
			LAYER=DYNAMIC_LAYER;
			for(EntityMissile i:r.getMissiles())
				i.draw();

			GUI gui=Main.getGameState(STATE_SETTING_MENU).getGUI("GUI_CB_PARTICLES");
			if(gui!=null && ((GUICheckBox)gui).getValue()) {
				LAYER=PARTICLE_LAYER;
				for(EntityParticle i:r.getParticles())
					i.draw();
			}
		}
	}
	public static void setCameraLevel() {
        camera.useCamera();
	}
	public static void setCameraGUI() {
        camera.prepareGUICamera();
	}
	public static void setCameraBackground() {
        camera.prepareBackgroundCamera();
	}
	public static void updateScreen() {
        glfwSwapBuffers(window); // swap the color buffers
	}
    private static void renderParticle() {
        for(int i=0;i<particle.size();i++) {
            EntityParticle p=particle.get(i);
			p.draw();
        }
    }
	public static void drawString(String s, Vector2f pos, float size, Vector4f color) {
		TextRender.drawString(s, pos, size, color);
	}
    public static void clear() {
        models.clear();
        textures.clear();
    }
    public static HashMap<String,Model> models=new HashMap();
    public static HashMap<String,Texture> textures=new HashMap();
    public static HashMap<String,Shader> shaders=new HashMap();
    public static ArrayList<EntityParticle> particle=new ArrayList();
    public static Camera2D camera=new Camera2D();
    public static long window;
    public static int HEIGHT, WIDTH;
    public static int LAYER=0;
    public static int STRING_LAYER=6;
    public static int GUI_LAYER=5;
    public static int PARTICLE_LAYER=4;
    public static int UNIT_LAYER=3;
    public static int DYNAMIC_LAYER=2;
    public static int STATIC_LAYER=1;
    public static int BACKGROUND_LAYER=0;
	public static int DIRECTION=1;
	public static void setDirection(int d) {
		DIRECTION=d;
	}
}
