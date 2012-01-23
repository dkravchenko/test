package net.sam_solutions.courses.dkrauchanka.framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//public class ControllerManager {
//	private static ControllerManager instance;
//	private Map<String,String> map; 
//	
//	private class SAXHandler extends DefaultHandler{
//		 @Override
//		  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
//		  {
//		    if("controller".equals(qName)){
//		    	map.put(attributes.getValue("name"),attributes.getValue("className"));
//		    }
//		  }
//	}
//	
//	private void init(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
//		this.map = new HashMap<String,String>();
//    	SAXParserFactory factory = SAXParserFactory.newInstance();
//    	SAXParser parser = factory.newSAXParser();
//    	SAXHandler handler = new SAXHandler();
//    	parser.parse(new File(path), handler);
//	}
//	
//    private ControllerManager(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
//    	this.init(path);
//    }
//    
//    public String getController(String name){
//    	return map.get(name);
//    }
//
//    public static synchronized ControllerManager getInstance(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
//            if (null == instance) {
//                    instance = new ControllerManager(path);
//            }
//            return instance;
//    }
//}
public class ControllerManager {
    private static final Logger log = Logger.getLogger(MainServlet.class);
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
	
	private void init(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
		this.map = new HashMap<String,String>();
    	SAXParserFactory factory = SAXParserFactory.newInstance();
    	SAXParser parser = factory.newSAXParser();
    	SAXHandler handler = new SAXHandler();
        log.info(this.getClass().getClassLoader().getResource(path).getPath());
    	parser.parse(this.getClass().getClassLoader().getResource(path).getFile(), handler);
	}
	
    public ControllerManager(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
    	this.init(path);
    }
    
    public String getController(String name){
    	return map.get(name);
    }
}
