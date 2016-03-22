/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUIBossBar;
import GUI.GUIBuffBar;
import GUI.GUIPlayerBar;
import GUI.GUISpellBar;
import static Graphic.GraphicMain.drawString;
import Logic.EntityUnit;
import Logic.EntityWayPoint;
import Logic.Logic;
import Logic.Realm;
import Maths.Vector2f;
import Maths.Vector4f;
import Physic.PhysicMain;
import static Tests.Main.STATE_LEVEL;
import static Tests.Main.STATE_TRANSITION;
import static Tests.Main.getGameState;

public class GameStateLevel extends GameState {
	public GameStateLevel() {
		super();
	}
	@Override
	public void onEnter() {
		if(Realm.getActiveRealm()==null) {
			if(Realm.getRealmCount()>0)
				this.setRealm(0);
		} else Audio.Audio.continueMusic();
	}
	@Override
	public void onLeave() {
		Audio.Audio.pauseMusic();
	}
	public void changeRealm(int realm) {
		System.out.println("GameStateLevel changing realm to "+realm);
		((GameStateTransition)getGameState(STATE_TRANSITION)).setNextRealm(realm);
		System.out.println("GameStateLevel change game state.");
		Main.setGameState(STATE_LEVEL);
	}
	public void setRealm(int realm) {
		Realm.changeRealm(realm);
	}
	public Realm getActiveRealm() {
		return Realm.getActiveRealm();
	}
	private boolean checkWayPoint() {
		Realm r=getActiveRealm();
		if(r==null)
			return false;
		for(EntityWayPoint e:r.getWayPoints()) {
			if(PhysicMain.collisionEE(Logic.getPlayer(),e)) {
				//System.out.println("WAY POINT ACTIVATED: "+e.getID());
				EntityWayPoint t=e.getTargetWayPoint();
				if(t==null) {
					//System.out.println("NO TARGET");
					return false;
				}
				if(e.getDirection()!=0) {
					//System.out.println("Direction: "+e.getDirection());
					//System.out.println("Destination: "+(e.getRealm()+e.getDirection()));
					this.setRealm(e.getRealm()+e.getDirection());
				} else {
					t.moveToWayPoint(Logic.getPlayer());
				}
				return true;
			}
		}
		return false;
	}
	@Override
	public void execute() {
		inputHandler();
		Realm r=getActiveRealm();
		if(r!=null) {
			if(!checkWayPoint()) {
				r.update();
			}
		}
	}
	@Override
	public void drawCore() {
		Realm r=getActiveRealm();
		if(r!=null) {
			if(!checkWayPoint()) {
				r.draw();
			}
		}
	}
	@Override
	public void drawGUI() {
		super.drawGUI();
		
		Realm r=getActiveRealm();
		if(r!=null) {
			if(!checkWayPoint()) {
				drawString(r.getName(),new Vector2f(0,400),2f,new Vector4f(0,0,0,1));
				drawString(".-- --- ..- .-.. -.. / -.-- --- ..- / ... -- --- --- ... .... / .- / --. .... --- ... - ..--..",new Vector2f(0,0),2f,new Vector4f(0,0,0,1));
				String info="";
				info+="pos x:"+(float)((int)(Logic.getPlayer().getPos().x*1000))/1000f+"\n";
				info+="pos y:"+(float)((int)(Logic.getPlayer().getPos().y*1000))/1000f+"\n";
				//info+="speed x:"+(float)((int)(Logic.getPlayer().getSpeed().x*100000))/100000f+"\n";
				info+="speed y:"+(float)((int)(Logic.getPlayer().getSpeed().y*100000))/100000f;
				//System.out.println(info+"\n______________________________");
				drawString(info,new Vector2f(0,200),2f,new Vector4f(1,1,1,1));
				
				if(r.getBoss()!=null) {
					GUIBossBar.setBoss(r.getBoss());
					GUIBossBar.draw();
				}
				if(Logic.getPlayer()!=null) {
					GUIPlayerBar.setPlayer(Logic.getPlayer());
					GUIPlayerBar.draw();

					GUISpellBar.setPlayer(Logic.getPlayer());
					GUISpellBar.draw();

					GUIBuffBar.setPlayer(Logic.getPlayer());
					GUIBuffBar.draw();
				}
			}
		}
	}
	@Override
	public void inputHandler() {
		super.inputHandler();
		Realm r=getActiveRealm();
		if(r!=null) {
			EntityUnit player=Logic.getPlayer();/*
			if(Input.isPressed(Input.getBind(Input.BIND_JUMP))) {
				if(player.getContact(EntityDynamic.CONTACT_LEFT) && Input.isPressed(Input.getBind(Input.BIND_RIGHT))) {
					player.setLookRight(true);
					player.setSpeed(0.15f,0.085f);
					EntityParticle temp;
					for(int i=0;i<5;i++) {
							temp=new EntityParticle(((float)Math.random()*50)-25+225);
							temp.setData(EntityDataParticle.get("particle"));
							temp.setPos(player.getPos().x+player.getSize().x/2,player.getPos().y-player.getSize().y);
							r.addEntity(temp);
					}
				} else if(player.getContact(EntityDynamic.CONTACT_RIGHT) && Input.isPressed(Input.getBind(Input.BIND_LEFT))) {
					player.setLookRight(false);
					player.setSpeed(-0.15f,0.085f);
					EntityParticle temp;
					for(int i=0;i<5;i++) {
							temp=new EntityParticle(((float)Math.random()*50)-25+315);
							temp.setData(EntityDataParticle.get("particle"));
							temp.setPos(player.getPos().x+player.getSize().x/2,player.getPos().y+player.getSize().y);
							r.addEntity(temp);
					}
				} else if(player.getContact(EntityDynamic.CONTACT_DOWN)){
					player.jump();
				}
			}
			if(Input.isPressed(Input.getBind(Input.BIND_LEFT)) && !Input.isPressed(Input.getBind(Input.BIND_RIGHT))) {
				if(player.getSpeed().x>-0.075f)
					player.setSpeed(-0.075f,player.getSpeed().y);
				player.setLookRight(false);
				EntityParticle temp;
				if(player.getContact(EntityDynamic.CONTACT_DOWN) && Math.random()<0.2) {
					temp=new EntityParticle(((float)Math.random()*50)-25+0);
					temp.setData(EntityDataParticle.get("particle"));
					temp.setPos(player.getPos().x+player.getSize().x/2,player.getPos().y-player.getSize().y);
					r.addEntity(temp);
				}
			}
			else if(Input.isPressed(Input.getBind(Input.BIND_RIGHT)) && !Input.isPressed(Input.getBind(Input.BIND_LEFT))) {
				if(player.getSpeed().x<0.075f)
					player.setSpeed(0.075f,player.getSpeed().y);
				player.setLookRight(true);
				EntityParticle temp;
				if(player.getContact(EntityDynamic.CONTACT_DOWN) && Math.random()<0.2) {
					temp=new EntityParticle(((float)Math.random()*50)-25+180);
					temp.setData(EntityDataParticle.get("particle"));
					temp.setPos(player.getPos().x-player.getSize().x/2,player.getPos().y-player.getSize().y);
					r.addEntity(temp);
				}
			} else player.setSpeed(player.getSpeed().x*0.5f,player.getSpeed().y);
			if(Input.isPressed(Input.getBind(Input.BIND_SHOOT))) {
				float dir=-1;
				if(player.getLookRight())
					dir=1;
				player.shoot(new Vector2f(dir,0));
			}*/
			player.resetInput();
			if(Input.isPressed(Input.getBind(Input.BIND_LEFT))) {
				player.setInput(EntityUnit.INPUT_LEFT, true);
			}
			if(Input.isPressed(Input.getBind(Input.BIND_RIGHT))) {
				player.setInput(EntityUnit.INPUT_RIGHT, true);
			}
			if(Input.isPressed(Input.getBind(Input.BIND_UP))) {
				player.setInput(EntityUnit.INPUT_UP, true);
			}
			if(Input.isPressed(Input.getBind(Input.BIND_DOWN))) {
				player.setInput(EntityUnit.INPUT_DOWN, true);
			}
			if(Input.isPressed(Input.getBind(Input.BIND_JUMP))) {
				player.setInput(EntityUnit.INPUT_JUMP, true);
			}
			if(Input.isPressed(Input.getBind(Input.BIND_SHOOT))) {
				float dir=-1;
				if(player.getLookRight())
					dir=1;
				player.shoot(new Vector2f(dir,0));
			}
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_1)))
				player.useSpell(0);
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_2)))
				player.useSpell(1);
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_3)))
				player.useSpell(2);
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_4)))
				player.useSpell(3);
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_5)))
				player.useSpell(4);
			
			if(Input.isPushed(Input.getBind(Input.BIND_MENU))) {
				Main.changeGameState(Main.STATE_MAIN_MENU);
			}
		}
	}
}
