
package Logic.Data;

import static Graphic.GraphicMain.getModel;
import Graphic.Model;
import java.util.HashMap;

public class DataBuff {
	protected String m_name = ""; // nom d'affichage
	protected String m_iconeName = "icone/berzerker.png"; // nom de l'icone pour affichage
	protected int m_maxStackCount = 1; // nombre maximum de stack
	protected float m_maxDuration = 0; // durée avant dissipation de l'effet ou d'une stack
	protected boolean m_stackDuration = false; // comportement de dissipation
	protected int m_maxActivationCount = 1;
	// true: dissipation stack par stack
	// false: dissipation de la totalité de l'effet
	protected boolean m_displayable = true; // affiché dans l'interface
	
	private HashMap<String,String> m_properties = new HashMap<>();
	
	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		this.m_name = name;
	}

	public String getIconeName() {
		return m_iconeName;
	}

	public void setIconeName(String iconeName) {
		this.m_iconeName = iconeName;
	}

	public int getMaxStackCount() {
		return m_maxStackCount;
	}

	public void setMaxStackCount(int maxStack) {
		this.m_maxStackCount = maxStack;
	}

	public float getMaxDuration() {
		return m_maxDuration;
	}

	public void setMaxDuration(float maxDuration) {
		this.m_maxDuration = maxDuration;
	}

	public boolean isStackDuration() {
		return m_stackDuration;
	}

	public void setStackDuration(boolean stackDuration) {
		this.m_stackDuration = stackDuration;
	}

	public float getActivationDelay() {
		return getMaxDuration()/(getMaxActivationCount());
	}

	public int getMaxActivationCount() {
		return m_maxActivationCount;
	}

	public void setMaxActivationCount(int maxActivationCount) {
		this.m_maxActivationCount = maxActivationCount;
	}

	public boolean isDisplayable() {
		return m_displayable;
	}

	public void setDisplayable(boolean displayable) {
		this.m_displayable = displayable;
	}
	
	public void setProperty(String propertyName, String propertyValue) {
		m_properties.put(propertyName, propertyValue);
	}
	public String getStringProperty(String propertyName) {
		return m_properties.get(propertyName);
	}
	public int getIntegerProperty(String propertyName) {
		return Integer.parseInt(m_properties.get(propertyName));
	}
	public float getFloatProperty(String propertyName) {
		return Float.parseFloat(m_properties.get(propertyName));
	}
	
	public Model getIconeModel() {
		return getModel(m_iconeName);
	}
	
	// static part
	
	private static HashMap<String,DataBuff> DATA_BUFF = initDataBuff();
	private static HashMap<String,DataBuff> initDataBuff() {
		HashMap<String,DataBuff> out = new HashMap<>();
		DataBuff data;
		
		data = new DataBuff();
		data.m_name = "regen";
		data.m_iconeName = "icone/heal.png";
		data.m_maxActivationCount = 10;
		data.m_maxDuration = 2;
		data.setProperty("healAmount", "100");
		out.put("regen", data);
		
		data = new DataBuff();
		data.m_name = "dash";
		data.m_iconeName = "icone/dash.png";
		data.m_maxDuration = 0.15f;
		data.setProperty("range", "3");
		out.put("dash", data);
			
		return out;
	}
	
	public static DataBuff getDataBuff(String name) {
		return DATA_BUFF.get(name);
	}
}
