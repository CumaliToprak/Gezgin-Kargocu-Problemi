/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab.pkg2.pkg1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author cumali_toprak
 */
public class FileUtility {

    public ArrayList<CityNode> readFile() {
        ArrayList<CityNode> cityArrayList = new ArrayList<CityNode>();
        int i;
        try {  //öncelikli listemize sadece city bilgilerini ekleriz. Komşuları daha sonra ekledik.
            File myObj = new File("/home/cumali_toprak/Desktop/Prolab Projeleri/Prolab-2.1/sehirlerVeMesafeler.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] wordArr = data.split(",");
                CityNode cityObj = new CityNode();
                cityObj.setLicensePlate(Integer.parseInt(wordArr[0]));
                cityObj.setName(wordArr[1]);
                cityArrayList.add(cityObj);
                for (i = 2; i < wordArr.length; i++) {
                    String splitedAdjacent[] = wordArr[i].split("=");

                }
            }
            //Burda tekrardan dosyayı okumamızın sebebi komşuları eklerken city bilgilerinin daha önceden listeye eklenmiş olması gerekir.
            //çünkü bu city bilgilerinden gerekli plaka numaralarını komşulara atamamız gerekir.
            int counter = 0;
            Scanner myReader2 = new Scanner(myObj);
            while (myReader2.hasNextLine()) {
                for (i = 0; i < 81; i++) {
                    if (i == counter) {
                        AdjacentNode adjacentNode = new AdjacentNode(cityArrayList.get(i).getName(), 0);
                        cityArrayList.get(counter).getAdjacentList().add(adjacentNode);
                    } else {
                        AdjacentNode adjacentNode = new AdjacentNode(cityArrayList.get(i).getName(), Integer.MAX_VALUE);
                        cityArrayList.get(counter).getAdjacentList().add(adjacentNode);
                    }

                }

                String data = myReader2.nextLine();
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

            myReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Dosyada sorun var! Kontrol ediniz. ---->  " + exception);
            System.exit(0);
        }

        return cityArrayList;
    }

    //İlgili komşuya ait plaka degerini city listesinden getirir.
    int findAdjacentIndex(String adjacentName, ArrayList<CityNode> cityArrayList) {
        for (CityNode city : cityArrayList) {
            if (city.getName().toLowerCase().equalsIgnoreCase(adjacentName)) {
                return city.getLicensePlate() - 1;
            }
        }
        return -1;
    }

    public void writeFie(TreeMap<Long, ArrayList<String>> shortestPaths) {

        try {
            Files.write(Paths.get("/home/cumali_toprak/Desktop/Prolab Projeleri/Prolab-2.1/output.txt"), ("").getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            for (Map.Entry<Long, ArrayList<String>> en : shortestPaths.entrySet()) {
                Long key = en.getKey();
                ArrayList<String> val = en.getValue();
                Files.write(Paths.get("/home/cumali_toprak/Desktop/Prolab Projeleri/Prolab-2.1/output.txt"), (val + "--->" + key + "\n\n").getBytes(), StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("output.txt dosyasi acilamadi : " + e.getMessage());
        }
        
        System.exit(0);
    }

}
