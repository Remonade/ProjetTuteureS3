
package Tools;

import java.util.HashMap;

public class Lang {
	public static String LANG_CONFIG = init();
	
	private static HashMap<String,HashMap<String,String>> stringDataMap;
	
	private static String init () {
		stringDataMap = new HashMap<>();
		addString("HUD_Label_MainMenu","FR","Menu principal");
		addString("HUD_Button_Play","FR","Jouer");
		addString("HUD_Help_Play","FR","Renvoit à l'écran de jeu.");
		addString("HUD_Button_Settings","FR","Options");
		addString("HUD_Help_Settings","FR","Menu permettant d'ajuster l'expérience de jeu.");
		addString("HUD_Button_Quit","FR","Quitter");
		addString("HUD_Help_Quit","FR","Ferme le jeu.");
		addString("HUD_Button_Back","FR","Retour");
		addString("HUD_Help_Back","FR","Revoit au menu précédent.");
		addString("key","FR","str");
		
		return "FR";
	}
	
	public static void addString(String stringKey, String lang, String str) {
		lang=lang.toUpperCase();
		if(stringDataMap.containsKey(stringKey)) {
			HashMap<String,String> temp = stringDataMap.get(stringKey);
			if(!temp.containsKey(lang)) // no overwrite allowed
				temp.put(lang, str);
		} else {
			HashMap<String,String> temp = new HashMap<>();
			temp.put(lang, str);
			stringDataMap.put(stringKey,temp);
		}
	}
	
	public static String getString(String stringKey) {
		String out;
		if(stringDataMap.containsKey(stringKey)) {
			HashMap<String,String> temp = stringDataMap.get(stringKey);
			if(temp.containsKey(LANG_CONFIG)) // no overwrite allowed
				out=temp.get(LANG_CONFIG);
			else
				out=stringKey;
		} else out=stringKey;
		return out;
	}
	
	public static String getString(String stringKey, String substitute) {
		String out=getString(stringKey);
		if(out.equals(stringKey))
			out=substitute;
		return out;
	}
}
