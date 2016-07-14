/**
 * 
 */
package com.binny.startup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import com.binny.data.Station;
import com.binny.data.WeatherHistory;
import com.binny.data.finder.DataFinder;
import com.binny.data.loader.HistoryLoader;
import com.binny.data.loader.StationLoader;
import com.binny.worker.ForecastWorker;
import com.binny.worker.Worker;
import com.binny.worker.WorkerFactory;

/**
 * Class WeatherForeCast to give weather data for a given date.
 * 
 * @author Binny
 * 
 */
public class WeatherForeCast {

	public static ArrayList<Station> stationList;

	public static HashMap<String, HashMap> historyDataMap;

	public static String[] workerList = { "KMEANS" };

	public static String[] forecastWorkerList = { "MEAN", "TIMEOFDAY",
			"GLOBALWARMING", "OCEAN", "EVENT" };

	public static ArrayList<Station> getStationList() {
		return stationList;
	}

	public static void setStationList(ArrayList<Station> stationList) {
		WeatherForeCast.stationList = stationList;
	}

	public static HashMap<String, HashMap> getHistoryDataMap() {
		return historyDataMap;
	}

	public static void setHistoryDataMap(HashMap<String, HashMap> historyDataMap) {
		WeatherForeCast.historyDataMap = historyDataMap;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out
					.println("Usage: java WeatherForeCast <date in YYYY-MM-DD> . Eg java WeatherForeCast 2016-10-25");
			return;
		}

		String dateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		Date dateTmp;
		try {
			dateTmp = sdf.parse(args[0]);
			dateStr = sdf.format(dateTmp);
			System.out.println("Date : " + dateStr);
		} catch (ParseException e) {
			System.out
					.println("Usage: java WeatherForeCast <date in YYYY-MM-DD> . Eg java WeatherForeCast 2016-10-25");
			return;
		}

		// String dateStr = "2016-6-1";
		Date dt = new Date();
		// String myDateString = dateStr + " " + dt.getHours() + ":" +
		// dt.getMinutes() + ":" + dt.getSeconds();

		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		String[] y1 = dateStr.split("-");
		cal.set(convertToInt(y1[0]), convertToInt(y1[1]), convertToInt(y1[2]));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// System.out.println("DATA" + myDateString);

		// Load City list
		setStationList(StationLoader.getInstance().getStationList());

		// Load weather data from files
		setHistoryDataMap(HistoryLoader.getInstance().getCityFileDataMap());

		System.out.println("Weather Data ...");
		System.out.println("===============================================================================");

		Iterator iterator = (Iterator) stationList.iterator();
		while (iterator.hasNext()) {
			Station station = (Station) iterator.next();
			// System.out.println("City : " + station.getCity());

			DataFinder finder = new DataFinder();
			HashMap cityDataMap = finder.findData(station.getCity()
					.toLowerCase(), dateStr, getHistoryDataMap());


			// Execute workers on the Weather data for the city for the given date for all years
			WeatherHistory weatherFC = null;
			WorkerFactory workerFactory = new WorkerFactory();

			for (int x = 0; x < workerList.length; x++) {
				Worker worker = workerFactory.getWorker(workerList[x]);
				weatherFC = worker.execute(cityDataMap);

			}

			// Change timezone for each station
			try {

				SimpleDateFormat sdfTz = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				TimeZone tz = TimeZone.getTimeZone(station.getTimeZone());
				sdfTz.setTimeZone(tz);
				String tzDateStr = sdfTz.format(cal.getTime()); // Convert to
																// String first
				Date tzDate = formatter.parse(tzDateStr);
				weatherFC.setReadingDate(tzDate);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Run forecast workers to calculate values
			for (int x = 0; x < forecastWorkerList.length; x++) {
				ForecastWorker worker = workerFactory
						.getForecastWorker(forecastWorkerList[x]);
				weatherFC = worker.forecast(weatherFC);

			}

			System.out.println(station.getCityCode() + "|"
					+ station.getLatitude() + "," + station.getLongitude()
					+ "|" + formatter.format(weatherFC.getReadingDate()) + "|"
					+ weatherFC.getEvent() + "|" + weatherFC.getTempMean()
					+ "|" + weatherFC.getPressureMean() + "|"
					+ weatherFC.getHumiditytMean()

			);
		}

	}

	private static int convertToInt(String st) {
		int num = 0;
		try {
			if (st != null && !st.isEmpty())
				num = Integer.parseInt(st);
		} catch (NumberFormatException nfe) {
			// nfe.printStackTrace();
		}
		return num;
	}
}
