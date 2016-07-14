/**
 * 
 */
package com.binny.worker;

import java.util.HashMap;

import com.binny.data.WeatherHistory;

/**
 * Interface for ForecastWorkers
 * 
 * @author binny
 *
 */
public interface ForecastWorker {
	
	public WeatherHistory forecast(WeatherHistory wh);
	
}
