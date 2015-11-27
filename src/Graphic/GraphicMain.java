/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Graphic;

import Logic.Entity;
import Logic.EntityParticle;
import Logic.Logic;
import java.util.ArrayList;
import java.util.HashMap;
import Maths.Vector2f;
import Maths.Vector3f;
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
        camera.setBound(10.0f, -1.0f, -10f, 10f);
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
        //glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
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
        models.put("default",new ModelQuad(0.75f,0.75f,0.75f));
        models.put("white",models.get("default"));
        models.put("red",new ModelQuad(0.5f,0,0));
        models.put("green",new ModelQuad(0,0.5f,0));
        models.put("blue",new ModelQuad(0,0,0.5f));
        models.put("fume",new Particle(0.5f,0.5f,0.5f));
        models.put("missile",new ModelQuad(0.5f,0.5f,0f));
    }
    public static Model getModel(String ref) {
        Model temp=models.get(ref);
        if(temp==null)
            return models.get("default");
        return temp;
    }
    public static void display() {
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        camera.useCamera();
        glEnable(GL_DEPTH_TEST);
        LAYER=0;
        for(Entity i:Logic.getEntity()) {
            if(i.isVisible())
                i.draw();
        }
        LAYER=1;
        renderParticle();
        camera.prepareGUICamera();
        glDisable(GL_DEPTH_TEST);
        COUNT++;
        drawString("x:"+(float)((int)(Logic.getPlayer().getPos().x*1000))/1000f+"\ny:"+(float)((int)(Logic.getPlayer().getPos().y*1000))/1000f,new Vector2f(0,200),2f,new Vector3f(1,1,1));
        glfwSwapBuffers(window); // swap the color buffers
    }
    private static void renderParticle() {
        for(int i=0;i<particle.size();i++) {
            EntityParticle p=particle.get(i);
            p.updateTime(0.05f);
            p.draw();
            if(p.updateTime(0.05f))
                particle.remove(i);
        }
    }
    public static void clear() {
        models.clear();
    }
    public static void drawString(String s, Vector2f pos, float size, Vector3f color) {
        glMatrixMode(GL_MODELVIEW);
        float line=0;
        glLoadIdentity();
        glTranslatef(pos.x,pos.y,0);
        glScalef(size,size,1f);
        glColor3f(color.x,color.y,color.z);
        for (int c : s.toLowerCase().toCharArray()) {
            glBegin(GL_LINES);
            float offset=8;
            switch (c) {
                case 'a':
                    glVertex3f(1f,5f, STRING_LAYER);
                    glVertex3f(5f,5f, STRING_LAYER);
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(5f,3f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(5f,5f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    offset=6;
                    break;
                case 'b':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=6;
                    break;
                case 'c':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=6;
                    break;
                case 'd':
                    glVertex3f(5f,0f, STRING_LAYER);
                    glVertex3f(5f,8f, STRING_LAYER);
                    
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    offset=6;
                    break;
                case 'e':
                    glVertex3f(5f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(5f,2f, STRING_LAYER);
                    glVertex3f(1f,2f, STRING_LAYER);
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    
                    glVertex3f(5f,2f, STRING_LAYER);
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    offset=6;
                    break;
                case 'f':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,7f, STRING_LAYER);
                    glVertex3f(1f,7f, STRING_LAYER);
                    glVertex3f(2f,8f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(3f,4f, STRING_LAYER);
                    glVertex3f(2f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    offset=5;
                    break;
                case 'g':
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(5f,-3f, STRING_LAYER);
                    
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    
                    glVertex3f(2f,-3f, STRING_LAYER);
                    glVertex3f(5f,-3f, STRING_LAYER);
                    offset=6;
                    break;
                case 'h':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    glVertex3f(5f,4f, STRING_LAYER);
                    
                    glVertex3f(5f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    offset=6;
                    break;
                case 'i':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,5f, STRING_LAYER);
                    offset=3;
                    break;
                case 'j':
                    glVertex3f(0f,-3f, STRING_LAYER);
                    glVertex3f(1f,-1f, STRING_LAYER);
                    glVertex3f(1f,-1f, STRING_LAYER);
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,5f, STRING_LAYER);
                    offset=3;
                    break;
                case 'k':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(5f,7f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(5f,0f, STRING_LAYER);
                    offset=6;
                    break;
                case 'l':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    offset=3;
                    break;
                case 'm':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,3f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(7f,3f, STRING_LAYER);
                    glVertex3f(7f,0f, STRING_LAYER);
                    glVertex3f(7f,3f, STRING_LAYER);
                    offset=8;
                    break;
                case 'n':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,3f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case 'o':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case 'p':
                    glVertex3f(1f,-4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case 'q':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,-4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case 'r':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case 's':
                    glVertex3f(4f,5f, STRING_LAYER);
                    glVertex3f(1f,5f, STRING_LAYER);
                    glVertex3f(1f,5f, STRING_LAYER);
                    glVertex3f(1f,2.5f, STRING_LAYER);
                    glVertex3f(1f,2.5f, STRING_LAYER);
                    glVertex3f(4f,2.5f, STRING_LAYER);
                    glVertex3f(4f,2.5f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case 't':
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,1f, STRING_LAYER);
                    glVertex3f(1f,1f, STRING_LAYER);
                    glVertex3f(2f,0f, STRING_LAYER);
                    glVertex3f(2f,0f, STRING_LAYER);
                    glVertex3f(3f,0f, STRING_LAYER);
                    glVertex3f(1f,5f, STRING_LAYER);
                    glVertex3f(3f,5f, STRING_LAYER);
                    offset=5;
                    break;
                case 'u':
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case 'v':
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(2.5f,0f, STRING_LAYER);
                    glVertex3f(2.5f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case 'w':
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(2.5f,0f, STRING_LAYER);
                    glVertex3f(2.5f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(5.5f,0f, STRING_LAYER);
                    glVertex3f(5.5f,0f, STRING_LAYER);
                    glVertex3f(7f,4f, STRING_LAYER);
                    offset=8;
                    break;
                case 'x':
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case 'y':
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(2.5f,0f, STRING_LAYER);
                    glVertex3f(1f,-4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case 'z':
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case '.':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,1f, STRING_LAYER);
                    offset=3;
                    break;
                case '!':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,1f, STRING_LAYER);
                    glVertex3f(1f,2f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    offset=3;
                    break;
                case '/':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(5f,8f, STRING_LAYER);
                    offset=6;
                    break;
                case '-':
                    glVertex3f(1f,2f, STRING_LAYER);
                    glVertex3f(3f,2f, STRING_LAYER);
                    offset=4;
                    break;
                case '0':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case '1':
                    glVertex3f(3f,0f, STRING_LAYER);
                    glVertex3f(3f,8f, STRING_LAYER);
                    glVertex3f(1f,6f, STRING_LAYER);
                    glVertex3f(3f,8f, STRING_LAYER);
                    offset=4;
                    break;
                case '2':
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    offset=5;
                    break;
                case '3':
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    offset=5;
                    break;
                case '4':
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(3f,8f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(1f,3f, STRING_LAYER);
                    glVertex3f(3f,3f, STRING_LAYER);
                    offset=5;
                    break;
                case '5':
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case '6':
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case '7':
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    offset=5;
                    break;
                case '8':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case '9':
                    glVertex3f(1f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,0f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(4f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,8f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(1f,4f, STRING_LAYER);
                    glVertex3f(4f,4f, STRING_LAYER);
                    offset=5;
                    break;
                case ' ':
                    offset=4;
                    break;
                case'\n':
                    glEnd();
                    line--;
                    offset=0;
                    glLoadIdentity();
                    glTranslatef(pos.x,pos.y,0);
                    glScalef(size,size,1f);
                    glTranslatef(0,10*line,0);
                    glBegin(GL_LINES);
                    break;
                default:
                    offset=0;
                    break;
            }
            //System.out.print((char)c);
            glEnd();
            glTranslatef(offset,0,0);
        }
        //System.out.println("-Done");
    }
    public static HashMap<String,Model> models=new HashMap();
    public static HashMap<String,Texture> textures=new HashMap();
    public static HashMap<String,Shader> shaders=new HashMap();
    public static ArrayList<EntityParticle> particle=new ArrayList();
    public static Camera2D camera=new Camera2D();
    public static long window;
    public static int HEIGHT, WIDTH;
    public static int LAYER=0;
    public static int COUNT=0;
    public static int STRING_LAYER=2;
}
