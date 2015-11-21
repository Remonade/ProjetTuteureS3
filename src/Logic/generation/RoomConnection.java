package Logic.generation;

import java.util.ArrayList;

import Maths.generation.Vector2i;

public class RoomConnection {
    
    private ArrayList<Vector2i> points = new ArrayList<>();
    
    private RoomConnection() {
        //
    }
    
    public ArrayList<Vector2i> getPoints() {
        return points;
    }
    
    public static RoomConnection get(Vector2i start, Vector2i end) {
        RoomConnection con = new RoomConnection();
        con.points.add(new Vector2i(start));
        con.points.add(new Vector2i(end));
        return con;
    }
    
    public static RoomConnection get(Vector2i start, Vector2i elbow, Vector2i end) {
        RoomConnection con = new RoomConnection();
        con.points.add(new Vector2i(start));
        con.points.add(new Vector2i(elbow));
        con.points.add(new Vector2i(end));
        return con;
    }
    
}
