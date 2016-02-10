/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Audio;

import static Audio.Vorbis.readVorbis;
import java.nio.ByteBuffer;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.AL10.alGenBuffers;
import static org.lwjgl.openal.ALUtil.checkALError;
import org.lwjgl.stb.STBVorbisInfo;

public class SoundData {

	private int m_buffer=-1;
	private float m_duration=0;
	
	private String m_path="";
	
	public SoundData(String path) {
		m_path=path;
		create();
	}
	public int getBuffer() {
		return m_buffer;
	}
	private void create() {
		STBVorbisInfo info=STBVorbisInfo.malloc();
		ByteBuffer pcm = readVorbis(m_path, 1*1024, info);
		// generate buffers and sources
		m_buffer = alGenBuffers();
		checkALError();

		//copy to buffer
		alBufferData(m_buffer, AL_FORMAT_MONO16, pcm, info.sample_rate());
		checkALError();
		
		info.free();
	}
	public void free() {
		alDeleteBuffers(m_buffer);
        checkALError();
	}
}
