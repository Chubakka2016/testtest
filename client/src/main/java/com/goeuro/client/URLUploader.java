package com.goeuro.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class URLUploader {
	protected final String hostname;
	protected final String path;
	protected final String protocol = "http";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public final static int RETRIES = 3;

	private static final Logger log = LoggerFactory.getLogger(URLUploader.class);

	/**
	 * Json uploader from api with specified host and URL path
	 * @param hostname requested hostname
	 * @param path API path
	 */
	public URLUploader(String hostname, String path) {
		super();
		this.hostname = hostname;
		this.path = path;
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
		objectMapper.configure(Feature.AUTO_DETECT_SETTERS, false);

	}

	public InputStream getURLContent(final String value) throws MalformedURLException {
		URL apiUrl = new URL(protocol + "://" + hostname + "/" + path + "/" + value);
		int attempts = 0;
		do {
			attempts++;
			try {
				URLConnection conn = apiUrl.openConnection();				

				if (!(conn instanceof HttpURLConnection)) {
					log.error("URL: " + conn + " is not a http url!");
					return null;
				}

				final HttpURLConnection httpConn = (HttpURLConnection) conn;

				httpConn.setInstanceFollowRedirects(false);
				int code;
				try {
					code = httpConn.getResponseCode();
				} catch (final IOException e) {
					log.error("IOException during retrieving http response code: " + e.getMessage());
					continue;
				}

				if (code != HttpURLConnection.HTTP_OK) {
					log.error("Http response code: " + code);
					continue;
				}

				try {
					InputStream is = conn.getInputStream();
					return is;
				} catch (IOException ex) {
					log.error("Error uploading resource");
				}
			} catch (IOException ex) {
				log.error("Error opening connection", ex);
			}

		} while (attempts < RETRIES);

		log.error("Unable to retrieve data from " + apiUrl);
		return null;
	}

	public <T> T readJsonObject(final String value, final TypeReference<T> type) throws MalformedURLException {
		InputStream input = getURLContent(value);
		return parseJson(type, input);
	}

	protected <T> T parseJson(final TypeReference<T> type, final InputStream input) {
		if (input != null) {
			try {
				T result = this.objectMapper.readValue(input, type);
				return result;
			} catch (IOException e) {
				log.error("Failed to parse json object to type " + type, e);
				return null;
			} finally {
				try {
					input.close();
				} catch (Exception ex) {
					log.error("Error closing URL stream:", ex);
				}
			}
		} else {
			return null;
		}
	}
}
