package models;

import play.*;
import java.util.*;

public class WorldWeatherOnlineConnection {
	
	// base url for the free weather service
	private final String baseurl = "http://free.worldweatheronline.com/feed/weather.ashx";
	// this key is registered to Peter Shepley, please see readme for details
	private final String key = "a9b1a3a8fd180134113007";
	
	// used to build the query string
	private String query = "";
	
	private HashMap<String, String> parameters;
	
	public WorldWeatherOnlineConnection() {
		parameters = new HashMap<String, String>();
		parameters.put("key", key);
	}
	
	public String getConnectionString() {
		generateQuery();
		return baseurl + "?" + query;
	}

	// set q= zipcode in query string
	private String zipcode = "";
	public void setZipCode(String value) {
		zipcode = value;
	}
	
	// XXX: city and state not supported, look into what needs to be done
	private String city = "";
	private String state = "";
	
	// XXX: IP Address is also not supported, but will work with the service's API
	private String IPAddress = "";
	
	// XXX: Lat and Long are also supported in the API, but not in this class
	private String Latitude = "";
	private String Longitude = "";

	// utility function for translation
	private String boolToYesNo(boolean value) {
		return value ? "yes" : "no";
	}
	
	// generate the query string from all the parameters, set query
	private void generateQuery() {
		query = "";
		for (String mapKey : parameters.keySet()) {
			query += mapKey + "=" + parameters.get(mapKey) + "&";
		}		
		// get the query from zipcode or city, state or IP or lat, long
		query += "q=" + zipcode;
	}

	// localObsTime (opt)
	public void setLocalTime(String value) {
		parameters.put("localObsTime", value);
	}
	// isDayTime (opt)
	public void setDayTime(boolean value) {
		parameters.put("isDayTime", boolToYesNo(value));
	}
	// utcDateTime (opt)
	public void setUtcDateTime(String value) {
		parameters.put("utcDateTime", value);
	}
	// num_of_days
	public void setNumberOfDays(int value) {
		parameters.put("num_of_days", Integer.toString(value));
	}
	// date (opt)
	public void setDate(String value) {
		parameters.put("date", value);
	}
	// fx (opt)
	public void setNormalOutput(boolean value) {
		parameters.put("fx", boolToYesNo(value));
	}
	// cc (opt)
	public void setCurrentConditions(boolean value) {
		parameters.put("cc", boolToYesNo(value));
	}
	// includeLocation (opt)
	public void setIncludeLocation(boolean value) {
		parameters.put("includeLocation", boolToYesNo(value));
	}

	public enum ResponseFormats { rfXml, rfJson, rfCsv }
	// format
	public void setResponseFormat(ResponseFormats value) {
		switch (value) {
			case rfXml:
				parameters.put("format", "xml");
				break;
			case rfJson:
				parameters.put("format", "json");
				break;
			case rfCsv:
				parameters.put("format", "csv");
				break;
		}
	}

	// show_comments (opt)
	public void setShowComments(boolean value) {
		parameters.put("show_comments", boolToYesNo(value));
	}
	// callback (opt)
	public void setCallbackName(String value) {
		parameters.put("callback", value);
	}
	    
}
