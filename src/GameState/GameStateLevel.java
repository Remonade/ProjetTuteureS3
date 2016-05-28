/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import GUI.GUI;
import GUI.GUIBossBar;
import GUI.GUIBuffBar;
import GUI.GUIPlayerBar;
import GUI.GUISpellBar;
import static Graphic.GraphicMain.drawString;
import static Graphic.GraphicMain.window;
import Logic.EntityUnit;
import Logic.EntityWayPoint;
import Logic.Logic;
import Logic.Realm;
import Maths.Vector2f;
import Maths.Vector4f;
import Physic.PhysicMain;
import Tools.Input;
import Tests.Main;
import static Tests.Main.STATE_LEVEL;
import static Tests.Main.STATE_TRANSITION;
import static Tests.Main.getGameState;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;

public class GameStateLevel extends GameState {
	public GameStateLevel() {
		super();
		GUIBossBar b=new GUIBossBar();
		b.setPos(0.5f,0.95f);
		b.setSize(0.30f,0.02f);
		m_GUI.add(b);
		
		GUIBuffBar g = new GUIBuffBar();
		g.setPos(1f,1f);
		g.setSize(20,20);
		m_GUI.add(g);
		
		GUIPlayerBar p = new GUIPlayerBar();
		p.setPos(0f,1f);
		p.setSize(0.06f,0.015f);
		m_GUI.add(p);
	}
	@Override
	public void onEnter() {
		if(Realm.getActiveRealm()==null) {
			if(Realm.getRealmCount()>0)
				this.setRealm(0);
			for(GUI g:m_GUI) {
				if(g instanceof GUIBossBar)
					((GUIBossBar)g).setBoss(Realm.getActiveRealm().getBoss());
			}
		} else Audio.Audio.continueMusic();
		glfwSetInputMode(window,GLFW_CURSOR,GLFW_CURSOR_DISABLED);
	}
	@Override
	public void onLeave() {
		Audio.Audio.pauseMusic();
		int GLFW_CURSOR_ENABLED = 0;
		glfwSetInputMode(window,GLFW_CURSOR,GLFW_CURSOR_NORMAL);
	}
	public void changeRealm(int realm) {
		System.out.println("GameStateLevel changing realm to "+realm);
		((GameStateTransition)getGameState(STATE_TRANSITION)).setNextRealm(realm);
		System.out.println("GameStateLevel change game state.");
		Main.setGameState(STATE_LEVEL);
	}
	public void setRealm(int realm) {
		Realm.changeRealm(realm);
		for(GUI g:m_GUI) {
			if(g instanceof GUIBossBar)
				((GUIBossBar)g).setBoss(Realm.getActiveRealm().getBoss());
		}
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
					continue;
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
				
				if(Logic.getPlayer()!=null) {
					//GUIPlayerBar.setPlayer(Logic.getPlayer());
					//GUIPlayerBar.draw();

					GUISpellBar.setPlayer(Logic.getPlayer());
					GUISpellBar.draw();

					//GUIBuffBar.setPlayer(Logic.getPlayer());
					//GUIBuffBar.draw();
				}
			}
		}
	}
	@Override
	public void inputHandler() {
		super.inputHandler();
		Realm r=getActiveRealm();
		if(r!=null) {
			EntityUnit player=Logic.getPlayer();
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
