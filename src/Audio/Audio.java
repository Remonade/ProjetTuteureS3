
package Audio;

import java.util.Map.Entry;
import java.util.TreeMap;
import org.lwjgl.openal.ALContext;
import org.lwjgl.openal.ALDevice;

public class Audio {

	private static ALDevice device;
	private static ALContext context;
	private static boolean VALIDE=false;
	
	private static float generalVolume=0.75f;
	
	// sound
	private static Channel[] soundChannels;
	private static float soundVolume=0.75f;
	
	
	// music
	private static final int MUSIC_STOPED=0;
	private static final int MUSIC_PLAYING=1;
	private static final int MUSIC_PAUSED=2;
	
	private static int CHANNEL_COUNT=16;
	
	private static int musicState=MUSIC_STOPED;
	private static Channel musicChannel;
	private static float musicVolume=1f;
	private static SoundData currentMusic=null;
	
	
	public static TreeMap<String,SoundData> soundList=new TreeMap<>();
	
	public static void init() {
		if(!VALIDE) {

			device = ALDevice.create(null);
			if (device==null)
				throw new IllegalStateException("Failed to open the default device.");
			context = ALContext.create(device);
			
			soundChannels=new Channel[CHANNEL_COUNT];
			for(int i=0;i<CHANNEL_COUNT;i++) {
				soundChannels[i]=new Channel();
				soundChannels[i].setVolume(generalVolume*soundVolume);
			}
				
			musicChannel=new Channel();
			musicChannel.setVolume(generalVolume*musicVolume);
			VALIDE=true;
		}
	}
	public static SoundData loadSoundData(String path) {
		SoundData s=soundList.get(path);
		if(s==null) {
			s=new SoundData("data/audio/"+path);
			soundList.put(path,s);
		}
		return s;
	}
	public static void playSound(String path) {
		if(!path.equals("")) {
			SoundData s=loadSoundData(path);
			playSound(s);
		}
	}
	public static void playSound(SoundData s) {
		if(s!=null) {
			Channel c=getFreeChannel();
			if(c==null)
				c=getOldestChannel();
			c.play(s,false);
		}
	}
	public static void playMusic(String path,boolean loop) {
		SoundData s=loadSoundData(path);
		playMusic(s,loop);
	}
	public static void playMusic(SoundData s,boolean loop) {
		if(s!=null) {
			if(s!=currentMusic) {
				currentMusic=s;
				musicChannel.play(s,loop);
			musicState=MUSIC_PLAYING;
			} else if(musicState==MUSIC_PAUSED) {
				continueMusic();
			}
		} else stopMusic();
	}
	public static void pauseMusic() {
		musicChannel.pause();
		musicState=MUSIC_PAUSED;
	}
	public static void continueMusic() {
		if(currentMusic!=null) {
			if(musicState==MUSIC_PAUSED) {
				musicChannel.continuePlaying();
				musicState=MUSIC_PLAYING;
			}
		}
	}
	public static void stopMusic() {
		if(musicState!=MUSIC_STOPED) {
			musicChannel.stop();
			musicState=MUSIC_STOPED;
		}
	}
	public static void setGeneralVolume(float volume) {
		generalVolume=volume;
		for(Channel c:soundChannels)
			c.setVolume(generalVolume*soundVolume);
		musicChannel.setVolume(generalVolume*musicVolume);
	}
	public static float getGeneralVolume() {
		return generalVolume;
	}
	public static void setSoundVolume(float volume) {
		soundVolume=volume;
		for(Channel c:soundChannels)
			c.setVolume(generalVolume*soundVolume);
	}
	public static float getSoundVolume() {
		return generalVolume;
	}
	public static void setMusicVolume(float volume) {
		musicVolume=volume;
		musicChannel.setVolume(generalVolume*musicVolume);
	}
	public static float getMusicVolume() {
		return generalVolume;
	}
	public static void clear() {
		if(VALIDE) {
			// free all channels
			for(Channel c:soundChannels)
				c.free();
			musicChannel.free();

			// free all sound data buffers
			for(Entry<String,SoundData> s:soundList.entrySet())
				s.getValue().free();

			// destroy OpenAL context
			context.destroy();
			device.destroy();
			VALIDE=false;
		}
	}
	public static Channel getFreeChannel() {
		for(Channel c:soundChannels)
			if(c.getBirth()<0)
				return c;
		return null;
	}
	public static Channel getOldestChannel() {
		Channel out=null;
		double birth=-1;
		for(Channel c:soundChannels)
			if(out==null || c.getBirth()<birth) {
				out=c;
				birth=c.getBirth();
			}
		return out;
	}
}
