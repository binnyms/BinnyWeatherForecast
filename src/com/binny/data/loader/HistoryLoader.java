package com.binny.data.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.binny.data.WeatherHistory;

/**
 * Class HistoryLoader loads the Hisory data from all files into hashmaps
 * 
 * @author Binny
 * 
 */
public class HistoryLoader {


	// Data will be loaded only once . Static HashMaps used to load City Data
	// and weather history data
	public static HashMap<String, ArrayList> weatherHistoryMap;
	public static HashMap<String, HashMap> cityFileDataMap;

	//Load all data on startup
	private HistoryLoader() {
		loadHistoryFiles();
	}

	// Static getter for weatherHistoryMap
	public static HashMap<String, ArrayList> getWeatherHistoryMap() {
		return weatherHistoryMap;
	}

	// Static getter for Instance
	public static HistoryLoader getInstance() {
		return new HistoryLoader();

	}

	/**
	 * 
	 * Method loadHistoryFiles reads the history files and loads to a hashmap
	 */
	public void loadHistoryFiles() {
		cityFileDataMap = new HashMap<String, HashMap>();
		String[] cities = { "Sydney", "Perth", "Adelaide", "Brisbane",
				"Melbourne" };
		File file = new File("resources/history");
		
		String[] files = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isFile();
			}
		});
		// System.out.println(Arrays.toString(files));

		for (int i = 0; i < cities.length; i++) {

			String city = (String) cities[i];
			ArrayList<String> cityFiles = new ArrayList();
			//System.out.println("cityFiles: " + cityFiles.toString());
			
			for (int j = 0; j < files.length; j++) {
				String fileName = (String) files[j];
				if (fileName.contains(city.toLowerCase())) {
					cityFiles.add(fileName);
				}

			}
			// System.out.println("City :" + city);
			// System.out.println("Files :" + cityFiles.toString());

			Iterator iter = cityFiles.iterator();
			// Use later
			// ArrayList historyList = new ArrayList<WeatherHistory>();

			HashMap yearMap = new HashMap<String, ArrayList>();
			while (iter.hasNext()) {
				// WeatherHistory wh= new WeatherHistory();
				String fileName = (String) iter.next();
				String[] y1 = fileName.split("_");
				String[] y2 = y1[1].split(Pattern.quote("."));
				String yearStr = y2[0];

				HashMap fileContents = parseFile(city, fileName);
				yearMap.put(yearStr, fileContents);
			}
			cityFileDataMap.put(city.toLowerCase(), yearMap);

		}

	}

	public static HashMap<String, HashMap> getCityFileDataMap() {
		return cityFileDataMap;
	}

	/**
	 * Method parseFile parses the entire data in file an loads a HashMap. One file contains one years data- one day per row
	 * 
	 * @param city
	 * @param fileName
	 * @return
	 */
	public HashMap parseFile(String city, String fileName) {

		String histFile = "resources/history/" + fileName;
		
		BufferedReader br = null;
		String line = "";
		String splitter = ",";

		// ArrayList historyFileList = new ArrayList<WeatherHistory>();
		HashMap historyFileMap = new HashMap<String, WeatherHistory>();
		int i = 0;
		try {

			br = new BufferedReader(new FileReader(histFile));
			while ((line = br.readLine()) != null) {

				if (i > 0) {
					String[] historyLine = line.split(splitter);
					WeatherHistory lineHist = parseLine(city, historyLine);
					historyFileMap.put(lineHist.getReadingDateStr(), lineHist);
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return historyFileMap;

	}

	/**
	 * Method parseLine parses one line of data from the file corresponding to a day
	 * 
	 * @param city
	 * @param line
	 * @return
	 */
	public WeatherHistory parseLine(String city, String[] line) {
		WeatherHistory wh = new WeatherHistory();
		wh.setCity(city);
		wh.setReadingDateStr(line[0]);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = df.parse(line[0]);
		} catch (ParseException e) {
			wh.setReadingDateStr(line[0]);
			System.out.println("++++++++++++ "+ city + wh.getReadingDateStr());
			e.printStackTrace();
		}
		wh.setReadingDate(dt);

		wh.setTempMax(convertToInt(line[1]));
		wh.setTempMean(convertToInt(line[2]));
		wh.setTempMin(convertToInt(line[3]));

		wh.setDewPointHigh(convertToInt(line[4]));
		wh.setDewPointMean(convertToInt(line[5]));
		wh.setDewPointMin(convertToInt(line[6]));

		wh.setHumidityHigh(convertToInt(line[7]));
		wh.setHumiditytMean(convertToInt(line[8]));
		wh.setHumidityMin(convertToInt(line[9]));

		wh.setPressureMax(convertToInt(line[10]));
		wh.setPressureMean(convertToInt(line[11]));
		wh.setPressureMin(convertToInt(line[12]));

		wh.setVisibilityMax(convertToInt(line[13]));
		wh.setVisibilityMean(convertToInt(line[14]));
		wh.setVisibilityMin(convertToInt(line[15]));

		wh.setWindSpeedMax(convertToInt(line[16]));
		wh.setWindSpeedMean(convertToInt(line[17]));
		wh.setGustSpeedMax(convertToInt(line[18]));

		wh.setPrecipitation(convertToFloat(line[19]));
		wh.setCloudCover(convertToInt(line[20]));

		wh.setEvent(line[21]);
		wh.setWindDirection(convertToInt(line[22]));
		return wh;
	}

	/*
	 * Utility method to covert String to int 
	 * @param st
	 * @return
	 */
	private int convertToInt(String st) {
		int num = 0;
		try {
			if (st != null && !st.isEmpty())
				num = Integer.parseInt(st);
		} catch (NumberFormatException nfe) {
			// nfe.printStackTrace();
		}
		return num;
	}

	/*
	 * Utility method to covert String to float 
	 * @param st
	 * @return
	 */
	private float convertToFloat(String st) {
		float num = 0;
		try {
			if (st != null && !st.isEmpty())
				num = Float.parseFloat(st);
		} catch (NumberFormatException nfe) {
			// nfe.printStackTrace();
		}
		return num;
	}
}
