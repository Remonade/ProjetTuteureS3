/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.IA;

import Logic.Entity;
import Logic.EntityUnit;

public class IATutorial  extends IA{
	
	public IATutorial() {
		super();
	}
	public IATutorial(int maxAPM) {
		super(maxAPM);
	}
	@Override
	public IA copy() {
		return new IATutorial();
	}
	@Override
	public IA copy(int APM) {
		return new IATutorial(APM);
	}
	@Override
	public void rotate(EntityUnit u, EntityUnit target) {
	}
	@Override
	public void move(EntityUnit u, EntityUnit target) {
		u.resetInput();
		if(u.getCustomValue()==0) {
			if(u.getContact(Entity.CONTACT_RIGHT)) {
				if(u.getPos().y<2) {
					u.setInput(EntityUnit.INPUT_JUMP,true);
					u.setInput(EntityUnit.INPUT_LEFT,true);
					u.setCustomValue(1);
				}
			} else u.setInput(EntityUnit.INPUT_RIGHT,true);
		} else {
			if(u.getContact(Entity.CONTACT_LEFT)) {
				if(u.getPos().y<2) {
					u.setInput(EntityUnit.INPUT_JUMP,true);
					u.setInput(EntityUnit.INPUT_RIGHT,true);
					u.setCustomValue(0);
				}
			} else u.setInput(EntityUnit.INPUT_LEFT,true);
		}
	}
	
	@Override
	public void attack(EntityUnit u, EntityUnit target) {
		u.talk("WallJump: Maintiens la touche \"Saut\" et alternes entre gauche et droite.");
	}
}
