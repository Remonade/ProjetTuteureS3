 /**
  * Class utilisée pour stocker les données communes aux entités
  * En particulier: taille et model.
  * Pour
  * Ex: des ennemis d'un même type ont le même nombre de PV max, le même model,
  * la même taille...
  *
  */
package Logic.Data;


import Graphic.Model;
import java.util.HashMap;
import Maths.Vector2f;
import Maths.Vector4f;

public class EntityData {
	// champs
    private Vector2f m_size=new Vector2f();
    private Model m_model=null;
	private Vector4f m_color=null;
	// base
    /**
     * Vous ne devez instancier manuelement cette classe!
     * Utilisez seulement EntityData.create(...), qui renvoit l'instance nouvelement créé.
     */
    public EntityData() {
    }
	// graphic part
    public void setModel(Model model) {
        m_model=model;
    }
    public Model getModel() {
        return m_model;
    }
	public void setColor(Vector4f color) {
		m_color=color;
	}
	public void setColor(float r,float g,float b, float a) {
		if(m_color==null)
			m_color=new Vector4f(r,g,b,a);
		else {
			m_color.x=r;
			m_color.y=g;
			m_color.z=b;
			m_color.w=a;
		}
	}
	public Vector4f getColor() {
		return m_color;
	}
	// physic part
    public void setSize(Vector2f size) {
        m_size=size;
    }
    public void setSize(float w, float h) {
        m_size.x=w;
		m_size.y=h;
    }
    public Vector2f getSize() {
        return m_size;
    }
    // fonctions de création
    protected static HashMap<String,EntityData> all=new HashMap();
    public static EntityData create(String key) {
        EntityData e=new EntityData();
        all.put(key,e);
        return e;
    }
    public static EntityData get(String key) {
        EntityData temp=all.get(key);
        if(temp==null)
            return all.get("default");
        return temp;
    }
	public static void clear() {
		all.clear();
	}
	public static int getCount() {
		return all.size();
	}
}
