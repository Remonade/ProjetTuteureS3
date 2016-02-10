/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Audio;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALUtil.checkALError;

public class Channel {

	private int m_source=-1;
	private double m_birth=-1;
	
	public Channel() {
		m_source=alGenSources();
		checkALError();
	}
	public void play(SoundData s,boolean loop) {
		if(s!=null) {
			stop();

			m_birth=Logic.Logic.CURRENT_TIME;

			alSourcei(m_source, AL_BUFFER, s.getBuffer());
			checkALError();

			if(loop) {
				alSourcei(m_source, AL_LOOPING, AL_TRUE);
				checkALError();
			}

			alSourcePlay(m_source);
			checkALError();
		}
	}
	public void continuePlaying() {
		alSourcePlay(m_source);
		checkALError();
	}
	public void pause() {
		alSourcePause(m_source);
		checkALError();
	}
	public void stop() {
		m_birth=-1;
		
		alSourceStop(m_source);
		checkALError();
	}
	public void rewind() {
		m_birth=Logic.Logic.CURRENT_TIME;
		
		alSourceRewind(m_source);
		checkALError();
	}
	public void setVolume(float volume) {
		alSourcef(m_source, AL_GAIN, volume);
	}
	public double getBirth() {
		return m_birth;
	}
	public void free() {
		alDeleteSources(m_source);
		checkALError();
	}
}
