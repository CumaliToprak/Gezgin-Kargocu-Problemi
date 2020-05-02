
package prolab.pkg2.pkg1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AllPairShortestPath {

    private static ArrayList<CityNode> cityArrayList;
    final static int INF = Integer.MAX_VALUE, V = 81;

    public static void floydWarshall() {
        cityArrayList = FileUtility.readFile(); // dosyadan sehireler okundu.
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

    private static int findIndex(ArrayList<CityNode> cityArrayList, String adjacentName) {
        for (CityNode cityNode : cityArrayList) {
            if (cityNode.getName().equalsIgnoreCase(adjacentName)) {
                return cityNode.getLicensePlate() - 1;

            }
        }
        return 1;
    }

    //Bu metod bize istenilen güzergaha göre en kısa yolları bulur.
    public static void findShortestPaths(String[] path, ArrayList<ArrayList<Integer>> koordinatlarX,
            ArrayList<ArrayList<Integer>> koordinatlarY,
            ArrayList<String> calculatedDistance) throws RequiredDataNotFoundException {

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
        TreeMap<Long, ArrayList<Integer>> coordinateXTree = new TreeMap<>();
        TreeMap<Long, ArrayList<Integer>> coordinateYTree = new TreeMap<>();
        for (i = 0; i < resultSet.size(); i++) {
            List<Integer> result = resultSet.get(i); //sıra ile tüm seçimleri deniyecez.
            int sourceCity = result.get(0);// plaka koduna gore islem yapiyoruz.
            int nextCity = result.get(1);
            int counter = 0;
            long totalDistance = 0;
            ArrayList<String> route = new ArrayList<>();
            ArrayList<Integer> routeXInt = new ArrayList<>();// sehirlerin bir yol icin X koordinatını tutacak
            ArrayList<Integer> routeYInt = new ArrayList<>();// sehirlerin bir yol icin Y koordinatını tutacak
            for (int index : result) { // suanki guzergah icin sehirlerin koordinatları arrayListlere ekleniyor.
                index -= 1;
                routeXInt.add(cityArrayList.get(index).getX()); // sehirin X koordinati eklendi.
                routeYInt.add(cityArrayList.get(index).getY());
            }

            while (counter < cityNumber) { //tüm sehirleri sıra ile gezeriz.
                totalDistance += cityArrayList.get(sourceCity - 1).getAdjacentList().get(nextCity - 1).getDistance();
                if (counter > 0) {
                    ArrayList<String> tempList = cityArrayList.get(sourceCity - 1).getAdjacentList().get(nextCity - 1).getShortestPath();
                    route.addAll(tempList.subList(1, tempList.size()));
                } else {
                    route.addAll(cityArrayList.get(sourceCity - 1).getAdjacentList().get(nextCity - 1).getShortestPath());
                }

                sourceCity = nextCity;
                nextCity = result.get(result.indexOf(sourceCity) + 1);
                counter++;
            }

            //Burada en kısa 5 güzergahı hesaplatıyoruz.
            if (shortestFiveRoute.size() >= 5) {
                shortestFiveRoute.put(totalDistance, route);
                shortestFiveRoute.remove(shortestFiveRoute.lastKey());
                coordinateXTree.put(totalDistance, routeXInt);
                coordinateXTree.remove(coordinateXTree.lastKey());
                coordinateYTree.put(totalDistance, routeYInt);
                coordinateYTree.remove(coordinateYTree.lastKey());

            } else {
                shortestFiveRoute.put(totalDistance, route);
                coordinateXTree.put(totalDistance, routeXInt);
                coordinateYTree.put(totalDistance, routeYInt);
            }

        }
        // koodinat bilgisi cekiliyor.
        for (Map.Entry<Long, ArrayList<String>> en : shortestFiveRoute.entrySet()) {  // x koordinatları haritada cizdirilmek icin ekleniyor.             
            ArrayList<String> val = en.getValue();
            ArrayList<Integer> oneRouteCoordinateX = new ArrayList<>();
            ArrayList<Integer> oneRouteCoordinateY = new ArrayList<>();
            // bir rota icin dongu
            for (String cityName : val) {
                // sehirin ismiyle plaka kodunu bulduk ve city arrayListinden citynin koordinatlarına ulaştık.
                int plateNumber = findCityPlateNumber(cityArrayList, cityName);
                int X = cityArrayList.get(plateNumber - 1).getX();
                int Y = cityArrayList.get(plateNumber - 1).getY();
                oneRouteCoordinateX.add(X);
                oneRouteCoordinateY.add(Y);

            }
            koordinatlarX.add(oneRouteCoordinateX);// hesaplanan koordinatlar arrayliste aktarılıyor.
            koordinatlarY.add(oneRouteCoordinateY);

        }// hesaplanmis mesafeler array Liste aktariliyor.
        for (Map.Entry<Long, ArrayList<String>> en : shortestFiveRoute.entrySet()) {           
            Long Distance = en.getKey();
            calculatedDistance.add(Long.toString(Distance));// mesafe string e cevirliyor.
        }
        
      
        
        FileUtility.writeFile(shortestFiveRoute); // en kisa yollar yaziliyor.
    }

    //Gönderdiğimiz şehirlerin plakalarını dönderir.
    private static int findCityPlateNumber(ArrayList<CityNode> cityArrayList, String city) throws RequiredDataNotFoundException {
        for (CityNode cityItem : cityArrayList) {
            if (cityItem.getName().equalsIgnoreCase(city)) {
                return cityItem.getLicensePlate();
            }
        }
        throw new RequiredDataNotFoundException(city + " için plaka numarası bulunamadı!");
    }

   
}
