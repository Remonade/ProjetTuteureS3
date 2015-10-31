package Graphic;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

public class Shader {
	public Shader() {
		GL.createCapabilities(); // on a besoin de ça Oo pour des paramètres et tout.
		m_programID=glCreateProgram(); // génère un ID de programme
		
		CharSequence sourceVertex=
			"// Version du GLSL\n" +
			"#version 150 core\n" +
			"// Entrées\n" +
			"in vec2 in_Vertex;\n" +
			"in vec3 in_Color;\n" +
			"// Sortie\n" +
			"out vec3 color;\n" +
			"// Fonction main\n" +
			"void main()\n" +
			"{\n" +
			"    // Position finale du vertex\n" +
			"    gl_Position = vec4(in_Vertex, 0.0, 1.0);\n" +
			"    // Envoi de la couleur au Fragment Shader\n" +
			"    color = in_Color;\n" +
			"}";
		CharSequence sourceFragment=
			"// Version du GLSL\n" +
			"#version 150 core\n" +
			"// Entrée\n" +
			"in vec3 color;\n" +
			"// Sortie \n" +
			"out vec4 out_Color;\n" +
			"// Fonction main\n" +
			"void main()\n" +
			"{\n" +
			"    // Couleur finale du pixel\n" +
			"    out_Color = vec4(color, 1.0);\n" +
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
