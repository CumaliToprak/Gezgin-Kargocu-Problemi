/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author cumali_toprak
 */
public class Prolab21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // file reading and storing in the ArrayList
        FileUtility fileObj = new FileUtility();
        ArrayList<CityNode> cityArrayList = fileObj.readFile();

        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }

        AllPairShortestPath allPairShortestPath = new AllPairShortestPath();
        allPairShortestPath.floydWarshall(cityArrayList);

        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }

        for (CityNode cityNode : cityArrayList) {
            System.out.print("plaka : " + cityNode.getLicensePlate() + "-" + cityNode.getName() + "--->>>> ");
            for (AdjacentNode adjacentNode : cityNode.getAdjacentList()) {
                System.out.print(adjacentNode.getName() + "--" + adjacentNode.getDistance() + "--" + "shortest path : ");
                for (String pathName : adjacentNode.getShortestPath()) {
                    System.out.print(pathName+ "-");
                }System.out.print(adjacentNode.getName());
                System.out.print("---->");
            }

            System.out.println("");
        }
        
        allPairShortestPath.findShortestPaths("kocaeli","ankara","adana","bursa");      
        
        

    }

    //   fileObj.writeFile();
}
