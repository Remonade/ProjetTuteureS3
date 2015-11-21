/**
 * Class utilisée pour stocker les données communes aux entités
 * En particulier: taille et model.
 * Pour
 * Ex: des ennemis d'un même type ont le même nombre de PV max, le même model,
 * la même taille...
 *
 */
package Logic;


import Graphic.Model;
import java.util.HashMap;
import Maths.Vector2f;

class EntityData {
    private Vector2f m_size=new Vector2f();
    private Model m_model=null;
    /**
     * Vous ne devez instancier manuelement cette classe!
     * Utilisez seulement EntityData.create(...), qui renvoit l'instance nouvelement créé.
     */
    public EntityData() {
    }
	public void setModel(Model model) {
		m_model=model;
	}
    public Model getModel() {
        return m_model;
    }
	public void setSize(Vector2f size) {
		m_size=size;
	}
    public Vector2f getSize() {
        return m_size;
    }
    
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
}
