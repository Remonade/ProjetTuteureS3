package Logic.Data;

import Logic.EntityUnit;
import Logic.Logic;
import Logic.Spell.SpellDash;
import Logic.Spell.SpellHeal;
import Logic.Spell.SpellShotSpread;
import java.io.Serializable;


public class Player implements Serializable {
	public Player() {
		m_level=1;
		m_exp=0;
	}
	private String m_name;
	public void setName(String name) {
		m_name=name;
	}
	public String getName() {
		return m_name;
	}
	private int m_level;
	public int getLevel() {
		return m_level;
	}
	private int m_exp;
	public int getExp() {
		return m_exp;
	}
	
	private float m_healthPower=0.05f;
	private float m_shieldPower=0.07f;
	private float m_energyPower=0.05f;
	
	public float getHealthPower() {
		return m_level*m_healthPower;
	}
	public float getShieldPower() {
		return m_level*m_shieldPower;
	}
	public float getEnergyPower() {
		return m_level*m_energyPower;
	}
	
	// scrore stuff - glory things
	private int m_totalExp;
	private float m_totalDmgDealt;
	private float m_totalDmgGet;
	private float m_totalHealDealt;
	private float m_totalHealGet;
	private float m_maxDamage;
	private float m_maxHeal;
	
	private int m_playerKill;
	private int m_playerDeath;
	
	private int m_monsterKill;
	private int m_monsterDeath;
	
	private int m_questCompleted;
	private int m_questFailed;
	
	public boolean addLevel(int level) {
		return setLevel(m_level+level);
	}
	public boolean setLevel(int level) {
		if(level>Logic.MAX_LEVEL)
			level=Logic.MAX_LEVEL;
		else if(level<1)
			level=1;
		if(level==m_level)
			return false;
		m_level=level;
		m_exp=0;
		return true;
	}
	public void addXP(int exp) {
		m_exp+=exp;
		m_totalExp+=exp;
		while(levelUp());
	}
	private boolean levelUp() {
		if(m_exp>Logic.getNextLevelXP(m_level+1)) {
			if(m_level<Logic.MAX_LEVEL) {
				m_exp-=Logic.getNextLevelXP(m_level+1);
				m_level++;
				return true;
			} else return false;
		} else if(m_exp<0 && -m_exp>Logic.getNextLevelXP(m_level)) {
			if(m_level>1) {
				m_exp+=Logic.getNextLevelXP(m_level);
				m_level--;
				return true;
			} else return false;
		}
		return false;
	}
	
	public EntityUnit getPlayerEntity() {
		EntityUnit player=new EntityUnit();
		
		player.setOwner(this);
		player.setData(EntityDataUnit.get("player"));
		player.setTeam(0);
		player.addSpell(new SpellShotSpread("Spread",4,0.05f,2));
		player.addSpell(new SpellDash("Dash",10,1));
		player.addSpell(new SpellHeal("Heal",10,0.5f,-1,250));
		//player.addSpell(new Spell("Health",10,0.35f,20));
		//player.addSpell(new SpellSummon("Invoque",25,10,5,"enemy"));
		
		return player;
	}
}
