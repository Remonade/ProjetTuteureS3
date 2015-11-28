/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class TextureAnimated extends Texture {
	private int m_spriteCount;
	private double m_animDuration;
	
	public TextureAnimated(int width, int height, ByteBuffer data,int spriteCount,double duration) {
		super(width, height, data);
		m_spriteCount=spriteCount;
		m_animDuration=duration;
	}
	
	public int getSpriteCount() {
		return m_spriteCount;
	}
	public float getSpriteWidth() {
		return 1.f/(float)m_spriteCount;
	}
	public double getAnimDuration() {
		return m_animDuration;
	}
	public int getCurrentSprite(double time) {
		if(time<=m_animDuration) {
			double spriteDuration=m_animDuration/m_spriteCount;
			int out=0;
			while((out+1)*spriteDuration<time)
				out++;
			return out;
		} else
			return -1;
	}
	public static Texture loadTextureAnimated(String path,int spriteCount,double duration) {
        /* Prepare image buffers */
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        /* Load image */
        stbi_set_flip_vertically_on_load(1);
        ByteBuffer image = stbi_load(path, w, h, comp, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load a texture file!"
                    + System.lineSeparator() + stbi_failure_reason() + path);
        }

        /* Get width and height of image */
        int width = w.get();
        int height = h.get();

        return new TextureAnimated(width, height, image, spriteCount, duration);
    }

}
