/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Graphic.GraphicMain;
import static Logic.Type.*;
import Maths.Vector2f;
import Physic.PhysicMain;
import java.util.ArrayList;
import org.lwjgl.glfw.GLFW;

public class Logic {
	public static double CURRENT_TIME=0;
	private static EntityUnit PLAYER;
	public static EntityUnit getPlayer() {
		if(PLAYER==null) {
			PLAYER=EntityUnit.create();
			PLAYER.setData(EntityDataUnit.get("player"));
			PLAYER.setPos(0.5f,0.5f);
			PLAYER.setTeam(0);
		}
		return PLAYER;
	}
	public static void killPlayer() {
		PLAYER=null;
	}
	private static void initData() {
		// data for static element
		EntityData e=EntityData.create("default");
		e.setSize(new Vector2f(0.1f,0.1f));
		e.setModel(GraphicMain.getModel("default"));
		
		e=EntityData.create("ground");
		e.setSize(new Vector2f(10f,0.5f));
		e.setModel(GraphicMain.getModel("repeat"));
		
		e=EntityData.create("block");
		e.setSize(new Vector2f(0.5f,0.5f));
		e.setModel(GraphicMain.getModel("repeat"));
		
		e=EntityData.create("prop/fire");
		e.setSize(new Vector2f(0.5f,0.5f));
		e.setModel(GraphicMain.getModel("fire"));
		
		// data for enemies
		EntityDataUnit d=EntityDataUnit.create("enemy");
		d.setSize(new Vector2f(0.1f,0.1f));
		d.setModel(GraphicMain.getModel("red"));
		d.setMaxHealth(50);
		d.setMaxShield(1000);
		d.setRegenShield(2);
		d.setMaxEnergy(100);
		d.setRegenEnergy(3);
		d.setType(SNIPER);
		d.setMaxSpeed(new Vector2f(0.1f,0.1f));
		
		d=EntityDataUnit.create("player");
		d.setSize(new Vector2f(0.15f,0.15f));
		d.setModel(GraphicMain.getModel("blue"));
		d.setMaxHealth(400);
		d.setRegenHealth(1);
		d.setMaxShield(100);
		d.setRegenShield(1);
		d.setMaxEnergy(0);
		d.setRegenEnergy(0);
		d.setType(GUNNER);
		d.setMaxSpeed(new Vector2f(0.1f,0.1f));
		
		// data for missile
		EntityDataMissile m=EntityDataMissile.create("missile");
		m.setSize(new Vector2f(0.05f,0.05f));
		m.setModel(GraphicMain.getModel("thunder"));
		m.setDamage(25);
		m.setMaxSpeed(1.0f);
		
		//data for particle
		EntityDataUnit p=EntityDataUnit.create("particle");
		p.setSize(new Vector2f(0.01f,0.01f));
		p.setModel(GraphicMain.getModel("fume"));
	}
	public static void init() {
		try{
			initData();
			Entity temp;

			temp=EntityUnit.create();
			temp.setData(EntityDataUnit.get("enemy"));
			temp.setPos(7,1);
			((EntityUnit)temp).setTeam(1);
			// ground
			temp=Entity.create();
			temp.setData(EntityData.get("ground"));
			temp.setPos(0,-0.5f);
			// left wall
			temp=Entity.create();
			temp.setPos(-1.5f,2);
			temp.setData(EntityData.get("block"));
			// right wall
			temp=Entity.create();
			temp.setPos(1.5f,2);
			temp.setData(EntityData.get("block"));
			// blocks
			temp=Entity.create();
			temp.setPos(-4f,1f);
			temp.setData(EntityData.get("block"));

			temp=Entity.create();
			temp.setPos(-2f,1f);
			temp.setData(EntityData.get("block"));

			temp=Entity.create();
			temp.setPos(2f,1f);
			temp.setData(EntityData.get("block"));

			temp=Entity.create();
			temp.setPos(4f,1f);
			temp.setData(EntityData.get("block"));
			// prop
			temp=Entity.create();
			temp.setPos(0f,1f);
			temp.setData(EntityData.get("prop/fire"));
			temp.setCollide(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void update() {
		CURRENT_TIME=GLFW.glfwGetTime();
		updateIA();
		updateMissile();
	}
	private static void updateIA() {
		ArrayList<EntityUnit> enemy=getEnemy();
		for(int i=0;i<enemy.size();i++) {
			EntityUnit e=enemy.get(i);
			if(e.isActive())
				e.update();
		}
	}
	private static void updateMissile() {
		ArrayList<EntityMissile> missile=getMissile();
		for(int i=0;i<missile.size();i++) {
			EntityMissile m=missile.get(i);
			if(m.isActive())
				m.update();
		}
	}
	public static void execute() {
		executeMissile();
	}
	private static void executeMissile() {
		ArrayList<EntityMissile> missile=getMissile();
		ArrayList<Entity> entity=getEntity();
		for(int i=0;i<missile.size();i++) {
			EntityMissile m=missile.get(i);
			if(m.isActive()) {
				for(int j=0;j<entity.size();j++) {
					Entity e=entity.get(j);
					if(e.getCollide() && !(e instanceof EntityMissile) && PhysicMain.collisionEE(m,e))
						m.explode(e);
				}
			}
		}
	}
	public static ArrayList<Entity> getEntity() {
		return Entity.getAll();
	}
	public static ArrayList<EntityDynamic> getDynamic() {
		return EntityDynamic.getAll();
	}
	public static ArrayList<EntityUnit> getEnemy() {
		return EntityUnit.getAll();
	}
	public static ArrayList<EntityMissile> getMissile() {
		return EntityMissile.getAll();
	}
}
