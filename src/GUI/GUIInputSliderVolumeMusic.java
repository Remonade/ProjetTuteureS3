/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import static Tests.Input.getMouseX;

public class GUIInputSliderVolumeMusic  extends GUIInputSlider {
	
	public GUIInputSliderVolumeMusic() {
		super();
		m_value=Audio.Audio.getMusicVolume();
		m_minRange=0;
		m_maxRange=100;
	}
	@Override
	public void inputHandler() {
		if(m_focus) {
			float mouseX=(float) getMouseX();
			if(mouseX<getPos().x-getSize().x) {
				m_value=0;
			} else if(mouseX>getPos().x+getSize().x) {
				m_value=1;
			} else {
				float dist=Math.abs(mouseX-(getPos().x-getSize().x));
				m_value=dist/(getSize().x*2);
			}
			Audio.Audio.setMusicVolume(m_value);
		}
	}
}
