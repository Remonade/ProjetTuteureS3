package Logic;

import GUI.GUIDialog;
import Logic.Data.Player;
import Logic.Data.EntityDataUnit;
import Logic.Data.EntityDataMissile;
import Graphic.GraphicMain;
import Graphic.Model;
import Graphic.ModelAnim;
import Logic.Buff.Buff;
import Logic.Data.EntityDataParticle;
import Logic.IA.IA;
import Logic.Spell.Spell;
import static Logic.Type.*;
import Maths.Vector2f;
import Maths.Vector4f;
import Physic.PhysicMain;
import java.util.ArrayList;

public class EntityUnit extends EntityDynamic {
    protected boolean m_lookRight; //True means "look to the right"
	protected float m_health=1f;
    protected float m_energy=1f;
    protected float m_shield=1f;
	protected double m_shieldCooldown=0;
	protected double m_weaponCooldown=0;
	
	protected double m_animTime=0;
	protected String m_currentAnim="NAN";
	protected int m_custom=0;
	protected Player m_owner;
	protected IA m_ia=null;
	
	// input
	protected boolean[] m_inputs=new boolean[5];
	public static final int INPUT_LEFT=0;
	public static final int INPUT_RIGHT=1;
	public static final int INPUT_UP=2;
	public static final int INPUT_DOWN=3;
	public static final int INPUT_JUMP=4;
	
	public void resetInput() {
		for(int i=0;i<m_inputs.length;i++)
			m_inputs[i]=false;
	}
	public void setInput(int input, boolean value) {
		if(input>-1 && input<m_inputs.length)
			m_inputs[input]=value;
	}
	public boolean getInput(int input) {
		if(input>-1 && input<m_inputs.length)
			return m_inputs[input];
		return false;
	}
	public void move() {
		Realm r=Realm.getActiveRealm();
		if(getInput(INPUT_JUMP)) {
			jump();
		}
		if(getInput(INPUT_LEFT) && !getInput(INPUT_RIGHT)) {
			if(getSpeed().x>-0.075f)
				setSpeed(-0.075f,getSpeed().y);
			setLookRight(false);
			EntityParticle temp;
			if(getContact(CONTACT_DOWN) && Math.random()<0.2) {
				temp=new EntityParticle(((float)Math.random()*50)-25+0);
				temp.setData(EntityDataParticle.get("particle"));
				temp.setPos(getPos().x+getSize().x/2,getPos().y-getSize().y);
				r.addEntity(temp);
			}
		} else if(getInput(INPUT_RIGHT) && !getInput(INPUT_LEFT)) {
			if(getSpeed().x<0.075f)
				setSpeed(0.075f,getSpeed().y);
			setLookRight(true);
			EntityParticle temp;
			if(getContact(CONTACT_DOWN) && Math.random()<0.2) {
				temp=new EntityParticle(((float)Math.random()*50)-25+180);
				temp.setData(EntityDataParticle.get("particle"));
				temp.setPos(getPos().x-getSize().x/2,getPos().y-getSize().y);
				r.addEntity(temp);
			}
		} else setSpeed(getSpeed().x*0.5f,getSpeed().y);
		/*if(getInput(INPUT_UP) && !getInput(INPUT_DOWN)) {
			if(getSpeed().x>-0.075f)
				setSpeed(getSpeed().x,0.075f);
		} else if(getInput(INPUT_DOWN) && !getInput(INPUT_UP)) {
			if(getSpeed().x<0.075f)
				setSpeed(getSpeed().x,-0.075f);
		}*/
	}
	// buff part
	protected float m_buffHealthRegen=0;
	protected float m_buffShieldRegen=0;
	protected float m_buffEnergyRegen=0;
	
	protected float m_buffHealthMax=0;
	protected float m_buffShieldMax=0;
	protected float m_buffEnergyMax=0;
	
	protected int m_buffStun;
	
	protected ArrayList<Spell> m_spellList=new ArrayList<>();
	protected ArrayList<Buff> m_buffList=new ArrayList<>();
	
	public boolean isStun() {
		return m_buffStun>0;
	}
	
	public void setOwner(Player owner) {
		m_owner=owner;
	}
	public Player getOwner() {
		return m_owner;
	}
    /**
    * Default constructor
    */
    public EntityUnit() {
        super();
		m_owner=null;
        m_lookRight = true;
            m_name="Batard!";
    }
	public void addSpell(Spell spell) {
		m_spellList.add(spell);
	}
	public ArrayList<Spell> getSpellList() {
		return m_spellList;
	}
	public void useSpell(int id) {
		if(id<m_spellList.size())
			m_spellList.get(id).use(this);
	}
	
