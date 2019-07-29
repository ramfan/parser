package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    private Map<Integer, Item> itemsList = new HashMap<>(Main.MAX_SIZE);
    private Map<String, Item> cities = new HashMap<>(Main.COUNT_CITIES);
    private String uri;

    public Parser(String _uri) {
        uri = _uri;
    }

    public Map<Integer, Item> parse() throws IOException {
        long start = System.currentTimeMillis();

        FileReader file = new FileReader(uri);
        BufferedReader scanner = new BufferedReader(file);
        Integer countItems = 0;
        scanner.readLine();
        while(scanner.ready()) {
            String line = scanner.readLine();
            List<String> candidate = new ArrayList<>();
            if(!line.equals("<root>") && !line.equals("<root/>") ) {
                char[] charArr = line.toCharArray();
                String value = new String();
                for (int i = 10; i < charArr.length; i++) {
                    int count = 0;
                    if( charArr[i] != '/' &&  charArr[i] != '>' && charArr[i] != '=' && (int) charArr[i] < 64  || (int) charArr[i] > 122){
                        if(charArr[i] == '"'){
                            count++;
                        }

                        if(count % 2 == 0) {
                            if(value.equals("") && charArr[i] == ' '){
                                continue;
                            }
                            value += charArr[i];
                        }

                    }

                    if(count % 2 != 0 && !value.equals(" ") && !value.equals("")) {
                        candidate.add(value);
                        value = new String();
                    }
                }
                if(candidate.size() == 4) {
                    Item item = new Item(candidate.get(0), candidate.get(1), candidate.get(2), Integer.parseInt(candidate.get(3)));
                    if(!cities.containsKey(item.getCity())) {
                        cities.put(item.getCity(), item);
                    }
                    itemsList.put(countItems++, item);
                }
            }

        }
        Double current = (System.currentTimeMillis() - start) * 0.001;
        System.out.println(current);
        return itemsList;

    }

    public Map<String, Item> getCities() {
        return cities;
    }
}
