
package Logic.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {
	private Player m_player;
	private String m_name;
	public Save(File file) throws Exception {
		loadFile(file);
	}
		
    public void saveFile(File file) throws FileNotFoundException, IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(m_player);
            output.writeObject(m_name);
        }
    }

    public void loadFile(File file) throws Exception {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
        m_player = (Player) input.readObject();
        m_name = (String) input.readObject();
    }
	/**
	 * Charge les fichiers de sauvegarde dans le répertoire data/save.
	 * Seuls les fichiers avec l'extension .otsu sont considérés.
	 */
	public static void loadSaveFiles() {
		File folder = new File("data/save");
		File[] listOfFiles = folder.listFiles();
		for (File f:listOfFiles) {
			if (f.isFile()) {
				if(f.getName().contains(".otsu"))
					try {
						Save s=new Save(f);
						saveList.add(s);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		}
	}
	
	public static ArrayList<Save> saveList=new ArrayList<>();
}
