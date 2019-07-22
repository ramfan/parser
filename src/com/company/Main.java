package com.company;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    public static void main(String[] args)throws IOException, XMLStreamException {

	    String uri = args[0];
	    new ParseProcess(uri).startProcess();

    }
}
