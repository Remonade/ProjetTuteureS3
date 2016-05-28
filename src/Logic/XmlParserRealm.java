/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Logic.Data.EntityData;
import Logic.Data.EntityDataUnit;
import Logic.IA.IA;
import Maths.Vector2f;
import Maths.Vector4f;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParserRealm {

	public static Entity loadGround(Element currentElement) {
		Entity out = null;
		try {
			String type, position, collision;
			type = currentElement.getAttribute("type");
			position = currentElement.getAttribute("position");
			collision = currentElement.getAttribute("collision");

			System.out.println("\tType : " + type);
			System.out.println("\tPosition : " + position);
			System.out.println("\tCollision : " + collision);

			// create entity
			if (!type.equals("")) {
				out = new Entity();

				// set data
				out.setData(EntityData.get(type));

				// set position
				if (!position.equals("")) {
					String[] split = position.split(" ");
					if (split.length > 1) {
						Vector2f pos = new Vector2f();
						pos.x = Float.parseFloat(split[0]);
						pos.y = Float.parseFloat(split[1]);
						out.setPos(pos);
					}
				}
				// set collusion
				if (!collision.equals("")) {
					if (collision.equals("off") || collision.equals("false")) {
						out.setCollide(false);
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return out;
	}

	public static Entity loadGround(Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("new " + n.getNodeName());
				return loadGround((Element) n);
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static ArrayList<Entity> loadGround(NodeList nl) {
		ArrayList<Entity> out = new ArrayList<>();
		try {
			System.out.println("Try to load ground list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				Entity temp = loadGround(nl.item(i));
				if (temp != null) {
					out.add(temp);
				}
			}
		} catch (Exception e) {
		}
		return out;
	}

	public static EntityWayPoint loadWaypoint(Element currentElement) {
		EntityWayPoint out = null;
		try {
			String type, position, targetRealm, targetWaypoint, targetPosition;
			type = currentElement.getAttribute("type");
			position = currentElement.getAttribute("position");
			targetRealm = currentElement.getAttribute("targetRealm");
			targetWaypoint = currentElement.getAttribute("targetWaypoint");
			targetPosition = currentElement.getAttribute("targetPosition");

			System.out.println("\tType : " + type);
			System.out.println("\tPosition : " + position);
			System.out.println("\tTargeted Realm : " + targetRealm);
			System.out.println("\tTargeted Waypoint : " + targetWaypoint);
			System.out.println("\tTargeted Position : " + targetPosition);

			// create entity
			if (!type.equals("")) {
				out = EntityWayPoint.create();

				// set data
				out.setData(EntityData.get(type));

				// set position
				if (!position.equals("")) {
					String[] split = position.split(" ");
					if (split.length > 1) {
						Vector2f pos = new Vector2f();
						pos.x = Float.parseFloat(split[0]);
						pos.y = Float.parseFloat(split[1]);
						out.setPos(pos);
					}
				}

				// set target realm
				if (!targetRealm.equals("")) {
					int realm = Integer.parseInt(targetRealm);
					out.setDirection(realm);
				}

				// set target waypoint
				if (!targetWaypoint.equals("")) {
					int target = Integer.parseInt(targetWaypoint);
					out.setTarget(target);
				}

				// set target position
				if (!targetPosition.equals("")) {
					String[] split = targetPosition.split(" ");
					if (split.length > 1) {
						Vector2f pos = new Vector2f();
						pos.x = Float.parseFloat(split[0]);
						pos.y = Float.parseFloat(split[1]);
						out.setOut(pos);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static Entity loadWaypoint(Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("new " + n.getNodeName());
				return loadWaypoint((Element) n);
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static ArrayList<Entity> loadWaypoint(NodeList nl) {
		ArrayList<Entity> out = new ArrayList<>();
		try {
			System.out.println("Try to load waypoint list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				Entity temp = loadWaypoint(nl.item(i));
				if (temp != null) {
					out.add(temp);
				}
			}
		} catch (Exception e) {
		}
		return out;
	}

	public static Entity loadUnit(Element currentElement) {
		EntityUnit out = null;
		try {
			String type, position, team, ia;
			type = currentElement.getAttribute("type");
			position = currentElement.getAttribute("position");
			team = currentElement.getAttribute("team");
			ia = currentElement.getAttribute("ia");

			System.out.println("\tType : " + type);
			System.out.println("\tPosition : " + position);
			System.out.println("\tTeam : " + team);
			System.out.println("\tIA : " + ia);

			// create entity
			if (!type.equals("")) {
				out = new EntityUnit();

				// set data
				out.setData(EntityDataUnit.get(type));

				// set position
				if (!position.equals("")) {
					String[] split = position.split(" ");
					if (split.length > 1) {
						Vector2f pos = new Vector2f();
						pos.x = Float.parseFloat(split[0]);
						pos.y = Float.parseFloat(split[1]);
						out.setPos(pos);
					}
				}

				// set team
				if (!team.equals("")) {
					out.setTeam(Integer.parseInt(team));
				}

				// set ia
				if (!ia.equals("")) {
					String[] split = ia.split(" ");
					if (split.length > 1) {
						out.setIA(IA.getIA_TEMPLATE(split[0], Integer.parseInt(split[1])));
					} else {
						out.setIA(IA.getIA_TEMPLATE(ia));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static Entity loadUnit(Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("new " + n.getNodeName());
				return loadUnit((Element) n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Entity> loadUnit(NodeList nl) {
		ArrayList<Entity> out = new ArrayList<>();
		try {
			System.out.println("Try to load unit list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				Entity temp = loadUnit(nl.item(i));
				if (temp != null) {
					out.add(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static Realm loadRealm(Element currentElement) {
		Realm out = null;
		try {
			String name, bg, music, camera;
			name = ((Element) currentElement).getAttribute("name");
			bg = ((Element) currentElement).getAttribute("background");
			music = ((Element) currentElement).getAttribute("music");
			camera = ((Element) currentElement).getAttribute("cameraBound");

			System.out.println("\tName: " + name);
			System.out.println("\tBackground: " + bg);
			System.out.println("\tMusic: " + music);
			System.out.println("\tCamera: " + camera);

			// create realm
			if (!name.equals("")) {
				int realmID = Realm.createRealm(name);
				if (realmID > -1) {
					out = Realm.getRealm(realmID);

					// set background
					if (!bg.equals("")) {
						out.setBackground(Graphic.GraphicMain.getModel(bg));
					}

					// set music
					if (!music.equals("")) {
						out.setMusic(music);
					}

					// set camera bound
					if (!camera.equals("")) {
						Vector4f cameraBound = new Vector4f();
						cameraBound.x = Float.parseFloat(camera.split(" ")[0]);
						cameraBound.y = Float.parseFloat(camera.split(" ")[1]);
						cameraBound.z = Float.parseFloat(camera.split(" ")[2]);
						cameraBound.w = Float.parseFloat(camera.split(" ")[3]);
						out.setCameraBound(cameraBound);
					}
				} else {
					System.out.println("invalid realm ID: " + realmID);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static Realm loadRealm(Node n) {
		Realm out = null;
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("new " + n.getNodeName());
				out = loadRealm((Element) n);
				if (out != null) {
					NodeList realmContent = n.getChildNodes();
					for (int i = 0; i < realmContent.getLength(); i++) {
						Node currentNode = realmContent.item(i);
						if (currentNode.getNodeName().equals("ground")) {
							out.addEntity(loadGround(currentNode.getChildNodes()));
						}
						if (currentNode.getNodeName().equals("waypoint")) {
							out.addEntity(loadWaypoint(currentNode.getChildNodes()));
						}
						if (currentNode.getNodeName().equals("unit")) {
							out.addEntity(loadUnit(currentNode.getChildNodes()));
						}
						if (currentNode.getNodeName().equals("boss")) {
							for (Entity boss : loadUnit(currentNode.getChildNodes())) {
								out.addEntity(boss);
								out.setBoss((EntityUnit) boss);
							}
						}
					}
				} else {
					System.out.println("ERROR");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static void loadRealm(NodeList nl) {
		try {
			System.out.println("Try to load realm list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				loadRealm(nl.item(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
