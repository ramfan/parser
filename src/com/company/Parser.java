package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    private List<Item> itemsList = new ArrayList<>();
    private Map<String, Item> cities = new HashMap<>();
    private String uri;

    public Parser(String _uri) {
        uri = _uri;
    }

    public List<Item> parse() throws IOException {
        long start = System.currentTimeMillis();

        FileReader file = new FileReader(uri);
        BufferedReader scanner = new BufferedReader(file);

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
                    Item item = new Item(candidate.get(0), candidate.get(1), candidate.get(2), candidate.get(3));
                    if(!cities.containsKey(item.getCity())) {
                        cities.put(item.getCity(), item);
                    }
                    itemsList.add(item);
                }
            }

        }
        Double current = (System.currentTimeMillis() - start) * 0.001;
        System.out.println(current);
        return itemsList;

    }

    public Map<String, Item> getCities() {
        return cities;
    };
}
