package com.company;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static int MAX_SIZE = 2310991;
    public static int COUNT_CITIES = 50;

    public static void main(String[] args)throws IOException, XMLStreamException {

	    String uri = args[0];
	    new ParseProcess(uri).startProcess();

    }
}
