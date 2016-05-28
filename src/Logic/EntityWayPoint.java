package Logic;

import Maths.Vector2f;
import java.util.ArrayList;

public class EntityWayPoint extends Entity {

	public int m_ID;
	private int m_direction = 0;
	private int m_target = 0;
	private Vector2f m_out = new Vector2f();
	private boolean m_active = true;
	private int m_realm = 0;

	public EntityWayPoint() {
		super();
	}

	public void setID(int id) {
		m_ID = id;
	}

	public int getID() {
		return m_ID;
	}

	public void setActive(boolean active) {
		m_active = active;
	}

	public boolean isActive() {
		return m_active;
	}

	public void setDirection(int direction) {
		m_direction = direction;
	}

	public int getDirection() {
		return m_direction;
	}

	public void setOut(Vector2f out) {
		m_out = out;
	}

	public void setOut(float x, float y) {
		m_out.x = x;
		m_out.y = y;
	}

	public Vector2f getOut() {
		return m_out;
	}

	public void setTarget(int target) {
		m_target = target;
	}

	public int getTarget() {
		if (m_direction == -1) {
			return 1;
		}
		if (m_direction == 1) {
			return 0;
		}
		return m_target;
	}

	public EntityWayPoint getTargetWayPoint() {
		return EntityWayPoint.get(m_realm + m_direction, getTarget());
	}

	public void setRealm(int realm) {
		m_realm = realm;
	}

	public int getRealm() {
		return m_realm;
	}

	public void moveToWayPoint(Entity e) {
		if (e != null) {
			e.setPos(getPos().add(m_out));
		}
	}

	private static ArrayList<EntityWayPoint> all = new ArrayList<EntityWayPoint>();

	public static EntityWayPoint create() {
		EntityWayPoint e = new EntityWayPoint();
		all.add(e);
		return e;
	}

	public static EntityWayPoint get(int realm, int target) {
		for (EntityWayPoint e : all) {
			if (e.getRealm() == realm && e.getID() == target) {
				return e;
			}
		}
		return null;
	}

	public static int getCount() {
		return all.size();
	}
}
