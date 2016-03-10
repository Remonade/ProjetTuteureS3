/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import GUI.GUITexture;
import static Graphic.GraphicMain.WIDTH;
import Logic.Logic;
import Logic.Realm;
import static Tests.Main.setGameState;

public class GameStateTransition extends GameState {
	
	private float m_time;
	private int m_next;
	private int m_prev;
	private int m_realm=0;
	private boolean m_in;
	
	// graphic part
	private float m_duration=0.5f;
	private float m_posLeft=0;
	private float m_posRight=0;
	private float m_ratio;
	
	public GameStateTransition() {
		super();
		
		GUITexture gui;
		gui=new GUITexture("loadingScreenLeft");
		gui.setPos(0,0.5f);
		gui.setSize(1,0.5f);
		m_GUI.add(gui);
		
		gui=new GUITexture("loadingScreenRight");
		gui.setPos(0,0.5f);
		gui.setSize(1,0.5f);
		m_GUI.add(gui);
		
		m_ratio=1/m_duration;
	}
	public void setPrev(int prev) {
		m_prev=prev;
	}
	public void setNext(int next) {
		m_next=next;
	}
	public void setNextRealm(int realm) {
		m_realm=realm;
	}
	@Override
	public void onEnter() {
		m_posLeft=-WIDTH*0.5f;
		m_posRight=WIDTH*1.5f;
		//Audio.Audio.playSound("");
		m_time=0;
		m_in=true;
	}
	
	@Override
	public void onLeave() {
		Audio.Audio.playSound("");
	}
	
	@Override
	public void execute() {
		if(m_in) {
			m_time+=Logic.DELTA_TIME;
			if(m_time>m_duration) {
				m_in=false;
				if(m_realm>-1) {
					Realm.changeRealm(m_realm);
					m_realm=-1;
				}
			}
		} else {
			m_time-=Logic.DELTA_TIME;
			if(m_time<0)
				setGameState(m_next);
		}
		m_GUI.get(0).setPos(m_posLeft+WIDTH*m_time*m_ratio,0.5f);
		m_GUI.get(1).setPos(m_posRight-WIDTH*m_time*m_ratio,0.5f);
	}
	
	@Override
	public void drawCore() {
		GameState gsp=Main.getGameState(m_prev);
		GameState gsn=Main.getGameState(m_next);
		if(m_in) {
			if(gsp!=this)
				gsp.draw();
		} else {
			if(gsn!=this)
				gsn.draw();
		}
	}
	
}
