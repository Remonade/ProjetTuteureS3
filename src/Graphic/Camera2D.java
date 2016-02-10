 /**
  * Cet objet sert à gérer a camera dans un espace 2D.
  * Les coorodnnées de la camera peuvent être contraintes grâce à setBound(top,bot,left,right).
  * setPos(x,y) permet de recadrer la camera aux positions x,y.
  * move(x,y) applique un vecteur de déplacement de coordonnées x,y.
  * useCamera() rédéfinit les paramètres OpenGL pour utiliser les données de cette camera.
  * @author Bernelin Antoine
  */

package Graphic;

import static java.lang.Math.max;
import static java.lang.Math.min;
import Maths.Vector2f;
import Maths.Vector4f;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glOrtho;


public class Camera2D {
    private float m_zoom=3, m_finalZoom=3;
    private float m_posX,m_posY;
    private float m_BT,m_BB,m_BL,m_BR;
    /**
     * Instancie un objet Camera avec des paramètres par défaut: setPos(0,0), setCameraBound(1000,-1000,-1000,1000).
     */
    public Camera2D() {
        setPos(0.0f,0.0f);
        setCameraBound(1000.0f,-1000.0f,-1000.0f,1000.0f);
    }
    /**
     * Modifie la camera openGL avec les données de l'instance. Cette fonction doit être appelée avant toute tentative de dessiner sur la carte graphique.
     */
    public void useCamera() {
        update();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        float ratio=(float)GraphicMain.WIDTH/(float)GraphicMain.HEIGHT;
        //glTranslatef(-m_posX,-m_posY,0.0f);
        //glFrustum(-m_zoom, m_zoom, -m_zoom/ratio, m_zoom/ratio, -10, 10);
        
        glTranslatef(-m_posX/m_zoom,-m_posY/m_zoom*ratio,0.0f);
        glOrtho(-m_zoom, m_zoom, -m_zoom/ratio, m_zoom/ratio, -100, 100);
    }
    public void prepareBackgroundCamera() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-1,1,-1,1, -100, 100);
    }
    public void prepareGUICamera() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,GraphicMain.WIDTH,0,GraphicMain.HEIGHT, -100, 100);
    }
    public void update() {
        if(m_zoom<m_finalZoom)
            m_zoom+=0.075;
        if(m_zoom>m_finalZoom)
            m_zoom-=0.075;
    }
    public void setZoom(float zoom) {
        if(zoom>1)
            m_finalZoom=zoom;
        else
            m_finalZoom=1;
    }
    public float getZoom() {
        return m_finalZoom;
    }
	public float getCurrentZoom() {
		return m_zoom;
	}
	public float getRatio() {
		return (float)GraphicMain.WIDTH/(float)GraphicMain.HEIGHT;
	}
    /**
     * Déplace la camera selon un vecteur passé en paramètre.
     * @param x float représentant le déplacement horizontal.
     * @param y float représentant le déplacement vertical.
     * @author Bernelin Antoine
     */
    public void move(float x,float y) {
        if(x>0.0f)
            m_posX=min(m_posX+x,m_BR);
        else
            m_posX=max(m_posX+x,m_BL);
        if(y>0.0f)
            m_posY=min(m_posY+y,m_BT);
        else
            m_posY=max(m_posY+y,m_BB);
    }
    public void move(Vector2f pos) {
        if(pos.x>0.0f)
            m_posX=min(m_posX+pos.x,m_BR);
        else
            m_posX=max(m_posX+pos.x,m_BL);
        if(pos.y>0.0f)
            m_posY=min(m_posY+pos.y,m_BT);
        else
            m_posY=max(m_posY+pos.y,m_BB);
    }
    /**
     * Déplace la camera aux coordonnées x,y.
     * @param x float représentant la nouvelle horizontal.
     * @param y float représentant la nouvelle vertical.
     * @author Bernelin Antoine
     */
    public final void setPos(float x,float y) {
        m_posX=min(max(m_BL,x),m_BR);
        m_posY=min(max(m_BB,y),m_BT);
    }
    public void setPos(Vector2f pos) {
        m_posX=min(max(m_BL,pos.x),m_BR);
        m_posY=min(max(m_BB,pos.y),m_BT);
    }
    /**
     * Définit des limites pour la position de la camera. Lors des appeles de move(x,y) et setPos(x,y), les coordonées sont automatiquement corrigées pour respécter les limites.
     * @param t float représentant la borne verticale supérieure.
     * @param b float représentant la borne verticale inférieure.
     * @param l float représentant la borne horizontale inférieure.
     * @param r float représentant la borne horizontale supérieure.
     * @author Bernelin Antoine
     */
    public final void setCameraBound(float t, float b,float l,float r) {
        m_BT=max(b,t);
        m_BB=min(b,t);
        m_BL=min(r,l);
        m_BR=max(r,l);
    }
    public final void setCameraBound(Vector4f bound) {
        m_BT=max(bound.z,bound.w);
        m_BB=min(bound.z,bound.w);
        m_BL=min(bound.y,bound.x);
        m_BR=max(bound.y,bound.x);
    }
}
