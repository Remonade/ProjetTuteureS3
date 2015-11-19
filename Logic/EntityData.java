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
    private Vector2f m_size;
    private Model m_model;
    /**
     * Vous ne devez instancier manuelement cette classe!
     * Utilisez seulement EntityData.create(...), qui renvoit l'instance nouvelement créé.
     */
    public EntityData(Vector2f size,Model model) {
        m_size=size;
        m_model=model;
    }
    public Model getModel() {
        return m_model;
    }
    public Vector2f getSize() {
        return m_size;
    }
    
    private static HashMap<String,EntityData> all=new HashMap();
    public static EntityData create(String key,Vector2f size,Model model) {
        EntityData e=new EntityData(size,model);
        all.put(key,e);
        return e;
    }
    public static EntityData get(String key) {
		EntityData temp=all.get(key);
		if(temp==null)
			return all.get("default");
        return temp;
    }
    public static EntityData create(String key,Vector2f size,Model model) {
        EntityData e=new EntityData(size,model);
        all.put(key,e);
        return e;
    }
}
