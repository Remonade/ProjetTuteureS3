package Graphic;

import Maths.Vector2f;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private final int id;    
    private final int width;
    private final int height;

    public Texture(int width, int height, ByteBuffer data) {
        id = glGenTextures();
        this.width = width;
        this.height = height;

        bind();

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    }

    public void bind() {
        glEnable(GL_TEXTURE_2D);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D,0);
        glDisable(GL_TEXTURE_2D);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void destroy() {
        glDeleteTextures(id);
    }
	public Vector2f getCoordinate(int corner, int clip) {
		Vector2f pos=new Vector2f();
		corner=corner%4;
		pos.x=0;
		pos.y=0;
		if(corner==1 || corner==2)
			pos.x+=1;
		if(corner==2 || corner==3)
			pos.y+=1;
		return pos;
	}
    
    public static Texture loadTexture(String path) {
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

        return new Texture(width, height, image);
    }

}