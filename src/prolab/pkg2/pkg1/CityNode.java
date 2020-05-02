
package prolab.pkg2.pkg1;

import java.util.*;
public class CityNode {
        
     private String name;
     private int licensePlate;
     //haritada sehir konumunu X ve Y tutacak;
     private int X;  
     private int Y;
     
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

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    

}
