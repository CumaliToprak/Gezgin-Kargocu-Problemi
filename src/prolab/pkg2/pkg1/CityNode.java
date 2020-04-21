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
     
    public int getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AdjacentNode> getAdjacentList() {
        return adjacentList;
    }

    public void setAdjacentList(ArrayList<AdjacentNode> adjacentList) {
        this.adjacentList = adjacentList;
    }

}
