package Graphic;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
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

        this.bind();

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
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
        glDisable(GL_TEXTURE);
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
    
    
    public static Texture loadTexture(String path) {
        /* Prepare image buffers */
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        /* Load image */
        stbi_set_flip_vertically_on_load(1);
        ByteBuffer image = stbi_load(path, w, h, comp, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load a texture file!"
                    + System.lineSeparator() + stbi_failure_reason());
        }

        /* Get width and height of image */
        int width = w.get();
        int height = h.get();

        return new Texture(width, height, image);
    }
}