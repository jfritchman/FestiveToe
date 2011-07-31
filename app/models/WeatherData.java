package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

/**
 * 
 * @author pshepley
 *
 * All temperature data is stored in celsius and converted to fahrenheit.
 * All speed data stored in kilometers per hour and converted to miles.
 * Precipitation is in millimeters
 */
public class WeatherData extends Model {

	public Date date;
	public double highTemp;
	public double lowTemp;
	public double windSpeed;
	public int windDirectionDegree;
	public String weatherCode;
	public String weatherSymbolUrl;
	public String weatherDescription;
	public double precipitation;
    
}
