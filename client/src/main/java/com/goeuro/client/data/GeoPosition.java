package com.goeuro.client.data;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoPosition {
	@JsonProperty("longitude")
	protected double longitude;
	@JsonProperty("latitude")
	protected double latitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
