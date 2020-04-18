/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.util.ArrayList;
import java.util.Arrays;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author cumali_toprak
 */
public class AllPairShortestPath {

    final static int INF = Integer.MAX_VALUE, V = 81;

    void floydWarshall(ArrayList<CityNode> cityArrayList) {
        long dist[][] = new long[V][V];
        int i, j, k;

        for (i = 0; i < 81; i++) {
            for (j = 0; j < 81; j++) {
                dist[i][j] = cityArrayList.get(i).getAdjacentList().get(j).getDistance();
            }
        }

        int a, b;
        for (a = 0; a < 81; a++) {
            for (b = 0; b < 81; b++) {
                System.out.print(" " + dist[a][b]);
            }
            System.out.println();
        }

        for (k = 0; k < V; k++) {
            // Pick all vertices as source one by one 
            for (i = 0; i < V; i++) {
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        cityArrayList.get(i).getAdjacentList().get(j).setDistance(dist[i][k] + dist[k][j]);
                        cityArrayList.get(i).getAdjacentList().get(j).getShortestPath().clear();
                        cityArrayList.get(i).getAdjacentList().get(j).getShortestPath().addAll(cityArrayList.get(i).getAdjacentList().get(k).getShortestPath());
                        cityArrayList.get(i).getAdjacentList().get(j).getShortestPath().addAll(cityArrayList.get(k).getAdjacentList().get(j).getShortestPath());
                    }
                }
            }
        }
        //  Print the shortest distance matrix 
        printSolution(dist);
    }

    int findIndex(ArrayList<CityNode> cityArrayList, String adjacentName) {
        for (CityNode cityNode : cityArrayList) {
            if (cityNode.getName().equalsIgnoreCase(adjacentName)) {
                return cityNode.getLicensePlate() - 1;

            }
        }
        return 1;
    }

    void printSolution(long dist[][]) {
        System.out.println("The following matrix shows the shortest "
                + "distances between every pair of vertices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    void findShortestPaths(String... path) {
        ArrayList<String> destination = new ArrayList<>(Arrays.asList(path));
          destination.add(path[0]);
        int flagList[] = new int[path.length+1];
        int distance[] = new int[path.length+1];
        
        
        
        
    }

}

// Contributed by Aakash Hasija 

