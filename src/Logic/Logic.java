/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Logic.Data.Player;
import Logic.Data.EntityDataUnit;
import Logic.Data.EntityDataMissile;
import Logic.Data.EntityData;
import Graphic.GraphicMain;
import Logic.IA.IA;
import Logic.IA.IARoamer;
import Logic.Spell.Spell;
import static Logic.Type.SNIPER;
import Maths.Vector2f;
import Tests.GameStateLevel;
import Tests.Main;
import static Tests.Main.getGameState;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;

public class Logic {
	public static double CURRENT_TIME=0;
	public static double DELTA_TIME=0;
	public static void updateTime() {
		DELTA_TIME=GLFW.glfwGetTime()-CURRENT_TIME;
		CURRENT_TIME+=DELTA_TIME;
	}
	private static EntityUnit PLAYER=null;
	private static Player LOCAL_PLAYER=null;
	public static float LEVEL_POWER=0.05f;
	public static Realm getActiveRealm() {
		return  ((GameStateLevel)getGameState(2)).getActiveRealm();
	}
	public static EntityUnit getPlayer() {
		if(LOCAL_PLAYER==null) {
			LOCAL_PLAYER=new Player();
			LOCAL_PLAYER.setName("Daratrix");
			LOCAL_PLAYER.setLevel(0);
		}
		if(PLAYER==null) {
			if(Realm.getRealmCount()>0) {
				Realm r=getActiveRealm();
				if(r!=null) {
					PLAYER=LOCAL_PLAYER.getPlayerEntity();
					Vector2f pos=getActiveRealm().getWayPoints().get(0).getPos();
					//pos=pos.add(getActiveRealm().getWayPoints().get(0).getOut());

					PLAYER.setPos(pos);
					getActiveRealm().addEntity(PLAYER);
				} else return null;
			} else return null;
		}
		return PLAYER;
	}
	public static void killPlayer() {
		try {
			for(int i=0;i<Realm.getRealmCount();i++) {
				Realm.getRealm(i).removeEntity(PLAYER);
			}
			PLAYER=null;
			Audio.Audio.playSound("Wscream.ogg");
			Main.setGameState(Main.STATE_GAME_OVER);
		} catch (Exception ex) {
			Logger.getLogger(Logic.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static void init() {
		try{
			loadEntityData("data/entity.data");
			generateRun(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void updateRealm(Realm r) {
		// IA
		ArrayList<EntityUnit> units=r.getUnits();
		for(int i=0;i<units.size();i++) {
			EntityUnit u=units.get(i);
			if(u.isActive()) {
				u.update();
			}
		}
		// Missile
		ArrayList<EntityMissile> missiles=r.getMissiles();
		for(int i=0;i<missiles.size();i++) {
			EntityMissile m=missiles.get(i);
			if(m.isActive())
				m.update();
		}
	}
	public static void loadEntityData(String path) throws IOException {
		System.out.println("Clear Current Entity Data");
		clearData();
		Charset encoding=StandardCharsets.UTF_8;
		System.out.println("Attempting to load "+path);
		List<String> lines = Files.readAllLines(Paths.get(path), encoding);
		System.out.println("Setting New Entity Data");
		createEntityData(lines);
	}
	private static void createEntityData(List<String> lines) {
		EntityData e=null;
		EntityDataUnit u=null;
		EntityDataMissile m=null;
		for(String l:lines) {
			if(l.charAt(0)=='+')
				continue;
			String[] data = l.split(" ");
			String action=data[0];
			if("new".equals(action)) { // create new thing
				String object=data[1];
					String name=data[2];
				if("EntityData".equals(object)) {
					u=null;
					m=null;
					e=EntityData.create(name);
					System.out.println("new EntityData "+name);
				} else if("EntityDataUnit".equals(object)) {
					u=EntityDataUnit.create(name);
					m=null;
					e=u;
					System.out.println("new EntityDataUnit "+name);
				} else if("EntityDataMissile".equals(object)) {
					u=null;
					m=EntityDataMissile.create(name);
					e=m;
					System.out.println("new EntityDataMissile "+name);
				}
			} else if("set".equals(action) && e!=null) { // set stuff
				String attribute=data[1];
				if("Size".equals(attribute)) {
					String sizeX=data[2];
					String sizeY=data[3];
					e.setSize(Float.valueOf(sizeX),Float.valueOf(sizeY));
				} else if("Model".equals(attribute)) {
					String model=data[2];
					e.setModel(GraphicMain.getModel(model));
				} else if("Color".equals(attribute)) {
					String r=data[2];
					String g=data[3];
					String b=data[4];
					String a=data[5];
					e.setColor(Float.valueOf(r),Float.valueOf(g),Float.valueOf(b),Float.valueOf(a));
				}
				if(u!=null) {
					if("Health".equals(attribute)) {
						String max=data[2];
						String regen=data[3];
						u.setMaxHealth(Float.valueOf(max));
						u.setRegenHealth(Float.valueOf(regen));
					} else if("Shield".equals(attribute)) {
						String max=data[2];
						String regen=data[3];
						((EntityDataUnit)e).setMaxShield(Float.valueOf(max));
						((EntityDataUnit)e).setRegenShield(Float.valueOf(regen));
					} else if("Energy".equals(attribute)) {
						String max=data[2];
						String regen=data[3];
						u.setMaxEnergy(Float.valueOf(max));
						u.setRegenEnergy(Float.valueOf(regen));
					} else if("Type".equals(attribute)) {
						String type=data[2];
						u.setType(SNIPER);
					}
				} else if(m!=null) {
					if("Speed".equals(attribute)) {
						String speed=data[2];
						m.setMaxSpeed(Float.valueOf(speed));
					} else if("Damage".equals(attribute)) {
						String damage=data[2];
						m.setDamage(Integer.valueOf(damage));
					}
				}
			}
		}
	}
	public static void generateRun(int difficulty) {
		clearRun();
		ArrayList<Integer> levels=new ArrayList<>();
		levels.add(0);
		for(int i=1;i<6;i++)
			levels.add(i);
		/*for(int i=0;i<difficulty;i++) {
			int id=1+(int)(Math.random()*4);
			levels.add(id);
		}*/
		levels.add(6);
		loadLevelList(levels);
	}
	public static void loadLevelList(Collection<Integer> list) {
		for(int i:list) {
			try {
				loadLevel(i+".data");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void loadLevel(String path) throws IOException {
		Charset encoding=StandardCharsets.UTF_8;
		System.out.println("Attempting to load "+path);
		List<String> lines = Files.readAllLines(Paths.get("data/world/"+path), encoding);
		System.out.println("Build new level");
		createLevel(lines);
	}
	private static void createLevel(List<String> lines) {
		int realm=-1;
		Entity e=null;
		EntityUnit u=null;
		for(String l:lines) {
			if(l.charAt(0)=='+')
				continue;
			String[] data = l.split(" ");
			String action=data[0];
			if("set".equals(action)) { // set environnement value
				String value=data[1];
				if(value.equals("Background")) {
					String model=data[2];
					Realm.getRealm(realm).setBackground(GraphicMain.getModel(model));
					System.out.println("set Backround to "+model+" for "+realm);
				} else if(value.equals("Music")) {
					String music=data[2];
					Audio.Audio.loadSoundData(music);
					Realm r=Realm.getRealm(realm);
					if(r!=null)
						r.setMusic(music);
					System.out.println("set Music to "+music+" for "+realm);
				} else if(value.equals("CameraBound")) {
					float left=Float.valueOf(data[2]);
					float right=Float.valueOf(data[3]);
					float bottom=Float.valueOf(data[4]);
					float top=Float.valueOf(data[5]);
					
					Realm.getRealm(realm).setCameraBound(left,right,bottom,top);
					System.out.println("set CameraBound to "+left+" "+right+" "+bottom+" "+top+" for "+realm);
				} else if(value.equals("Boss")) {
					if(u!=null);
						Realm.getRealm(realm).setBoss(u);
					System.out.println("set Boss for "+realm);
				} else if(value.equals("CollisionOff")) {
					if(u!=null)
						u.setCollide(false);
					if(e!=null)
						e.setCollide(false);
					System.out.println("set Collision Off int "+realm);
				} else if(value.equals("IA")) {
					String ia=data[2];
					int APM=0;
					if(data.length>3) APM=Integer.valueOf(data[3]);
					if(u!=null) {
						if(ia.equals("Null") || ia.equals("0") || ia.equals("None")) u.setIA(null);
						if(ia.equals("Default")) u.setIA(new IA(APM));
						if(ia.equals("Roamer")) u.setIA(new IARoamer(APM));
					}
					System.out.println("set IA to "+ia+"("+APM+")");
				} else if(value.equals("CustomValue")) {
					int custom=0;
					if(data.length>2) custom=Integer.valueOf(data[2]);
					if(u!=null) {
						u.setCustomValue(custom);
					}
					System.out.println("set Custom Value to "+custom);
				}
			} else if("new".equals(action)) { // create new thing
				String object=data[1];
				if("Realm".equals(object)) {
					String name=data[2];
					realm=Realm.createRealm(name+"("+(Realm.getRealmCount()+1)+")");
					System.out.println("create Realm ("+realm+"): "+name);
				} else if(realm>-1) {
					String type=data[2];
					String posX=data[3];
					String posY=data[4];
					if("Entity".equals(object)) {
						System.out.println("create Entity: "+type+" "+posX+","+posY);
						u=null;
						e=new Entity();
						e.setData(EntityData.get(type));
						e.setPos(Float.valueOf(posX),Float.valueOf(posY));
						Realm.getRealm(realm).addEntity(e);
					} else if("WayPoint".equals(object)) {
						String direction=data[5];
						String target=data[6];
						String outX=data[7];
						String outY=data[8];
						EntityWayPoint temp;
						temp=EntityWayPoint.create();
						if(temp!=null) {
							temp.setData(EntityData.get(type));
							temp.setPos(Float.valueOf(posX),Float.valueOf(posY));
							temp.setDirection(Integer.valueOf(direction));
							temp.setTarget(Integer.valueOf(target));
							temp.setOut(Float.valueOf(outX),Float.valueOf(outY));
							temp.setRealm(realm);
							Realm.getRealm(realm).addEntity(temp);
							System.out.println("create WayPoint: "+type+" ("+posX+","+posY+")->("+outX+","+outY+") in realm "+temp.getRealm()+" with ID "+temp.getID());
						}
					} else if("Unit".equals(object)) {
						String team=data[5];
						System.out.println("create Unit: "+type+" "+posX+","+posY+" in team "+team);
						e=null;
						u=new EntityUnit();
						u.setData(EntityDataUnit.get(type));
						u.setPos(Float.valueOf(posX),Float.valueOf(posY));
						u.setTeam(Integer.valueOf(team));
						u.setIA(new IA(0));
						u.addSpell(new Spell("Empty",0,0));
						Realm.getRealm(realm).addEntity(u);
					}
					
				}
			
			}
		}
	}
	private static void clearRun() {
		Realm.clearAll();
	}
	private static void clearData() {
		EntityData.clear();
	}
	public static void clear() {
		clearData();
	}
	public static int MAX_LEVEL=100;
	public static int getNextLevelXP(int level) {
		return level*level*10;
	}
}
