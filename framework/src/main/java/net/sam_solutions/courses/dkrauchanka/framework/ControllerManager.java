package net.sam_solutions.courses.dkrauchanka.framework;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

public class ControllerManager {
	private static ControllerManager instance;
	private Map<String,String> map; 
	
	private class SAXHandler extends DefaultHandler{
		 @Override
		  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
		  {
		    if("controller".equals(qName)){
		    	map.put(attributes.getValue("name"),attributes.getValue("className"));
		    }
		  }
	}
	
	private void init() throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
		this.map = new HashMap<String,String>();
    	SAXParserFactory factory = SAXParserFactory.newInstance();
    	SAXParser parser = factory.newSAXParser();
    	SAXHandler handler = new SAXHandler();
    	parser.parse(new File("controllers.xml"), handler);
	}
	
    private ControllerManager() throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
    	this.init();
    }
    
    public String getController(String name){
    	return map.get(name);
    }

    public static synchronized ControllerManager getInstance() throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
            if (null == instance) {
                    instance = new ControllerManager();
            }
            return instance;
    }
}