	// buff
	public void addBuffHealthRegen(float v) {
		m_buffHealthRegen+=v;
	}
	public void addBuffShieldRegen(float v) {
		m_buffShieldRegen+=v;
	}
	public void addBuffEnergyRegen(float v) {
		m_buffEnergyRegen+=v;
	}
	
	public void addBuffHealthMax(float v) {
		m_buffHealthMax+=v;
	}
	public void addBuffShieldMax(float v) {
		m_buffShieldMax+=v;
	}
	public void addBuffEnergyMax(float v) {
		m_buffEnergyMax+=v;
	}
	public void addBuffStun() {
		m_buffStun++;
	}
	
	public void removeBuffHealthRegen(float v) {
		m_buffHealthRegen-=v;
	}
	public void removeBuffShieldRegen(float v) {
		m_buffShieldRegen-=v;
	}
	public void removeBuffEnergyRegen(float v) {
		m_buffEnergyRegen-=v;
	}
	
	public void removeBuffHealthMax(float v) {
		m_buffHealthMax-=v;
	}
	public void removeBuffShieldMax(float v) {
		m_buffShieldMax-=v;
	}
	public void removeBuffEnergyMax(float v) {
		m_buffEnergyMax-=v;
	}
	
	public void removeBuffStun() {
		if(m_buffStun>0)
			m_buffStun--;
	}
	
