package com.company;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseProcess {
    private XMLStreamReader reader;
    private List<Item> itemList = new ArrayList<>();
    private ExecutorService service = Executors.newCachedThreadPool();
    private String uri;

    public ParseProcess(String _uri) throws IOException, XMLStreamException {
        uri = _uri;
    };

    private Map<String, Item> getCities() {
        Map<String, Item> cities = new HashMap<>();
        for(Item item : itemList) {
            if(!cities.containsKey(item.getCity())) {
                cities.put(item.getCity(), item);
            }
        }

        return cities;
    }

    private void displayCountHousesInEveryCity() {
        long start = System.currentTimeMillis();
        Map<String, Item> cities = getCities();
        Map<String, int[]> mapBuilders = new HashMap<>();
        for(String city : cities.keySet()) {
            int[] countHousesForFloor = {0,0,0,0,0,0};
            for(Item item : itemList){
                if(item.getCity().equals(city)) {
                    switch (item.getFloor()) {
                        case "1":
                            countHousesForFloor[0]++;
                            break;
                        case "2":
                            countHousesForFloor[1]++;
                            break;

                        case "3":
                            countHousesForFloor[2]++;
                            break;

                        case "4":
                            countHousesForFloor[3]++;
                            break;

                        case "5":
                            countHousesForFloor[4]++;
                            break;

                        case "6":
                            countHousesForFloor[5]++;
                            break;

                    }
                }


            }
            mapBuilders.put(city, countHousesForFloor);
        }

        for(Map.Entry<String, int[]> city : mapBuilders.entrySet()) {
            System.out.println(city.getKey() + " - " + Arrays.toString(city.getValue()));
        }
        Double current = (System.currentTimeMillis() - start) * 0.001;
        System.out.println(current);
    }



    private void searchDuplicate() {
        long start = System.currentTimeMillis();
        Map<Item, Integer> duplicate = new HashMap<>();
        duplicate.put(itemList.get(0), 1);

        for(int i = 1; i < itemList.size(); i++) {
            Item candidate = itemList.get(i);
            if(duplicate.containsKey(candidate)) {
                Integer count = duplicate.get(candidate);
                duplicate.put(candidate, ++count);
            }
        }

        if(duplicate.size() > 1 || duplicate.get(itemList.get(0)) > 1 ) {
            for(Map.Entry<Item, Integer> item : duplicate.entrySet() ) {
                System.out.println(item.getKey().toString() + " - " + item.getValue());
            }
        }
        Double current = (System.currentTimeMillis() - start) * 0.001;
        System.out.println(current);
    }

    public void startProcess() throws FileNotFoundException/*XMLStreamException*/ {
        itemList = new Parser().parse(uri);
        if(itemList.size() > 0){
            startProcessing();
        }


    }

    private void startProcessing() {
        service.submit(task1);
        service.submit(task2);
        service.shutdown();
    }

    private Runnable task1 = this::searchDuplicate;
    private Runnable task2 = this::displayCountHousesInEveryCity;
}
