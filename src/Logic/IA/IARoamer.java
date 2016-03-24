/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.IA;

import Logic.Entity;
import Logic.EntityUnit;
import Maths.Vector2f;

public class IARoamer extends IA{


	public IARoamer() {
		super();
	}
	public IARoamer(int maxAPM) {
		super(maxAPM);
	}
	@Override
	public IA copy() {
		return new IARoamer();
	}
	@Override
	public IA copy(int APM) {
		return new IARoamer(APM);
	}
	
	@Override
	public void behavior(EntityUnit u, EntityUnit target) {
		if(target!=null)
			m_behavior=1; // fight
		else
			m_behavior=0; // just roam
	}
	@Override
	public void move(EntityUnit u, EntityUnit target) {
		u.resetInput();
		int mode=u.getCustomValue();
		Vector2f mod=new Vector2f(0,0);
		if(u.getContact(mode)) {
			switch(mode) {
				case Entity.CONTACT_UP: // in case of upward movement
					if(u.getContact(Entity.CONTACT_LEFT) && !u.getContact(Entity.CONTACT_RIGHT)) {
						u.setCustomValue(Entity.CONTACT_RIGHT);
					} else if(u.getContact(Entity.CONTACT_RIGHT) && !u.getContact(Entity.CONTACT_LEFT)) {
						u.setCustomValue(Entity.CONTACT_LEFT);
					} else {
						u.setCustomValue(Entity.CONTACT_DOWN);
					}
					break;
				case Entity.CONTACT_DOWN: // in case of upward movement
					if(u.getContact(Entity.CONTACT_LEFT) && !u.getContact(Entity.CONTACT_RIGHT)) {
						u.setCustomValue(Entity.CONTACT_RIGHT);
					} else if(u.getContact(Entity.CONTACT_RIGHT) && !u.getContact(Entity.CONTACT_LEFT)) {
						u.setCustomValue(Entity.CONTACT_LEFT);
					} else {
						u.setCustomValue(Entity.CONTACT_UP);
					}
					break;
				case Entity.CONTACT_LEFT: // in case of upward movement
					if(u.getContact(Entity.CONTACT_DOWN) && !u.getContact(Entity.CONTACT_UP)) {
						u.setCustomValue(Entity.CONTACT_UP);
					} else if(u.getContact(Entity.CONTACT_UP) && !u.getContact(Entity.CONTACT_DOWN)) {
						u.setCustomValue(Entity.CONTACT_DOWN);
					} else {
						u.setCustomValue(Entity.CONTACT_RIGHT);
					}
					break;
				case Entity.CONTACT_RIGHT: // in case of upward movement
					if(u.getContact(Entity.CONTACT_DOWN) && !u.getContact(Entity.CONTACT_UP)) {
						u.setCustomValue(Entity.CONTACT_UP);
					} else if(u.getContact(Entity.CONTACT_UP) && !u.getContact(Entity.CONTACT_DOWN)) {
						u.setCustomValue(Entity.CONTACT_DOWN);
					} else {
						u.setCustomValue(Entity.CONTACT_LEFT);
					}
					break;
			}
		}
		mode=u.getCustomValue();
		switch(mode) {
			case Entity.CONTACT_UP: // in case of upward movement
				mod.y=0.05f;
				break;
			case Entity.CONTACT_DOWN: // in case of upward movement
				mod.y=-0.05f;
				break;
			case Entity.CONTACT_LEFT: // in case of upward movement
				mod.x=-0.05f;
				break;
			case Entity.CONTACT_RIGHT: // in case of upward movement
				mod.x=0.05f;
				break;
		}
		mod.y-=Physic.PhysicMain.GRAVITY;
		u.setSpeed(mod);
	}
}
