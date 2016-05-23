/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Data;

import static Graphic.GraphicMain.getModel;
import Graphic.Model;
import java.util.HashMap;

public class DataSpell {
	protected String m_name="";
	protected String m_iconeName="";
	protected float m_energyCost=0;
	protected float m_baseCooldown=0;
	protected int m_maxChargeCount=-1;
	
	protected boolean m_displayable = true; // affiché dans l'interface
	
	private HashMap<String,String> m_properties = new HashMap<>();
	
	
	protected Model m_icone=null;
	
	public String getName() {
		return m_name;
	}
	public void setName(String name) {
		m_name = name;
	}
	public String getIconeName() {
		return m_iconeName;
	}
	public void setIconeName(String iconeName) {
		m_iconeName = iconeName;
	}
	public float getEnergyCost() {
		return m_energyCost;
	}
	public void setEnergyCost(float energyCost) {
		m_energyCost = energyCost;
	}
	public float getBaseCooldown() {
		return m_baseCooldown;
	}
	public void setBaseCooldown(float baseCooldown) {
		m_baseCooldown = baseCooldown;
	}
	public int getMaxChargeCount() {
		return m_maxChargeCount;
	}
	public void setMaxChargeCount(int maxChargeCount) {
		m_maxChargeCount = maxChargeCount;
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
	
	private static HashMap<String,DataSpell> DATA_SPELL = initDataSpell();
	private static HashMap<String,DataSpell> initDataSpell() {
		HashMap<String,DataSpell> out = new HashMap<>();
		DataSpell data;
		
		data = new DataSpell();
		data.m_name = "Spread";
		data.m_iconeName = "Ispread";
		data.m_energyCost = 10;
		data.m_baseCooldown = 0.35f;
		data.setProperty("missileCount", "2");
		data.setProperty("missileType", "missile");
		out.put("spread", data);
		
		data = new DataSpell();
		data.m_name = "Dash";
		data.m_iconeName = "Idash";
		data.m_energyCost = 25;
		data.m_baseCooldown = 0.5f;
		out.put("dash", data);
		
		data = new DataSpell();
		data.m_name = "Heal";
		data.m_iconeName = "Iheal";
		data.m_energyCost = 50;
		data.m_baseCooldown = 5;
		data.m_maxChargeCount = 5;
		out.put("heal", data);
			
		return out;
	}
	
	public static DataSpell getDataSpell(String name) {
		return DATA_SPELL.get(name);
	}
}
