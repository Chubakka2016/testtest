package com.goeuro.client.data;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

// Using protobuf is definitely an option here
public class City {
	@JsonProperty("_id")
	protected String _id;
	@JsonProperty("key")
	protected String key;
	@JsonProperty("name")
	protected String name;
	@JsonProperty("fullName")
	protected String fullName;
	@JsonProperty("iata_airport_code")
	protected String iata_airport_code;
	@JsonProperty("type")
	protected String type;
	@JsonProperty("country")
	protected String country;
	@JsonProperty("geo_position")
	protected GeoPosition geo_position;

	@JsonProperty("location_id")
	protected String location_id;
	@JsonProperty("inEurope")
	protected boolean inEurope;
	@JsonProperty("countryCode")
	protected String countryCode;
	@JsonProperty("coreCountry")
	protected boolean coreCountry;
	@JsonProperty("distance")
	protected Double distance;

	private Map<String, Object> otherProperties = new HashMap<String, Object>();

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIata_airport_code() {
		return iata_airport_code;
	}

	public void setIata_airport_code(String iata_airport_code) {
		this.iata_airport_code = iata_airport_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public GeoPosition getGeo_position() {
		return geo_position;
	}

	public void setGeo_position(GeoPosition geo_position) {
		this.geo_position = geo_position;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public boolean isInEurope() {
		return inEurope;
	}

	public void setInEurope(boolean inEurope) {
		this.inEurope = inEurope;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isCoreCountry() {
		return coreCountry;
	}

	public void setCoreCountry(boolean coreCountry) {
		this.coreCountry = coreCountry;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
