/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Audio;

import static Audio.Buffer.ioResourceToByteBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_close;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_get_info;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_get_samples_short_interleaved;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_open_memory;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_stream_length_in_samples;
import org.lwjgl.stb.STBVorbisInfo;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Vorbis {
    static ByteBuffer readVorbis(String resource, int bufferSize, STBVorbisInfo info){
        ByteBuffer vorbis;
        try {
            vorbis = ioResourceToByteBuffer(resource, bufferSize);
        }catch (IOException e) {
			throw new RuntimeException(e);
        }
        IntBuffer error = BufferUtils.createIntBuffer(1);
        long decoder = stb_vorbis_open_memory(vorbis, error, null);
        if (decoder == NULL)
            throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
        stb_vorbis_get_info(decoder, info);
        int channels = info.channels();
        int lengthSamples = stb_vorbis_stream_length_in_samples(decoder);
        ByteBuffer pcm = BufferUtils.createByteBuffer(lengthSamples*2);
        stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm, lengthSamples);
        stb_vorbis_close(decoder);
        return pcm;
    }
}
