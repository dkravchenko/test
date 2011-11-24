package net.sam_solutions.courses.dkrauchanka.framework;

import java.util.Map;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ControllerManager {
	private static ControllerManager instance;
	private Map<String> map; 
	
	private class SAXHandler extends DefaultHandler{
		private ArrayList<String> controllers;
		private boolean isControllerTag = false;
		
		public String[] getControllers()
		{
		    return controllers.toArray(new String[controllers.size()]);
		}
		@Override
		public void startDocument() throws SAXException
		{
		  controllers = new ArrayList<String>();
		}
		  @Override
		  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
		  {
		    isControllerTag = "controller".equals(qName);
		  }
		  @Override
		  public void characters(char[] ch, int start, int length) throws SAXException
		  {
		    if(isControllerTag)
		      users.add(new String(ch, start, length));
		  }
		  @Override
		  public void endElement(String uri, String localName, String qName) throws SAXException
		  {
		    isControllerTag = false;
		  }
		  @Override
		  public void endDocument() throws SAXException
		  {
		  }
	}
	
    private ControllerManager() {
    	this.map = new HashMap<Class>();
    	SAXParserFactory factory = SAXParserFactory.newInstance();
    	SAXParser parser = factory.newSAXParser();
    	SAXHandler handler = new SAXHandler();
    	parser.parse(new File("controllers.xml"), handler);
    	
    }

    public static synchronized ControllerManager getInstance() {
            if (null == instance) {
                    instance = new ControllerManager();
            }
            return instance;
    }
}
