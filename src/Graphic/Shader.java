package Graphic;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

public class Shader {
    public Shader() {
        GL.createCapabilities(); // on a besoin de ça Oo pour des paramètres et tout.
        m_programID=glCreateProgram(); // génère un ID de programme
        
        CharSequence sourceVertex=
                "#version 150 core\n" +
				"\n" +
				"in vec3 in_Vertex;\n" +
				"in vec2 in_TexCoord0;\n" +
				"\n" +
				"uniform mat4 projection;\n" +
				"uniform mat4 modelview;\n" +
				"\n" +
				"out vec2 coordTexture;\n" +
				"\n" +
				"void main()\n" +
				"{\n" +
				"    gl_Position = projection * modelview * vec4(in_Vertex, 1.0);\n" +
				"    coordTexture = in_TexCoord0;\n" +
				"}";
        CharSequence sourceFragment=
                "#version 150 core\n" +
				"\n" +
				"in vec2 coordTexture;\n" +
				"\n" +
				"uniform sampler2D texture;\n" +
				"\n" +
				"out vec4 out_Color;\n" +
				"\n" +
				"void main()\n" +
				"{\n" +
				"    vec4 tex=texture2D(texture, coordTexture);\n" +
				"    if(tex.w<0.5)\n" +
				"        discard;\n" +
				"    out_Color = tex;\n" +
				"}";
        // vertex shader
        m_vertex=glCreateShader(GL_VERTEX_SHADER); // génère un id de Vertex Shader
        glShaderSource(m_vertex,sourceVertex); // indique le code du shader
        glCompileShader(m_vertex); // compile le shader
        glAttachShader(m_programID,m_vertex); // lie le shader au programme
        
        // geometry shader
        //m_geometry=glCreateShader(GL_GEOMETRY_SHADER);  // génère un id de Geometry Shader
        //glShaderSource(m_geometry,sourceGeometry);
        //glCompileShader(m_geometry);
        //glAttachShader(m_programID,m_geometry);
        
        // fragment shader
        m_fragment=glCreateShader(GL_FRAGMENT_SHADER);  // génère un id de Fragment Shader
        glShaderSource(m_fragment,sourceFragment);
        glCompileShader(m_fragment);
        glAttachShader(m_programID,m_fragment);
        
        glLinkProgram(m_programID);
        glValidateProgram(m_programID);
        
        if (glGetProgrami(m_programID, GL_LINK_STATUS) != GL_TRUE)
            throw new RuntimeException(glGetProgramInfoLog(m_programID));
    }
    public void destroy() {
        glDeleteShader(m_vertex);
        //glDeleteShader(m_geometry);
        glDeleteShader(m_fragment);
        glDeleteProgram(m_programID);
    }
    public int getProgramID() {
        return m_programID;
    }
    // Correspond à l'ID du programme du Shader dans la VRAM
    private final int m_programID;
    
    // Correspond à l'ID du code compilé du VertexShader dans la VRAM
    // Ce code positionne les pixels dans la scène 3D
    private final int m_vertex;
    
    // Correspond à l'ID du code compilé du GeometryShader dans la VRAM
    // Ce code permet de générer plus de pixels
    // Ce code est utilisé uniquement pas les particules
    //private final int m_geometry;
    
    // Correspond à l'ID du code compilé du FragmentShader dans la VRAM
    // Ce code définit la couleur des pixels
    private final int m_fragment;
}
