package Listok.sber;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void readCities(List<City> listOfCities, String path) throws IOException {
        var in = new Scanner(Path.of(path));
        String[] infoArray;
        while(in.hasNext()) {
            infoArray = in.nextLine().split(";");
            if(infoArray.length < 6) {
                listOfCities.add(new City(
                        infoArray[1],
                        infoArray[2],
                        infoArray[3],
                        Integer.parseInt(infoArray[4]),
                        ""));
            } else {
                listOfCities.add(new City(
                        infoArray[1],
                        infoArray[2],
                        infoArray[3],
                        Integer.parseInt(infoArray[4]),
                        infoArray[5]));
            }
        }
    }

    public static void printCities(List<City> listOfCities) {
        for(var city : listOfCities) {
            System.out.println(city);
        }
    }

    public static void sortByName(List<City> listOfCities) {
        listOfCities.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
    }

    public static void sortByDistrict(List<City> listOfCities) {
        listOfCities.sort(new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                if(city1.getDistrict().equals(city2.getDistrict())) return city1.getName().compareTo(city2.getName());
                return city1.getDistrict().compareTo(city2.getDistrict());
            }
        });

    }

    public static Map<String, Integer> maxPopulation(List<City> listOfCities) {
        var arrayOfCities = listOfCities.toArray(new City[0]);
        int max = 0;
        int indexOfMax = -1;
        for(int i = 0; i < arrayOfCities.length; ++i) {
            if(arrayOfCities[i].getPopulation() > max) {
                max = arrayOfCities[i].getPopulation();
                indexOfMax = i;
            }
        }
        var res = new HashMap<String, Integer>();
        res.put("Index", indexOfMax);
        res.put("Population", max);
        return res;
    }

    public static void main(String[] args) {
        var listOfCities = new ArrayList<City>();
        try {
            readCities(listOfCities, "citiesList.csv");
            System.out.println(maxPopulation(listOfCities));
        } catch (IOException e) {
            System.out.println("Problems with file!");
        }
    }

}
