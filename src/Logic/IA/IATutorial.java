/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.IA;

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
	public void move(EntityUnit u, EntityUnit target) {
		//u.jump();
	}
	
	@Override
	public void attack(EntityUnit u, EntityUnit target) {
		u.talk("WallJump: Maintiens la touche \"Saut\" et alternes entre gauche et droite.");
	}
}
