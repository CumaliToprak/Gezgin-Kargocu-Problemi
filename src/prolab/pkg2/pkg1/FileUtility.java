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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author cumali_toprak
 */
public class FileUtility {

    public ArrayList<CityNode> readFile() {
        ArrayList<CityNode> cityArrayList = new ArrayList<CityNode>();
        int i;
        try {
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
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }

        return cityArrayList;
    }

    int findAdjacentIndex(String adjacentName, ArrayList<CityNode> cityArrayList) {
        for (CityNode city : cityArrayList) {
            if (city.getName().toLowerCase().equalsIgnoreCase(adjacentName)) {
                return city.getLicensePlate() - 1;
            }
        }
        return -1;
    }

    //Bunu başlangıçta dosya formatını düzeltmek için kullandık artık kullanmayacağız.
//    public void writeFile()
//            throws IOException {
//        for (String str : arrLine) {
//
//            String textToAppend = str;
//
//            BufferedWriter writer = new BufferedWriter(
//                    new FileWriter("/home/cumali_toprak/Desktop/calismalar/java/Prolab-2.1/sampleFile.txt", true) //Set true for append mode
//            );
//            writer.newLine();   //Add new line
//            writer.write(textToAppend);
//            writer.close();
//        }
//
//    }
}
