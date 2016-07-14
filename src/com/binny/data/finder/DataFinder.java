/**
 * 
 */
package com.binny.data.finder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.binny.data.WeatherHistory;

/**
 * Class DataFinder is used to search and find weather data on the same date 
 * for all years for a particular station (city)
 * 
 * @author binny
 *
 */
public class DataFinder {

	/**
	 * Default COnstructor
	 */
	public DataFinder() {
		
	}

	/*
	 * Method findData finds data for a city for a date for all years
	 * 
	 */
	public HashMap findData (String city, String dateStr, HashMap histoyDataMap)
	{
		HashMap <String , WeatherHistory> dMap = new HashMap();
		
		//Get the hashmap for city
		HashMap cityMap =  (HashMap) histoyDataMap.get(city);
		Set yearSet =  (Set)cityMap.keySet();
		Iterator iter = yearSet.iterator();
		while(iter.hasNext())
		{
			String yearStr = (String)iter.next();
			//System.out.println("Year : "+ yearStr);
			// Get hashmap for the year
			HashMap yearMap = (HashMap) cityMap.get(yearStr);
			
			String[] y1 = dateStr.split("-");
			String searchDateStr = yearStr + "-" +  y1[1] + "-" + y1[2];
			
			//Get weather history for each year
			WeatherHistory dateHistory = (WeatherHistory) yearMap.get(searchDateStr.trim());
			dMap.put(searchDateStr, dateHistory);

		}
		return dMap;
		
	}
	
}
