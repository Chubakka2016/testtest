package com.goeuro.client;

import org.junit.Assert;
import org.junit.Test;

import com.goeuro.client.data.City;
import com.goeuro.client.data.GeoPosition;

public class CityCSVSerializerTest {
	@Test
	public void testCSVSerializer() {
		City city = new City();
		city.set_id("111");
		city.setCoreCountry(true);
		city.setName("Berlin");
		city.setFullName("Berlin, Germany");
		city.setType("location");
		city.setGeo_position(null);

		String serializedCity = CityCSVSerializer.serialize(city);
		Assert.assertEquals("Incorrect csv serialization", "111,Berlin,location,,", serializedCity);

		GeoPosition geoPosition = new GeoPosition();
		geoPosition.setLatitude(52);
		geoPosition.setLongitude(13);

		city.setGeo_position(geoPosition);
		String serializedCityWPos = CityCSVSerializer.serialize(city);
		Assert.assertEquals("Incorrect csv serialization", "111,Berlin,location,52.0,13.0", serializedCityWPos);
	}
}
