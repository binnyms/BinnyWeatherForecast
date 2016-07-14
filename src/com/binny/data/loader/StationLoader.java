package com.binny.data.loader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.binny.data.Station;

public class StationLoader {

	// private static StationLoader stationLoader;

	public static ArrayList<Station> stationList;

	public ArrayList<Station> getStationList() {
		return stationList;
	}

	private StationLoader() {
		loadStations();

	}

	public static StationLoader getInstance() {
		return new StationLoader();

	}

	public void loadStations() {
		JSONParser parser = new JSONParser();
		stationList = new ArrayList();
		try {

			JSONArray cityList = (JSONArray) parser.parse(new FileReader(
					"data/cities/cities.json"));
			Iterator iterator = (Iterator) cityList.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();

				Station station = new Station();
				station.setFullName((String) jsonObject.get("full_name"));
				station.setCity((String) jsonObject.get("city"));
				
				station.setCityCode((String) jsonObject.get("city_code"));

				station.setState((String) jsonObject.get("state"));
				station.setCountry((String) jsonObject.get("country"));

				String lat = (String) jsonObject.get("latitude");
				station.setLatitude(Float.parseFloat(lat));

				String longi = (String) jsonObject.get("longitude");
				station.setLongitude(Float.parseFloat(longi));

				String elev = (String) jsonObject.get("elevation");
				station.setElevation(Float.parseFloat(elev));

				station.setTimeZoneShort((String) jsonObject
						.get("local_tz_short"));

				station.setTimeZone((String) jsonObject.get("local_tz_long"));
				station.setTimeZoneOffset((String) jsonObject
						.get("local_tz_offset"));
				stationList.add(station);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
