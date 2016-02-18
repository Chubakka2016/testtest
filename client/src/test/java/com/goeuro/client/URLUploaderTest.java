package com.goeuro.client;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.ReaderInputStream;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import com.goeuro.client.data.City;

public class URLUploaderTest {

	@Test
	public void testJsonDeserializer() {
		String testJson = "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":448103,\"key\":null,\"name\":\"Berlingo\",\"fullName\":\"Berlingo, Italy\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Italy\",\"geo_position\":{\"latitude\":45.50298,\"longitude\":10.04366},\"locationId\":147721,\"inEurope\":true,\"countryCode\":\"IT\",\"coreCountry\":true,\"distance\":null},{\"_id\":425332,\"key\":null,\"name\":\"Berlingerode\",\"fullName\":\"Berlingerode, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":51.45775,\"longitude\":10.2384},\"locationId\":124675,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":425326,\"key\":null,\"name\":\"Bernau bei Berlin\",\"fullName\":\"Bernau bei Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.67982,\"longitude\":13.58708},\"locationId\":124669,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314826,\"key\":null,\"name\":\"Berlin Tegel\",\"fullName\":\"Berlin Tegel (TXL), Germany\",\"iata_airport_code\":\"TXL\",\"type\":\"airport\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.5548,\"longitude\":13.28903},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314827,\"key\":null,\"name\":\"Berlin Schönefeld\",\"fullName\":\"Berlin Schönefeld (SXF), Germany\",\"iata_airport_code\":\"SXF\",\"type\":\"airport\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.3887261,\"longitude\":13.5180874},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":334196,\"key\":null,\"name\":\"Berlin Hbf\",\"fullName\":\"Berlin Hbf, Germany\",\"iata_airport_code\":null,\"type\":\"station\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.525589,\"longitude\":13.369548},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":333977,\"key\":null,\"name\":\"Berlin Ostbahnhof\",\"fullName\":\"Berlin Ostbahnhof, Germany\",\"iata_airport_code\":null,\"type\":\"station\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.510972,\"longitude\":13.434567},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}]";
		InputStream input = new ReaderInputStream(new StringReader(testJson), "UTF-8");

		URLUploader uploader = new URLUploader("testhost", "testpath");

		List<City> cities = uploader.parseJson(new TypeReference<List<City>>() {
		}, input);
		Assert.assertEquals("Unexpected number of reuslts", 8, cities.size());
		Assert.assertEquals("Results order is not kept", "Berlin, Germany", cities.get(0).getFullName());
	}

	@Test
	public void testEmptyJsonDeserializer() {
		String testJson = "[]";
		InputStream input = new ReaderInputStream(new StringReader(testJson), "UTF-8");

		URLUploader uploader = new URLUploader("testhost", "testpath");
		List<City> cities = uploader.parseJson(new TypeReference<List<City>>() {
		}, input);
		Assert.assertEquals("Unexpected number of reuslts", 0, cities.size());
	}
}
