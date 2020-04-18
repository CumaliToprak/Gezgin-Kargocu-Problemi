/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.util.ArrayList;

/**
 *
 * @author cumali_toprak
 */
public class AdjacentNode {
    private String name;
    private long distance;

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
     

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */    
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the distance
     */
    public long getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(long distance) {
        this.distance = distance;
    }

    /**
     * @return the shortestPath
     */
    public ArrayList<String> getShortestPath() {
        return shortestPath;
    }

    /**
     * @param shortestPath the shortestPath to set
     */
    public void setShortestPath(ArrayList<String> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
