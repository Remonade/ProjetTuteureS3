/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Graphic.GraphicMain;
import Logic.Data.EntityData;
import Logic.Data.EntityDataMissile;
import Logic.Data.EntityDataParticle;
import Logic.Data.EntityDataUnit;
import Maths.Vector2f;
import Maths.Vector4f;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParserEntityData {

	public static void loadEntitySound(EntityData data, Element currentElement) {
		try {
			String key, value;
			key = currentElement.getAttribute("key");
			value = currentElement.getAttribute("value");
			
			System.out.println("\tClé : " + key);
			System.out.println("\tValeur : " + value);
			
			data.setSound(key, value);
		} catch (Exception e) {
		}
	}

	public static void loadEntitySound(EntityData data, Node n) {
		try {
			if (data != null && n.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("new " + n.getNodeName());
				loadEntitySound(data, (Element) n);
			}
		} catch (Exception e) {
		}
	}

	public static void loadEntitySound(EntityData data, NodeList nl) {
		try {
			System.out.println("Try to load sound list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				loadEntitySound(data, nl.item(i));
			}
		} catch (Exception e) {
		}
	}

	public static EntityData loadEntityData(EntityData data, Element currentElement) {
		try {
			String name, bodySize, modelSize, modelName, color;
			name = currentElement.getAttribute("name");
			bodySize = currentElement.getAttribute("bodySize");
			modelSize = currentElement.getAttribute("modelSize");
			modelName = currentElement.getAttribute("modelName");
			color = currentElement.getAttribute("color");

			System.out.println("\tNom : " + name);
			System.out.println("\tTaille corps : " + bodySize);
			System.out.println("\tTaille modèle : " + modelSize);
			System.out.println("\tModèle : " + modelName);
			System.out.println("\tCouleur : " + color);

			// create entity
			if (!name.equals("")) {
				if (data == null) {
					data = EntityData.create(name);
				}

				// set bodySize
				if (!bodySize.equals("")) {
					String[] split = bodySize.split(" ");
					if (split.length > 1) {
						Vector2f size = new Vector2f();
						size.x = Float.parseFloat(split[0]);
						size.y = Float.parseFloat(split[1]);
						data.setBodySize(size);
					}
				}

				// set modelSize
				if (!modelSize.equals("")) {
					String[] split = modelSize.split(" ");
					if (split.length > 1) {
						Vector2f size = new Vector2f();
						size.x = Float.parseFloat(split[0]);
						size.y = Float.parseFloat(split[1]);
						data.setModelSize(size);
					}
				}

				// set model
				if (!modelName.equals("")) {
					data.setModel(GraphicMain.getModel(modelName));
				}

				// set color
				if (!color.equals("")) {
					String[] split = color.split(" ");
					if (split.length > 3) {
						Vector4f colorVect = new Vector4f();
						colorVect.x = Float.parseFloat(split[0]);
						colorVect.y = Float.parseFloat(split[1]);
						colorVect.z = Float.parseFloat(split[2]);
						colorVect.w = Float.parseFloat(split[3]);
						data.setColor(colorVect);
					}
				}
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static EntityData loadEntityData(EntityData data, Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				data = loadEntityData(data, (Element) n);
				loadEntitySound(data, n.getChildNodes());
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static void loadEntityData(NodeList nl) {
		try {
			System.out.println("Try to load block data list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				EntityData temp = loadEntityData(null, nl.item(i));
			}
		} catch (Exception e) {
		}
	}

	public static EntityDataUnit loadEntityDataUnit(EntityDataUnit data, Element currentElement) {
		try {
			String name, maxHealth, regenHealth, maxShield, regenShield, maxEnergy, regenEnergy;
			name = currentElement.getAttribute("name");
			maxHealth = currentElement.getAttribute("maxHealth");
			regenHealth = currentElement.getAttribute("regenHealth");
			maxShield = currentElement.getAttribute("maxShield");
			regenShield = currentElement.getAttribute("regenShield");
			maxEnergy = currentElement.getAttribute("maxEnergy");
			regenEnergy = currentElement.getAttribute("regenEnergy");

			System.out.println("\tNom : " + name);
			System.out.println("\tMax Health : " + maxHealth);
			System.out.println("\tRegen Health : " + regenHealth);
			System.out.println("\tMax Shield : " + maxShield);
			System.out.println("\tRegen Shield : " + regenShield);
			System.out.println("\tMax Energy : " + maxEnergy);
			System.out.println("\tRegen Energy : " + regenEnergy);

			// set bodySize
			if (!name.equals("")) {
				if (data == null) {
					data = EntityDataUnit.create(name);
				}

				// set maxHealth
				if (!maxHealth.equals("")) {
					data.setMaxHealth(Float.parseFloat(maxHealth));
				}

				// set regenHealth
				if (!regenHealth.equals("")) {
					data.setRegenHealth(Float.parseFloat(regenHealth));
				}

				// set maxShield
				if (!maxShield.equals("")) {
					data.setMaxShield(Float.parseFloat(maxShield));
				}

				// set regenShield
				if (!regenShield.equals("")) {
					data.setRegenShield(Float.parseFloat(regenShield));
				}

				// set maxEnergy
				if (!maxEnergy.equals("")) {
					data.setMaxEnergy(Float.parseFloat(maxEnergy));
				}

				// set regenEnergy
				if (!regenEnergy.equals("")) {
					data.setRegenEnergy(Float.parseFloat(regenEnergy));
				}
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static EntityDataUnit loadEntityDataUnit(EntityDataUnit data, Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				data = XmlParserEntityData.loadEntityDataUnit(data, (Element) n);
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static void loadEntityDataUnit(NodeList nl) {
		try {
			System.out.println("Try to load unit data list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				EntityDataUnit temp = XmlParserEntityData.loadEntityDataUnit(null, nl.item(i));
				loadEntityData(temp, nl.item(i));
			}
		} catch (Exception e) {
		}
	}

	public static EntityDataMissile loadEntityDataMissile(EntityDataMissile data, Element currentElement) {
		try {
			String name, speed, range, damage, radius;
			name = currentElement.getAttribute("name");
			speed = currentElement.getAttribute("speed");
			range = currentElement.getAttribute("range");
			damage = currentElement.getAttribute("damage");
			radius = currentElement.getAttribute("radius");

			System.out.println("\tNom : " + name);
			System.out.println("\tVitesse : " + speed);
			System.out.println("\tPortée : " + range);
			System.out.println("\tDégâts : " + damage);
			System.out.println("\tRayon : " + radius);

			// set bodySize
			if (!name.equals("")) {
				if (data == null) {
					data = EntityDataMissile.create(name);
				}

				// set speed
				if (!speed.equals("")) {
					data.setSpeed(Float.parseFloat(speed));
				}

				// set range
				if (!range.equals("")) {
					data.setRange(Float.parseFloat(range));
				}

				// set damage
				if (!damage.equals("")) {
					data.setDamage(Integer.parseInt(damage));
				}

				// set radius
				if (!radius.equals("")) {
					data.setRadius(Float.parseFloat(radius));
				}
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static EntityDataMissile loadEntityDataMissile(EntityDataMissile data, Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				data = loadEntityDataMissile(data, (Element) n);
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static void loadEntityDataMissile(NodeList nl) {
		try {
			System.out.println("Try to load missile data list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				EntityDataMissile temp = loadEntityDataMissile(null, nl.item(i));
				loadEntityData(temp, nl.item(i));
			}
		} catch (Exception e) {
		}
	}

	public static EntityDataParticle loadEntityDataParticle(EntityDataParticle data, Element currentElement) {
		try {
			String name, speed, duration, rotation, type;
			name = currentElement.getAttribute("name");
			speed = currentElement.getAttribute("speed");
			duration = currentElement.getAttribute("duration");
			rotation = currentElement.getAttribute("rotation");
			type = currentElement.getAttribute("type");

			System.out.println("\tNom : " + name);
			System.out.println("\tVitesse translation : " + speed);
			System.out.println("\tDurée : " + duration);
			System.out.println("\tVitesse rotation : " + rotation);
			System.out.println("\tType : " + type);

			// set bodySize
			if (!name.equals("")) {
				if (data == null) {
					data = EntityDataParticle.create(name);
				}

				// set speed
				if (!speed.equals("")) {
					data.setSpeed(Float.parseFloat(speed));
				}

				// set duration
				if (!duration.equals("")) {
					data.setDuration(Float.parseFloat(duration));
				}

				// set rotation
				if (!rotation.equals("")) {
					data.setRotation(Float.parseFloat(rotation));
				}

				// set type
				if (!type.equals("")) {
					data.setType(Integer.parseInt(type));
				}
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static EntityDataParticle loadEntityDataParticle(EntityDataParticle data, Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				data = loadEntityDataParticle(data, (Element) n);
			}
		} catch (Exception e) {
		}
		return data;
	}

	public static void loadEntityDataParticle(NodeList nl) {
		try {
			System.out.println("Try to load particle data list with " + nl.getLength() + " elements.");
			for (int i = 0; i < nl.getLength(); i++) {
				EntityDataParticle temp = loadEntityDataParticle(null, nl.item(i));
				loadEntityData(temp, nl.item(i));
			}
		} catch (Exception e) {
		}
	}

	public static void loadEntityDataGlobal(Node n) {
		try {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				NodeList dataContent = n.getChildNodes();
				for (int i = 0; i < dataContent.getLength(); i++) {
					Node currentNode = dataContent.item(i);
					if (currentNode.getNodeName().equals("block")) {
						loadEntityData(currentNode.getChildNodes());
					}
					if (currentNode.getNodeName().equals("unit")) {
						loadEntityDataUnit(currentNode.getChildNodes());
					}
					if (currentNode.getNodeName().equals("missile")) {
						loadEntityDataMissile(currentNode.getChildNodes());
					}
					if (currentNode.getNodeName().equals("particle")) {
						loadEntityDataParticle(currentNode.getChildNodes());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
