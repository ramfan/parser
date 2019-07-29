package com.company;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseProcess {

    private Map<Integer, Item> itemList = new HashMap<>(Main.MAX_SIZE);
    private String uri;
    private Map<String, Item> cities = new HashMap<>(Main.COUNT_CITIES);
    private ExecutorService service = Executors.newCachedThreadPool();

    public ParseProcess(String _uri) {
        uri = _uri;
    }

    private void displayCountHousesInEveryCity() {
        long start = System.currentTimeMillis();
        for(Map.Entry<String, Item> city : cities.entrySet()) {
            int[] countHousesForFloor = {0,0,0,0,0};
            for( Map.Entry<Integer, Item> item: itemList.entrySet()){
                if(item.getValue().getCity().equals(city.getKey())) {
                    switch (item.getValue().getFloor()) {
                        case 1:
                            countHousesForFloor[0]++;
                            break;
                        case 2:
                            countHousesForFloor[1]++;
                            break;

                        case 3:
                            countHousesForFloor[2]++;
                            break;

                        case 4:
                            countHousesForFloor[3]++;
                            break;

                        case 5:
                            countHousesForFloor[4]++;
                            break;

                    }
                }


            }

            System.out.println(city.getKey() + " - " + Arrays.toString(countHousesForFloor));
        }

    }



    private void searchDuplicate() {
        long start = System.currentTimeMillis();
        Map<Item, Integer> duplicate = new HashMap<>(Main.MAX_SIZE);
        duplicate.put(itemList.get(0), 1);

        for(int i = 1; i < itemList.size(); i++) {
            Item candidate = itemList.get(i);
            if(duplicate.containsKey(candidate)) {
                Integer count = duplicate.get(candidate);
                duplicate.put(candidate, ++count);
            } else {
                duplicate.put(candidate, 1);
            }
        }


        for(Map.Entry<Item, Integer> item : duplicate.entrySet() ) {
            if( item.getValue() > 1) {
                System.out.println(item.getKey().toString() + " - " + item.getValue());
            }

        }

    }

    public void startProcess() throws IOException {
        Parser parser = new Parser(uri);
        itemList = parser.parse();
        cities = parser.getCities();
        if(itemList.size() > 0){
            startProcessing();
        }


    }

    private void startProcessing() {
        long start = System.currentTimeMillis();
        service.submit(task1);
        service.submit(task2);
        System.out.println((System.currentTimeMillis() - start) * 0.001);

        service.shutdown();
    }

    private Runnable task1 = this::searchDuplicate;
    private Runnable task2 = this::displayCountHousesInEveryCity;
}
