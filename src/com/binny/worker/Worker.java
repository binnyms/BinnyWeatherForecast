/**
 * 
 */
package com.binny.worker;

import java.util.HashMap;

import com.binny.data.WeatherHistory;

/**
 * Generic Worker interface
 * @author binny
 *
 */
public interface Worker {
	
	public WeatherHistory execute(HashMap dataMap);
	
}
