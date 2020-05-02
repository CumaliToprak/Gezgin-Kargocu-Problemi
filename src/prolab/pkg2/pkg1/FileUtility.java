
package prolab.pkg2.pkg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileUtility {

    public static ArrayList<CityNode> readFile() {
        ArrayList<CityNode> cityArrayList = new ArrayList<CityNode>();
        int i;
        //öncelikli listemize sadece city bilgilerini ekleriz. Komşuları daha sonra ekledik.
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("sehirlerVeMesafeler.txt")))) {

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] wordArr = data.split(",");
                CityNode cityObj = new CityNode();
                cityObj.setLicensePlate(Integer.parseInt(wordArr[0]));
                cityObj.setName(wordArr[1]);
                cityObj.setX(Integer.parseInt(wordArr[2]));
                cityObj.setY(Integer.parseInt(wordArr[3]));
                cityArrayList.add(cityObj);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("sehilerVeMesafeler.txt dosyasi okunamadi...");
        }
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("sehirlerVeMesafeler.txt")))) {
            //Burda tekrardan dosyayı okumamızın sebebi komşuları eklerken city bilgilerinin daha önceden listeye eklenmiş olması gerekir.
            //çünkü bu city bilgilerinden gerekli plaka numaralarını komşulara atamamız gerekir.
            int counter = 0;
            while (scanner.hasNextLine()) {

                for (i = 0; i < 81; i++) {
                    if (i == counter) {
                        AdjacentNode adjacentNode = new AdjacentNode(cityArrayList.get(i).getName(), 0);
                        cityArrayList.get(counter).getAdjacentList().add(adjacentNode);
                    } else {
                        AdjacentNode adjacentNode = new AdjacentNode(cityArrayList.get(i).getName(), Integer.MAX_VALUE);
                        cityArrayList.get(counter).getAdjacentList().add(adjacentNode);
                    }

                }

                String data = scanner.nextLine();
                String[] wordArr = data.split(",");

                for (i = 2; i < wordArr.length; i++) {
                    String splitedAdjacent[] = wordArr[i].split("=");
                    int index = findAdjacentIndex(splitedAdjacent[0], cityArrayList);
                    if (index != -1) {
                        cityArrayList.get(counter).getAdjacentList().get(index).setName(splitedAdjacent[0]);
                        cityArrayList.get(counter).getAdjacentList().get(index).setDistance(Integer.parseInt(splitedAdjacent[1]));
                        cityArrayList.get(counter).getAdjacentList().get(index).getShortestPath().add(cityArrayList.get(counter).getName());
                    }

                }
                counter++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("sehilerVeMesafeler.txt dosyasi okunamadi...");
        }

        return cityArrayList;
    }

    //İlgili komşuya ait plaka degerini city listesinden getirir.
    private static int findAdjacentIndex(String adjacentName, ArrayList<CityNode> cityArrayList) {
        for (CityNode city : cityArrayList) {
            if (city.getName().toLowerCase().equalsIgnoreCase(adjacentName)) {
                return city.getLicensePlate() - 1;
            }
        }
        return -1;
    }

    public static void writeFile(TreeMap<Long, ArrayList<String>> shortestPaths) {

        String temp = "";
        for (Map.Entry<Long, ArrayList<String>> en : shortestPaths.entrySet()) {
            Long key = en.getKey();
            ArrayList<String> val = en.getValue();
            temp += val + "--->" + key + "\n\n";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            writer.write(temp);

        } catch (IOException ex) {
            System.out.println("write fonksiyonunda dosya açılırken hata oldu.");
        }

    }

    public static int dosyadanCozunurluguOku() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("cozunurlukAyar.txt")))) {

            while (scanner.hasNextLine()) {
                 String data = scanner.nextLine();
                 if(data.equalsIgnoreCase("hd".trim())){
                     return 0;
                 }
            
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cozunurluk ayari dosyasi okunamadi...");
            System.exit(0);
        }
        return 1;
    }

}
