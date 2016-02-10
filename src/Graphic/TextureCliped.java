/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import Maths.Vector2f;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class TextureCliped extends Texture {
	private int m_countX=1,m_countY=1;
	
	public TextureCliped(int width, int height, ByteBuffer data) {
		super(width, height, data);
	}
	public void setFrameCountX(int x) {
		m_countX=x;
	}
	public void setFrameCountY(int y) {
		m_countY=y;
	}
	public void setFrameCount(int x, int y) {
		m_countX=x;
		m_countY=y;
	}
	public int getSpriteCount() {
		return m_countX*m_countY;
	}
	public float getSpriteWidth() {
		return 1.f/(float)m_countX;
	}
	public float getSpriteHeight() {
		return 1.f/(float)m_countY;
	}
	public int getSpritePositionX(int sprite) {
		return sprite%m_countX;
	}
	public int getSpritePositionY(int sprite) {
		int i=m_countY-1;
		while(sprite>=m_countX) {
			sprite-=m_countX;
			i--;
		}
		return i;
	}
	@Override
	public Vector2f getCoordinate(int corner, int clip) {
		Vector2f pos=new Vector2f();
		corner=corner%4;
		clip=clip%getSpriteCount();
		float dir=GraphicMain.DIRECTION;
		pos.x=((float)getSpritePositionX(clip))*getSpriteWidth();
		if(dir<0)
			pos.x+=getSpriteWidth();
		pos.y=((float)getSpritePositionY(clip))*getSpriteHeight();
		if(corner==1 || corner==2)
			pos.x+=getSpriteWidth()*dir;
		if(corner==2 || corner==3)
			pos.y+=getSpriteHeight();
		return pos;
	}
	public static TextureCliped loadTextureCliped(String path) {
        /* Prepare image buffers */
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        /* Load image */
        stbi_set_flip_vertically_on_load(1);
        ByteBuffer image = stbi_load("data/graphic/"+path, w, h, comp, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load a texture file!"
                    + System.lineSeparator() + stbi_failure_reason() + path);
        }

        /* Get width and height of image */
        int width = w.get();
        int height = h.get();

        return new TextureCliped(width, height, image);
    }
}
