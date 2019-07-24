package com.company;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Pattern pattern = Pattern.compile("\"([^\"]+)\"");

    public List<Item> parse(String uri) throws FileNotFoundException {
        FileReader file = new FileReader(uri);
        Scanner scanner = new Scanner(file);
        List<String> candidate = new ArrayList<>();
        List<Item> itemsList = new ArrayList<>();

        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(!line.equals("<root>") && !line.equals("<root/>") ) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    candidate.add(matcher.group().replace("\"",""));
                }

                Item item = new Item(candidate.get(0), candidate.get(1), candidate.get(2), candidate.get(3));
                itemsList.add(item);
            }




        }
        return itemsList;
    }
}
