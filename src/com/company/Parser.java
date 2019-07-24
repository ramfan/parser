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
        long start = System.currentTimeMillis();

        FileReader file = new FileReader(uri);
        Scanner scanner = new Scanner(file);

        List<Item> itemsList = new ArrayList<>();

        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> candidate = new ArrayList<>();
            if(!line.equals("<root>") && !line.equals("<root/>") ) {
                line += " ";
                char[] charArr = line.toCharArray();

                for (int i = 10; i < charArr.length; i++) {
                    int count = 0;
                    if( charArr[i] != '/' &&  charArr[i] != '>' && charArr[i] != '=' && (int)charArr[i] > 255 || (int)charArr[i] == ' ' ){
                        if(charArr[i] == '"'){
                            count++;
                        }

                        if(count % 2 == 0) {
                            System.out.println(charArr[i]);
                        }

                    }

                }
                break;
            }




        }
        Double current = (System.currentTimeMillis() - start) * 0.001;
        System.out.println(current);
        return itemsList;

    }
}
