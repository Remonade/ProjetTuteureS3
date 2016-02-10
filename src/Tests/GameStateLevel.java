/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import Logic.Data.EntityDataUnit;
import Logic.EntityDynamic;
import Logic.EntityParticle;
import Logic.EntityUnit;
import Logic.EntityWayPoint;
import Logic.Logic;
import Logic.Realm;
import Maths.Vector2f;
import Physic.PhysicMain;

public class GameStateLevel extends GameState {
	public GameStateLevel() {
		super();
		m_showCursor=false;
	}
	@Override
	public void onEnter() {
		if(Realm.getActiveRealm()==null) {
			if(Realm.getRealmCount()>0)
				this.changeRealm(0);
		} else Audio.Audio.continueMusic();
	}
	@Override
	public void onLeave() {
		Audio.Audio.pauseMusic();
	}
	
	
	public void changeRealm(int realm) {
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
				GameStateLevel gst=(GameStateLevel)Main.getGameState(Main.STATE_LEVEL);
				gst.changeRealm(e.getRealm()+e.getDirection());
				t.moveToWayPoint(Logic.getPlayer());
				return true;
			}
		}
		return false;
	}
	@Override
	public void execute() {
		Realm r=getActiveRealm();
		inputHandler();
		if(r!=null) {
			if(!checkWayPoint()) {
				r.update();
				r.draw();
			}
		}
		//GraphicMain.camera.move(0,0.01f);
	}
	
	@Override
	public void inputHandler() {
		Realm r=getActiveRealm();
		if(r!=null) {
			EntityUnit player=Logic.getPlayer();
			super.inputHandler();
			if(Input.isPressed(Input.getBind(Input.BIND_JUMP))) {
				if(player.getContact(EntityDynamic.CONTACT_LEFT) && Input.isPressed(Input.getBind(Input.BIND_RIGHT))) {
					player.setLookRight(true);
					player.setSpeed(0.15f,0.085f);
					EntityParticle temp;
					for(int i=0;i<5;i++) {
							temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+225);
							temp.setData(EntityDataUnit.get("particle"));
							temp.setPos(player.getPos().x+player.getSize().x/2,player.getPos().y-player.getSize().y);
							r.addEntity(temp);
					}
				} else if(player.getContact(EntityDynamic.CONTACT_RIGHT) && Input.isPressed(Input.getBind(Input.BIND_LEFT))) {
					player.setLookRight(false);
					player.setSpeed(-0.15f,0.085f);
					EntityParticle temp;
					for(int i=0;i<5;i++) {
							temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+315);
							temp.setData(EntityDataUnit.get("particle"));
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
					temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+0);
					temp.setData(EntityDataUnit.get("particle"));
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
					temp=new EntityParticle(1.5f,((float)Math.random()*50)-25+180);
					temp.setData(EntityDataUnit.get("particle"));
					temp.setPos(player.getPos().x-player.getSize().x/2,player.getPos().y-player.getSize().y);
					r.addEntity(temp);
				}
			} else player.setSpeed(player.getSpeed().x*0.5f,player.getSpeed().y);
			if(Input.isPressed(Input.getBind(Input.BIND_SHOOT))) {
				float dir=-1;
				if(player.getLookRight())
					dir=1;
				player.shoot(new Vector2f(dir,0));
			}


			/*if(Input.isClicked(BIND_MOUSE)) {
				Vector2f target=Input.getWorldMousePosition();
				player.shoot(target);
			}*/
			
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_1)))
				player.useSpell(0);
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_2)))
				player.useSpell(1);
			if(Input.isPressed(Input.getBind(Input.BIND_SPELL_3)))
				player.useSpell(2);
			
			if(Input.isPushed(Input.getBind(Input.BIND_MENU)))
				Main.setGameState(Main.STATE_MAIN_MENU);
		}
	}
}
