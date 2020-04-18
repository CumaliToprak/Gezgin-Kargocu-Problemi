/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.util.*;
import java.util.Set;
/**
 *
 * @author cumali_toprak
 */
public class CityNode {
        
     private String name;
     private int licensePlate;
     
     //this is for the adjecent list for the all the city that are in the graph.
     private ArrayList<AdjacentNode> adjacentList = new ArrayList<AdjacentNode>();
     
    
    /**
     * @return the licensePlate
     */
    public int getLicensePlate() {
        return licensePlate;
    }

    /**
     * @param licensePlate the licensePlate to set
     */
    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

  

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
     * @return the adjacentList
     */
    public ArrayList<AdjacentNode> getAdjacentList() {
        return adjacentList;
    }

    /**
     * @param adjacentList the adjacentList to set
     */
    public void setAdjacentList(ArrayList<AdjacentNode> adjacentList) {
        this.adjacentList = adjacentList;
    }

    /**
     * @param adjacentList the adjacentList to set
     */
   

}