	public void addBuff(Buff b) {
		b.onStart(this);
		m_buffList.add(b);
	}
	public void removeBuff(Buff b) {
		b.onExpire(this);
		m_buffList.remove(b);
	}
	public ArrayList<Buff> getBuffList() {
		return m_buffList;
	}
	public void updateBuff() {
		ArrayList<Buff>expire=new ArrayList<>();
		for(Buff b:m_buffList) {
			if(b.update(this))
				expire.add(b);
		}
		for(Buff b:expire)
			removeBuff(b);
	}
	@Override
	public String getAnim() {
		return m_currentAnim;
	}
	@Override
	public double getAnimTime() {
		return m_animTime;
	}
	public void setIA(IA ia) {
		m_ia=ia;
	}
	public int getCustomValue() {
		return m_custom;
	}
	public void setCustomValue(int value) {
		m_custom=value;
	}
	public float getPercentShield() {
		return m_shield;
	}
	public float getShield() {
		return m_shield*getMaxShield();
	}
	public float getRegenShield() {
		if(m_data==null)
			return 0;
		float regen=((EntityDataUnit)m_data).getRegenShield();
		if(m_owner!=null)
			regen=(1+m_owner.getShieldPower())*regen;
		return regen+m_buffShieldRegen;
	}
	public float getMaxShield() {
		if(m_data==null)
			return 0;
		float max=((EntityDataUnit)m_data).getMaxShield();
		if(m_owner!=null)
			max=(1+m_owner.getShieldPower())*max;
		return max+m_buffShieldMax;
	}
	public float getPercentEnergy() {
		return m_energy;
	}
    public float getEnergy() {
		return m_energy*getMaxEnergy();
    }
    public float getRegenEnergy() {
        if(m_data==null)
            return 0;
		float regen=((EntityDataUnit)m_data).getRegenEnergy();
		if(m_owner!=null)
			regen=(1+m_owner.getEnergyPower())*regen;
        return regen+m_buffEnergyRegen;
    }
    public float getMaxEnergy() {
		if(m_data==null)
            return 0;
		float max=((EntityDataUnit)m_data).getMaxEnergy();
		if(m_owner!=null)
			max=(1+m_owner.getEnergyPower())*max;
		return max+m_buffEnergyMax;
    }
    public float getHealth() {
		return m_health*getMaxHealth();
    }
    public float getPercentHealth() {
		return m_health;
    }
    public float getRegenHealth() {
        if(m_data==null)
            return 0;
		float regen=((EntityDataUnit)m_data).getRegenHealth();
		if(m_owner!=null)
			regen=(1+m_owner.getHealthPower())*regen;
        return regen+m_buffHealthRegen;
    }
    public float getMaxHealth() {
		if(m_data==null)
            return 0;
		float max=((EntityDataUnit)m_data).getMaxHealth();
		if(m_owner!=null)
			max=(1+m_owner.getHealthPower())*max;
		return max+m_buffHealthMax;
    }
    public boolean getLookRight(){
        return m_lookRight;
    }
    public void setLookRight(boolean lookRight) {
        m_lookRight=lookRight;
    }
	public void updateAnim() {
		if(!m_contact[CONTACT_DOWN]) {
			if(m_contact[CONTACT_LEFT]) {
				m_currentAnim="WALL";
				m_lookRight=false;
			}
			else if(m_contact[CONTACT_RIGHT]) {
				m_currentAnim="WALL";
				m_lookRight=true;
			} else
				m_currentAnim="JUMP";
			m_animTime=0;
		} else if(Math.abs(m_speed.x)>0.01f) {
			if(!m_currentAnim.equals("RUN")) {
				m_currentAnim="RUN";
				m_animTime=0;
			}
		} else if(!m_currentAnim.equals("NAN")) {
			m_currentAnim="NAN";
			m_animTime=0;
		}
	}
    @Override
    public void update() {
        super.update();
		move();
		/*if(m_dialog==null)
			if(Math.random()<0.01) {
				talk("Je disparais a nouveau.");
			}*/
		
		if(m_shieldCooldown<Logic.DELTA_TIME)
			m_shieldCooldown=0;
		else m_shieldCooldown-=Logic.DELTA_TIME;
		
		m_animTime+=Logic.DELTA_TIME;
		
		if(m_currentAnim.equals("SHOOT")) {
			if(m_animTime>0.25)
				updateAnim();
		} else updateAnim();
		
        regen();
		
		for(Spell s: m_spellList)
			s.refreshCooldown();
		updateBuff();
		if(m_ia!=null)
			m_ia.execute(this);
    }
	@Override
	public void addSpeed(Vector2f speed) {
		addSpeed(speed.x,speed.y);
	}
	@Override
	public void addSpeed(float x,float y) {
		super.addSpeed(x,y);
		Vector2f max=getMaxSpeed();
		if(Math.abs(m_speed.x)>max.x)
			m_speed.x=max.x*Math.signum(m_speed.x);
		if(m_speed.y<PhysicMain.FALL_SPEED_LIMIT)
			m_speed.y=PhysicMain.FALL_SPEED_LIMIT;
		if(m_speed.y>max.y)
			m_speed.y=max.y*Math.signum(m_speed.x);
	}
	public Vector2f getMaxSpeed() {
		if(this.getData()!=null)
			return ((EntityDataUnit)m_data).getMaxSpeed();
		return new Vector2f(0.05f,0.05f);
	}
    public void kill() {
		Audio.Audio.playSound(getSound("death"));
		Realm.getActiveRealm().removeEntity(this);
		if(this==Logic.getPlayer())
			Logic.killPlayer();
    }
	public boolean spendEnergy(float energy) {
		float percent=energy/getMaxEnergy();
		if(m_energy<percent)
			return false;
		m_energy-=percent;
		return true;
	}
    public boolean shoot(Vector2f target) {
		if(!isStun() && spendEnergy(25)) {
			m_weaponCooldown=Logic.CURRENT_TIME;
			EntityMissile mis=EntityMissile.create();
			mis.setData(EntityDataMissile.get("missile"));
			mis.setDir(target);
			Realm.getActiveRealm().addEntity(mis);
			mis.setPos(getPos());
			mis.setOwner(this);
			mis.setTeam(getTeam());
			m_currentAnim="SHOOT";
			m_animTime=0;
			return true;
		}
		return false;
    }
    public void jump() {
		Realm r=Realm.getActiveRealm();
		if(getContact(CONTACT_LEFT) && getInput(INPUT_RIGHT)) {
			setLookRight(true);
			setSpeed(0.15f,0.085f);
			EntityParticle temp;
			for(int i=0;i<5;i++) {
					temp=new EntityParticle(((float)Math.random()*50)-25+225);
					temp.setData(EntityDataParticle.get("particle"));
					temp.setPos(getPos().x+getSize().x/2,getPos().y-getSize().y);
					r.addEntity(temp);
			}
		} else if(getContact(CONTACT_RIGHT) && getInput(INPUT_LEFT)) {
			setLookRight(false);
			setSpeed(-0.15f,0.085f);
			EntityParticle temp;
			for(int i=0;i<5;i++) {
					temp=new EntityParticle(((float)Math.random()*50)-25+315);
					temp.setData(EntityDataParticle.get("particle"));
					temp.setPos(getPos().x+getSize().x/2,getPos().y+getSize().y);
					r.addEntity(temp);
			}
		} else if(getContact(CONTACT_DOWN)){
				setSpeed(0,0.1f);
		}
    }
    public boolean damage(int damage) {
		m_shieldCooldown=SHIELD_REGEN_DELAY;
		if(getShield()>=damage)
			m_shield-=damage/getMaxShield();
		else {
			float diff=damage-getShield();
			m_shield=0;
			m_health-=diff/getMaxHealth();
			if(m_health<=0) {
				kill();
				return true;
			} else Audio.Audio.playSound(getSound("hurt"));
		}
		return false;
    }
	public void heal(int heal) {
		float percent=heal/getMaxHealth();
		m_health+=percent;
		if(m_health>1.0f)
			m_health=1.0f;
	}
    public void attack(EntityUnit target) {
		if(!isStun()) {
			Vector2f pos=target.getPos();
			Vector2f size=target.getSize();
			switch(((EntityDataUnit)m_data).getType()){
				case GUNNER :
					if(Physic.PhysicMain.inRow(pos, size.y, getPos(), getSize().y)) {
						if(getMaxEnergy()==m_energy)
							m_custom=1; //the gunner can launch a burst with his weapon
						if(m_custom==1 && m_energy-50>0f) { //the gunner shoot
							if(m_lookRight)
								shoot(new Vector2f(1,0));
							else
								shoot(new Vector2f(-1,0)); 
						} else
							m_custom=0; //the weapon of the gunner is overheated and he needs to wait before launching another burst
					}
					break;
				case SNIPER :
					if(m_energy-50>0f) {
						shoot(getPos().subtract(pos).negate());
					}
					break;
				default :
					break;
			}
		}
    }
    public void regen() {
		if(m_shieldCooldown<=0.000f && m_shield<1.0f)
			m_shield+=getRegenShield()/getMaxShield();
		if(m_shield>1.0f)
			m_shield=1.0f;
		if(m_energy<1.0f)
			m_energy+=getRegenEnergy()/getMaxEnergy();
		if(m_energy>1.0f)
			m_energy=1.0f;
		if(m_health<1.0f)
			m_health+=getRegenHealth()/getMaxHealth();
		if(m_health>1.0f)
			m_health=1.0f;
    }
    private static final float SHIELD_REGEN_DELAY=1;
    @Override
    public void draw() {
		if(!m_lookRight)
			GraphicMain.setDirection(-1);
		super.draw();
		ModelAnim shield=(ModelAnim)GraphicMain.getModel("shield");
		
		Vector4f color=null;
		if(m_data!=null)
			color=m_data.getColor();
		if(color==null)
			color=new Vector4f(1,1,1,1);
		
		color.w*=m_shield/3;
		
		shield.draw(m_pos,getSize().scale(1.5f),color, Logic.CURRENT_TIME, "NAN");
		
		if(m_shield<1.0f || m_health<1.0f) {
			Vector2f barSize=new Vector2f(getSize().x*m_health,0.03f);
			Vector2f barPos=new Vector2f(m_pos.x,m_pos.y+getSize().y+barSize.y);
			Model.renderTexture("",barPos, barSize, new Vector4f(1,0,0,1));
			
			barSize=new Vector2f(getSize().x*m_shield,0.03f);
			barPos=new Vector2f(m_pos.x,m_pos.y+getSize().y+barSize.y+0.06f);
			Model.renderTexture("",barPos, barSize, new Vector4f(0,0,1,1));
		}
		//GraphicMain.drawString((int)getHealth()+"/"+getMaxHealth(),m_pos.add(getSize()),0.01f,new Vector4f(0,1,0,1));
		//GraphicMain.drawString((int)getShield()+"/"+getMaxShield(),m_pos.add(new Vector2f(getSize().x,0)),0.01f,new Vector4f(0,0,1,1));
		//GraphicMain.drawString((int)getEnergy()+"/"+getMaxEnergy(),m_pos.add(new Vector2f(getSize().x,-getSize().y)),0.01f,new Vector4f(1,0,1,1));
		//if(m_owner!=null)
			//GraphicMain.drawString(m_owner.getName()+" - "+m_owner.getLevel(),m_pos.add(new Vector2f(-getSize().x,getSize().y*2)),0.03f,new Vector4f(1,1,0,1));
		GraphicMain.setDirection(1);
		drawDialog();
	}
	
	/* ------------------------------------------------------------- */
	
	private GUIDialog m_dialog=null;
	public boolean isTalking() {
		return m_dialog!=null;
	}
	public void talk(String speach) {
		Audio.Audio.playSound(getSound("talk"));
		m_dialog=new GUIDialog(speach);
	}
	public void drawDialog() {
		if(m_dialog!=null) {
			m_dialog.setPos(getPos().add(new Vector2f(0,m_dialog.getSize().y+getSize().y+0.12f)));
			m_dialog.draw();
			if(!m_dialog.update())
				m_dialog=null;
		}
	}
}
