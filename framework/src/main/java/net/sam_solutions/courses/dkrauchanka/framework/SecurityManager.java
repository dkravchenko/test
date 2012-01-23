package net.sam_solutions.courses.dkrauchanka.framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//public class SecurityManager {
//    private static final Logger log = Logger.getLogger(SecurityManager.class);
//    private static SecurityManager instance;
//    private Map<String,String> map;
//    
//    private class SAXHandler extends DefaultHandler{
//        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
//	{
//            if("security-element".equals(qName)){
//                map.put(attributes.getValue("name"),attributes.getValue("userRole"));
//            }
//	}
//    }
//    
//    private SecurityManager(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
//        map = new HashMap<String,String>();
//        SAXParserFactory factory = SAXParserFactory.newInstance();
//    	SAXParser parser = factory.newSAXParser();
//    	SAXHandler handler = new SAXHandler();
//    	parser.parse(new File(path), handler);
//    }
//    
//    public boolean isAccessable(String name, String role){
//        Pattern p = Pattern.compile("[\\w\\W]*"+role+"[\\w\\W]*");
//        Matcher m = p.matcher(map.get(name));
//        return m.matches();
//    }
//    
//    public static synchronized SecurityManager getInstance(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
//        if(instance == null)
//            instance = new SecurityManager(path);
//        return instance;
//    }
//}
public class SecurityManager {
    private static final Logger log = Logger.getLogger(SecurityManager.class);
    private static SecurityManager instance;
    private Map<String,String> map;
    
    private class SAXHandler extends DefaultHandler{
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
            if("security-element".equals(qName)){
                map.put(attributes.getValue("name"),attributes.getValue("userRole"));
            }
	}
    }
    
    public SecurityManager(String path) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
        map = new HashMap<String,String>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
    	SAXParser parser = factory.newSAXParser();
    	SAXHandler handler = new SAXHandler();
    	parser.parse(this.getClass().getClassLoader().getResource(path).getFile(), handler);
    }
    
    public boolean isAccessable(String name, String role){
        Pattern p = Pattern.compile("[\\w\\W]*"+role+"[\\w\\W]*");
        Matcher m = p.matcher(map.get(name));
        return m.matches();
    }
}