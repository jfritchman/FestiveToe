package models;

import play.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class WeatherDataXMLParser {
	public static List<WeatherData> parse(Document document) {
		List<WeatherData> weatherList = new ArrayList<WeatherData>();
		
		NodeList dataNodes = document.getElementsByTagName("data");
		for (int dataIdx = 0; dataIdx < dataNodes.getLength(); dataIdx++) {
			Element dataElement = (Element) dataNodes.item(dataIdx);
			NodeList weatherNodes = dataElement.getElementsByTagName("weather"); 
		    for (int weatherIdx = 0; weatherIdx < weatherNodes.getLength(); weatherIdx++) {
		    	Element weatherElement = (Element) weatherNodes.item(weatherIdx);
		    	WeatherData weather = new WeatherData();
		    	weather.date = getDateValue(weatherElement, "date");
		    	weather.highTemp = getDoubleValue(weatherElement, "tempMaxC");
		    	weather.lowTemp = getDoubleValue(weatherElement, "tempMinC");
		    	weather.precipitation = getDoubleValue(weatherElement, "precipMM");
		    	weather.weatherCode = getTextValue(weatherElement, "weatherCode");
		    	weather.weatherDescription = getTextValue(weatherElement, "weatherDesc");
		    	weather.weatherSymbolUrl = getTextValue(weatherElement, "weatherIconUrl");
		    	weather.windDirectionDegree = getIntValue(weatherElement, "winddirDegree");
		    	weather.windSpeed = getDoubleValue(weatherElement, "windspeedKmph");
		    	weatherList.add(weather);
		    }
		}
		
		return weatherList;
	}

	private static String getTextValue(Element element, String tagName) {
		String textVal = null;
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			Element e = (Element) list.item(0);
			textVal = e.getFirstChild().getNodeValue();
		}
		return textVal;
	}
	
	private static Date getDateValue(Element element, String tagName) {
		Date actualDate = new Date();
		try {
			actualDate = new SimpleDateFormat("yyyy-mm-dd").parse(getTextValue(element, tagName));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualDate;
	}
	
	private static double getDoubleValue(Element element, String tagName) {
		return Double.parseDouble(getTextValue(element, tagName));
	}
	
	private static int getIntValue(Element element, String tagName) {
		return Integer.parseInt(getTextValue(element, tagName));
	}
}
