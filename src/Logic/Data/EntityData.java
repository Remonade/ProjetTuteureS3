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
	
	private HashMap<String,String>m_sounds=new HashMap<>();
	
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
		if(m_color==null)
			m_color=new Vector4f(1,1,1,1);
		return m_color.add(new Vector4f(0,0,0,0));
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
	// audio part
	public void setSound(String key,String path) {
		if(m_sounds.containsKey(key))
			m_sounds.remove(key);
		m_sounds.putIfAbsent(key, path);
	}
	public String getSound(String key) {
		if(m_sounds.containsKey(key))
			return m_sounds.get(key);
		else
			return "";
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
