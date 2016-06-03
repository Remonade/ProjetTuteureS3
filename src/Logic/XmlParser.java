/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlParser {

	public static void loadNode(Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				if (n.getNodeName().equals("realm")) {
					XmlParserRealm.loadRealm(n);
				}
				if (n.getNodeName().equals("entityData")) {
					XmlParserEntityData.loadEntityDataGlobal(n);
				}
			} else {
				System.out.println("no data loaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadFile(String path) {
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			Node root = doc.getDocumentElement();

			loadNode(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
