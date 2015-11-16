package Logic.generation;

import Logic.generation.EdgeList.Edge;
import Maths.generation.Vector2i;
import Maths.generation.delaunay.DT_Point;
import Maths.generation.delaunay.DelaunayTriangulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generation {
    public enum State {
        INIT, READY, NONE, GENERATE, SEPARATE, SELECT, TRIANGULATE, MINSPAN, LOOPS, FILL, CONNECTION, INTERSECTION
    }
    
    private State m_state = State.INIT;
    private boolean m_ready = true;
    private static final boolean m_sleep = true;
    
    private final Vector2i m_areaSize = new Vector2i(300, 300);
    private final Vector2i m_areaCenter = new Vector2i(m_areaSize.x >> 1, m_areaSize.y >> 1);
    
    private final ArrayList<Cell> m_allCells = new ArrayList<>();
    private final ArrayList<Cell> m_fillerCells = new ArrayList<>();
    private final ArrayList<Cell> m_masterCells = new ArrayList<>();
    private final ArrayList<RoomConnection> m_connections = new ArrayList<>();
    
    private DelaunayTriangulation m_dt;
    private DisjointSetForest<DT_Point> m_forest;
    private final ArrayList<EdgeList.Edge> m_minTree = new ArrayList<>();
    private final ArrayList<EdgeList.Edge> m_discardEdge = new ArrayList<>();
    private ArrayList<EdgeList.Edge> m_edgeList;
    
    private CellBounds m_bounds;
    private final ArrayList<Cell> m_rooms = new ArrayList<>();
    
    private final int m_roomMin = 10;
    private final int m_roomArea = 16;
    private final Random m_randSeed = new Random();
    private XORShiftRandom m_rand;
    private int m_cellCount = 100;
    private final int m_radius = 16;
    private final Vector2i m_cellSize = new Vector2i(1, 12);
    private final float m_loopPercentage = 0.15f;
    
    public Generation() {
        run(m_state);
    }
    
    public void run(State state) {

        System.out.println(state.toString());
        Vector2i v = new Vector2i();

        switch (state) {
        case INIT:
            m_allCells.clear();
            m_fillerCells.clear();
            m_masterCells.clear();
            m_connections.clear();
            m_minTree.clear();
            m_rooms.clear();
            m_discardEdge.clear();
            m_forest = new DisjointSetForest<>();

            m_bounds = null;
            System.out.println(state.toString());
            System.out.println("   Cleared");
            m_rand = getRandom();
            m_ready = true;
            nextState();
            break;

        case GENERATE: 
            //Generate cell
            m_cellCount = m_rand.nextInt(150) + 50;
            System.out.println("   Target cell count: " + m_cellCount);
            System.out.println("   Generating cells...");
            for (int i = 0; i < m_cellCount; i++) {
                sleep(10);
                Generator.generateCell(m_rand, m_cellSize, m_areaCenter, m_radius, m_allCells);
            }
            System.out.println("   Cells generated: " + m_allCells.size());
            m_ready = true;
            nextState();
            break;

        case SEPARATE:
            //Separation steering behavior
            System.out.println("   Separating overlapping cells...");
            int overlap = 0;
            while(Generator.isAnyOverlap(m_allCells)) {
              sleep(30);
              Generator.moveCells(m_rand, m_allCells);
              overlap++;
            }
            System.out.println("   Overlap passes: " + overlap);
            m_ready = true;
            nextState();
            break;

        case FILL:
            //Fill vacant area with 1x1 filler cells
            System.out.println("   Filling empty space...");
            m_bounds = CellBounds.get(m_allCells);
            System.out.println("   " + m_bounds.toString());
            for(int x = m_bounds.getLeft(); x < m_bounds.getRight(); x++) {
              for(int y = m_bounds.getBottom(); y < m_bounds.getTop(); y++) {
                if (Generator.getCell(v.set(x, y), m_allCells) == null) {
                  Cell cell = new Cell();
                  cell.center.set(x, y);
                  cell.type = Cell.TYPE_FILL;
                  m_fillerCells.add(cell);
                }
              }
            }
            System.out.println("   Filler cells: " + m_fillerCells.size());
            System.out.println("   Total cells: " + (m_fillerCells.size() + m_allCells.size()));
            m_ready = true;
            nextState();
            break;


        case SELECT:
            //Flag cells over an area threshold as rooms
            System.out.println("   Selecting rooms...");
            for(Cell cell : m_allCells) {
              if(cell.getArea() > m_roomArea) {
                m_rooms.add(cell);
                cell.type = Cell.TYPE_ROOM;
                sleep(40);
              }
            }
            System.out.println("   Rooms discovered: " + m_rooms.size());
            System.out.println("   Non-rooms left: " + (m_allCells.size() - m_rooms.size()));

            //Ensure that the minimum room count is reached
            if(m_rooms.size() < m_roomMin) {
              ArrayList<Cell> list = new ArrayList<>(m_allCells);
              if (!m_rooms.isEmpty()) {
                list.removeAll(m_rooms);
              }
              Collections.sort(list, new CellAreaSorter());
              int augment = 0;
              Cell cell;
              while(m_rooms.size() < m_roomMin) {
                cell = list.remove(0);
                cell.type = Cell.TYPE_ROOM;
                m_rooms.add(cell);
                augment++;
              }
              System.out.println("   Extra rooms added: " + augment);
            } else {
              System.out.println("   No extra rooms added");
            }
            m_ready = true;
            nextState();
            break;

        case TRIANGULATE:
            //Generate the Delaunay Triangulation using room center points
            System.out.println("   Generating DT...");
            m_dt = new DelaunayTriangulation();
            for(Cell room : m_rooms) {
              m_dt.insertPoint(new DT_Point(room.center.x, room.center.y));
              sleep(50);
            }
            //Generate a list of edges using a disjoint-set forest. List is sorted by edge length, increasing.
            m_edgeList = new EdgeList(m_dt, m_forest).getList();
            System.out.println("   Edges: " + m_edgeList.size());
            m_ready = true;
            nextState();
            break;


          case MINSPAN:
            //Generate a minimum spanning tree using the edge list
            System.out.println("   Generating min-span tree...");
            while(m_edgeList.size() > 0) {
              EdgeList.Edge edge = m_edgeList.remove(m_edgeList.size() - 1);
              if (!m_forest.findSet(edge.getN1()).equals(m_forest.findSet(edge.getN2()))) {
                m_minTree.add(edge);
                m_forest.union(edge.getN1(), edge.getN2());
                sleep(50);
              } else {
                m_discardEdge.add(edge);
              }
            }
            System.out.println("   Edges: " + m_minTree.size());
            System.out.println("   Discarded edges: " + m_discardEdge.size());
            m_ready = true;
            nextState();
            break;

        case LOOPS:
            //Add a percentage of discarded edges into to the min tree
            int m_loopsAdded = 0;
            int loopsToAdd = (int) (m_loopPercentage * m_discardEdge.size());
            m_minTree.add(m_discardEdge.remove(m_discardEdge.size() - 1));
            loopsToAdd--;
            m_loopsAdded++;
            sleep(50);
            Collections.shuffle(m_discardEdge, m_rand);
            while(loopsToAdd > 0) {
              m_minTree.add(m_discardEdge.remove(m_discardEdge.size() - 1));
              loopsToAdd--;
              m_loopsAdded++;
              sleep(50);
            }
            System.out.println("   Loop edges added: " + m_loopsAdded);
            m_ready = true;
            nextState();
            break;

        case CONNECTION:
            //Connect the rooms
            Vector2i start = new Vector2i();
            Vector2i end = new Vector2i();
            int straight = 0;
            int elbow = 0;
            while(m_minTree.size() > 0) {
              Edge e = m_minTree.remove(m_minTree.size() - 1);
              Generator.getVectorFromDTPoint(e.getP1(), start);
              Generator.getVectorFromDTPoint(e.getP2(), end);
              if(start.x == end.x || start.y == end.y) {
                // straight connection
                m_connections.add(RoomConnection.get(start, end));
                straight++;
              } else {
                if(m_rand.nextBoolean()) {
                  v.set(start.x, end.y);
                } else {
                  v.set(end.x, start.y);
                }
                m_connections.add(RoomConnection.get(start, v, end));
                elbow++;
              }
              sleep(50);
            }
            System.out.println("   Straight connections: " + straight);
            System.out.println("   L connections: " + elbow);
            System.out.println("   Total connections: " + m_connections.size());
            m_ready = true;
            nextState();
            break;


        case INTERSECTION:
            //Walk the connections, test for intersection
            System.out.println("   Testing intersection...");
            ArrayList<Cell> intersecting = new ArrayList<>();
            while (m_connections.size() > 0) {
              int extraWidth = 1;
              RoomConnection con = m_connections.remove(m_connections.size() - 1);
              ArrayList<Vector2i> p = con.getPoints();
              Generator.getIntersectingCells(p.get(0), p.get(1), m_allCells, intersecting, extraWidth);
              Generator.getIntersectingCells(p.get(0), p.get(1), m_fillerCells, intersecting, extraWidth);
              if (p.size() == 3) {
                Generator.getIntersectingCells(p.get(1), p.get(2), m_allCells, intersecting, extraWidth);
                Generator.getIntersectingCells(p.get(1), p.get(2), m_fillerCells, intersecting, extraWidth);
              }
              m_allCells.removeAll(intersecting);
              m_fillerCells.removeAll(intersecting);
              m_masterCells.addAll(intersecting);
              sleep(50);
            }
            System.out.println("   Intersected cells: " + m_masterCells.size());
            m_ready = true;
            break;

        default:
            nextState();
            break;
     
    }
   
    }
    
    public void nextState() {
        if (m_ready) {
          m_ready = false;
          switch (m_state) {
          case INIT:
            m_state = State.GENERATE;
            break;
          case GENERATE:
            m_state = State.SEPARATE;
            break;
          case SEPARATE:
            m_state = State.FILL;
            break;
          case FILL:
            m_state = State.SELECT;
            break;
          case SELECT:
            m_state = State.TRIANGULATE;
            break;
          case TRIANGULATE:
            m_state = State.MINSPAN;
            break;
          case MINSPAN:
            m_state = State.LOOPS;
            break;
          case LOOPS:
            m_state = State.CONNECTION;
            break;
          case CONNECTION:
            m_state = State.INTERSECTION;
            break;
          default:
            break;
          }
          run(m_state);
        }
    }
    
    public XORShiftRandom getRandom() {
        long seed = 4769910376287802944L;
        System.out.println("   Seed: " + seed);
            return new XORShiftRandom(seed);
    }
    
    public void sleep(int time) {
        if(m_sleep) {
          try {
            Thread.sleep(time);
          } 
            catch (InterruptedException e) {
          }
        }
    }
    
    public ArrayList<Cell> getMasterCells() {
        return m_masterCells;
    }
    
}
