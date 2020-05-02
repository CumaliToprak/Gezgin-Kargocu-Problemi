
package prolab.pkg2.pkg1;

import java.util.ArrayList;

public class AdjacentNode {
    private String name;
    private long distance;
    private long ShortestPathDistance;

    public AdjacentNode() {
       this.name=null;
       this.distance=0;
    }
    public AdjacentNode(String name, long distance)
    {
        this.name=name;
        this.distance=distance;
    }
    
 
    private ArrayList<String> shortestPath = new ArrayList<String>();
     
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public ArrayList<String> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(ArrayList<String> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public long getShortestPathDistance() {
        return ShortestPathDistance;
    }

    public void setShortestPathDistance(long ShortestPathDistance) {
        this.ShortestPathDistance = ShortestPathDistance;
    }
}
