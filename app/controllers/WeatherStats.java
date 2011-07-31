package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import models.WeatherData;
import models.WeatherDataXMLParser;
import models.WorldWeatherOnlineConnection;
import models.WorldWeatherOnlineConnection.ResponseFormats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import play.Logger;
import play.mvc.*;

public class WeatherStats extends Controller {

    public static void index() {
        render();
    }
    
    public static void byzip(int zipcode) throws ParserConfigurationException
    {    	
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();

        WorldWeatherOnlineConnection connection = new WorldWeatherOnlineConnection();
        connection.setNumberOfDays(5);
        connection.setZipCode(Integer.toString(zipcode));
        connection.setResponseFormat(ResponseFormats.rfXml);
        
        List<WeatherData> weatherList = null;
    	
        try {        	
        	URL url = new URL(connection.getConnectionString());
        	
			Document dom = builder.parse(url.openStream());
						
			weatherList = WeatherDataXMLParser.parse(dom);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.error(e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			Logger.error(e.getMessage());
		}
    	
    	render(weatherList);
    }

}
