package com.goeuro.client;

import org.apache.commons.lang.StringUtils;

import com.goeuro.client.data.City;

/**
 * Utility POJO serialization class 
 */
public class CityCSVSerializer {
	
	// Stores city object as "_id, name, type, latitude, longitude"
	public static String serialize(City city) {
		String latitude = city.getGeo_position() != null ? String.valueOf(city.getGeo_position().getLatitude()) : "";
		String longitude = city.getGeo_position() != null ? String.valueOf(city.getGeo_position().getLongitude()) : "";

		String[] storedValues = new String[] { city.get_id(), city.getName(), city.getType(), latitude, longitude };
		return StringUtils.join(storedValues, ",");
	}

}
