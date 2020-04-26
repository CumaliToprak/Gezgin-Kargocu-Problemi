/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
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

        for (i = 0; i < 81; i++) {   //Her city icin komsulara giden degerleri diziye atadık.
            for (j = 0; j < 81; j++) {
                dist[i][j] = cityArrayList.get(i).getAdjacentList().get(j).getDistance();
            }
        }

        for (k = 0; k < V; k++) {
            // Her city degerini kaynak node olarak sıra ile alır. 
            for (i = 0; i < V; i++) {
                // Her city degerini varış noktası olarak sıra ile alır. 
                // yukarıdaki sıra degerine göre. 
                for (j = 0; j < V; j++) {
                    // Eger i' den j' ye k üzerinden daha kısa bir güzergah varsa güncelleme yapılır.
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        cityArrayList.get(i).getAdjacentList().get(j).setDistance(dist[i][j]);//güncellenen komşunun yeni maliyeti.
                        cityArrayList.get(i).getAdjacentList().get(j).getShortestPath().clear(); //eski shortest path silinir.
                        cityArrayList.get(i).getAdjacentList().get(j).getShortestPath().//shortest path güncellenir.
                                addAll(cityArrayList.get(i).getAdjacentList().get(k).getShortestPath());
                        cityArrayList.get(i).getAdjacentList().get(j).getShortestPath().
                                addAll(cityArrayList.get(k).getAdjacentList().get(j).getShortestPath());
                        cityArrayList.get(i).getAdjacentList().get(j).setShortestPathDistance(dist[i][j]);
                    }
                }
            }
        }

        //shortest path'in sonuna kendi komsuluk degerlerini ekledik.
        for (CityNode cityNode : cityArrayList) {
            for (AdjacentNode adjacent : cityNode.getAdjacentList()) {
                adjacent.getShortestPath().add(adjacent.getName());
            }
        }

    }

    int findIndex(ArrayList<CityNode> cityArrayList, String adjacentName) {
        for (CityNode cityNode : cityArrayList) {
            if (cityNode.getName().equalsIgnoreCase(adjacentName)) {
                return cityNode.getLicensePlate() - 1;

            }
        }
        return 1;
    }

    //Bu metod bize istenilen güzergaha göre en kısa yolları bulur.
    TreeMap<Long, ArrayList<String>> findShortestPaths(ArrayList<CityNode> cityArrayList, String... path) throws RequiredDataNotFoundException {

        int cityNumber = path.length;
        int[] plateNumbers = new int[cityNumber - 1];
        int i;
        int startPoint; //basladığımız yere geri dönmek icin.

        startPoint = findCityPlateNumber(cityArrayList, path[0]); //başladığımız yerin plaka numarasını saklarız

        //baslangıc ve bitisi permutasyon methoduna göndermeye gerek yok.
        for (i = 1; i < path.length; i++) {     //indexler üzerinden arama yapacagımız icin plakaları cekiyoruz.
            plateNumbers[i - 1] = findCityPlateNumber(cityArrayList, path[i]);
        }

        Permutation permutation = new Permutation();
        List<List<Integer>> resultSet = permutation.permute(plateNumbers);
        for (List<Integer> list : resultSet) {  //her result set'e baslangıc ve bitis noktaları ekledik.
            list.add(0, startPoint);
            list.add(startPoint);
        }

        TreeMap<Long, ArrayList<String>> shortestFiveRoute = new TreeMap<>();// Tree map ascending sıralama yaptığı için bunu kullandık.
        for (i = 0; i < resultSet.size(); i++) {
            List<Integer> result = resultSet.get(i); //sıra ile tüm seçimleri deniycez.
            int sourceCity = result.get(0);
            int nextCity = result.get(1);
            int counter = 0;
            long totalDistance = 0;
            ArrayList<String> route = new ArrayList<>();

            while (counter < cityNumber) { //tüm sehirleri sıra ile gezeriz.
                      totalDistance += cityArrayList.get(sourceCity - 1).getAdjacentList().get(nextCity - 1).getDistance();
                if(counter>0)
                {   ArrayList<String> tempList = cityArrayList.get(sourceCity - 1).getAdjacentList().get(nextCity - 1).getShortestPath();
                    route.addAll(tempList.subList(1, tempList.size()));
                }else
                {
                    route.addAll(cityArrayList.get(sourceCity - 1).getAdjacentList().get(nextCity - 1).getShortestPath());
                }
                
                sourceCity = nextCity;
                nextCity = result.get(result.indexOf(sourceCity) + 1);
                counter++;
            }
           
            //Burada en kısa 5 gğzergahı hesaplatıyoruz.
            if (shortestFiveRoute.size() >= 5) {
                shortestFiveRoute.put(totalDistance, route);
                shortestFiveRoute.remove(shortestFiveRoute.lastKey());
            } else {
                shortestFiveRoute.put(totalDistance, route);
            }

        }

        return shortestFiveRoute;  //bulduğumuz sonuçları  dönüyoruz.
    }

    //Gönderdiğimiz şehirlerin plakalarını dönderir.
    public int findCityPlateNumber(ArrayList<CityNode> cityArrayList, String city) throws RequiredDataNotFoundException {
        for (CityNode cityItem : cityArrayList) {
            if (cityItem.getName().equalsIgnoreCase(city)) {
                return cityItem.getLicensePlate();
            }
        }
        throw new RequiredDataNotFoundException(city + " için plaka numarası bulunamadı!");
    }

}
