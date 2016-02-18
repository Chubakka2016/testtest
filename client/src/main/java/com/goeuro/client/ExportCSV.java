package com.goeuro.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goeuro.client.data.City;

/**
 * Exports information about locations retrieved from api.goeuro.com in csv format,
 * including the following fields:
 * _id, name, type, latitude, longitude
 */
public class ExportCSV {

	public final static String API_HOST = "api.goeuro.com";
	public final static String API_PATH = "api/v2/position/suggest/en";

	private static final Logger log = LoggerFactory.getLogger(ExportCSV.class);

	public final static void main(String[] argv) {
		// using commons.cli or similar would be an overkill here
		if (argv.length < 1) {
			log.error("Usage: java -jar GoEuroTest.jar <CITY_NAME> [output.csv]");
			return;
		}

		String cityName = argv[0];

		String outputFile = "output.csv";
		if (argv.length > 1) {
			outputFile = argv[1];
		}

		URLUploader urlUploader = new URLUploader(API_HOST, API_PATH);

		System.out.println("Connecting to " + urlUploader.hostname);
		try {
			List<City> cities = urlUploader.readJsonObject(cityName, new TypeReference<List<City>>() {});
			if (cities != null) {
				storeCities(cities, outputFile);
			} else {
				log.error("Failed obtaining city information");
			}

		} catch (Exception ex) {
			log.error("Error obtaining city information", ex);
		}

	}

	private static void storeCities(List<City> cities, String outputFile) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
			for (City city : cities) {
				writer.println(CityCSVSerializer.serialize(city));
			}
		} catch (IOException ex) {
			log.error("Error storing cities data");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ex) {
					log.error("Error closing output file", ex);
				}
			}
		}

	}
}
