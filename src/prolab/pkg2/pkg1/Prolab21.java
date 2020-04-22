/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cumali_toprak
 */
public class Prolab21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // file reading and storing in the ArrayList
        FileUtility fileObj = new FileUtility();  //file okuma islemi.
        ArrayList<CityNode> cityArrayList = fileObj.readFile();


        AllPairShortestPath allPairShortestPath = new AllPairShortestPath(); //algoritmamızı calıştıryoruz.
        allPairShortestPath.floydWarshall(cityArrayList);


       for (CityNode cityNode : cityArrayList) {
            System.out.print("plaka : " + cityNode.getLicensePlate() + "-" + cityNode.getName() + "--->>>> ");
            for (AdjacentNode adjacentNode : cityNode.getAdjacentList()) {
                System.out.print(adjacentNode.getName() + "--" + adjacentNode.getDistance() + "--" + "shortest path : ");
                for (String pathName : adjacentNode.getShortestPath()) {
                    System.out.print(pathName+ "-");
                }
                System.out.print("---->");
            }

            System.out.println("");
        }
        
        
                for (int i = 0; i < 10; i++) {
            System.out.println("");
        }

        System.out.println(allPairShortestPath.INF);
        
        TreeMap<Long, ArrayList<String>> resultSet;
        try {
            resultSet = allPairShortestPath.findShortestPaths(cityArrayList, "kocaeli","ankara","izmir","antalya","sanliurfa","van", "denizli","bursa","edirne","rize");
            for (Map.Entry<Long, ArrayList<String>> entrySet : resultSet.entrySet()) {
            Long key = entrySet.getKey();
            ArrayList<String> value = entrySet.getValue();
            for (String city : value) {
                System.out.print(city+" - ");
            }System.out.println(" ---> "+key);
            
        }
        } catch (RequiredDataNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     
    }

    
}
