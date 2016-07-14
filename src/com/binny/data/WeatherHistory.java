package com.binny.data;

import java.util.Date;

/**
 * Class WeatherHistory is a data object to weather history details. 
 * The same data object is used for forecast data also
 * 
 * @author Binny
 * 
 */

public class WeatherHistory {

	private String city;
	
	private Date readingDate;
	private String readingDateStr;
	
	private int tempMax;
	private int tempMean;
	private int tempMin;
	
	private int dewPointHigh;
	private int dewPointMean;
	private int dewPointMin;
	
	private int humidityHigh;
	private int humiditytMean;
	private int humidityMin;
	
	private int pressureMax;
	private int pressureMean;
	private int pressureMin;
	
	private int visibilityMax;
	private int visibilityMean;
	private int visibilityMin;
	
	private int windSpeedMax;
	private int windSpeedMean;
	private int gustSpeedMax;
	
	private float precipitation;
	private int cloudCover;
	
	public String getReadingDateStr() {
		return readingDateStr;
	}
	public void setReadingDateStr(String readingDateStr) {
		this.readingDateStr = readingDateStr;
	}
	private String event;
	private int windDirection; // In degrees
	
	
	public int getHumidityHigh() {
		return humidityHigh;
	}
	public void setHumidityHigh(int humidityHigh) {
		this.humidityHigh = humidityHigh;
	}
	public int getHumiditytMean() {
		return humiditytMean;
	}
	public void setHumiditytMean(int humiditytMean) {
		this.humiditytMean = humiditytMean;
	}
	public int getHumidityMin() {
		return humidityMin;
	}
	public void setHumidityMin(int humidityMin) {
		this.humidityMin = humidityMin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getTempMax() {
		return tempMax;
	}
	public void setTempMax(int tempMax) {
		this.tempMax = tempMax;
	}
	public int getTempMean() {
		return tempMean;
	}
	public void setTempMean(int tempMean) {
		this.tempMean = tempMean;
	}
	public int getTempMin() {
		return tempMin;
	}
	public void setTempMin(int tempMin) {
		this.tempMin = tempMin;
	}
	public int getDewPointHigh() {
		return dewPointHigh;
	}
	public void setDewPointHigh(int dewPointHigh) {
		this.dewPointHigh = dewPointHigh;
	}
	public int getDewPointMean() {
		return dewPointMean;
	}
	public void setDewPointMean(int dewPointMean) {
		this.dewPointMean = dewPointMean;
	}
	public int getDewPointMin() {
		return dewPointMin;
	}
	public void setDewPointMin(int dewPointMin) {
		this.dewPointMin = dewPointMin;
	}
	public int getPressureMax() {
		return pressureMax;
	}
	public void setPressureMax(int pressureMax) {
		this.pressureMax = pressureMax;
	}
	public int getPressureMean() {
		return pressureMean;
	}
	public void setPressureMean(int pressureMean) {
		this.pressureMean = pressureMean;
	}
	public int getPressureMin() {
		return pressureMin;
	}
	public void setPressureMin(int pressureMin) {
		this.pressureMin = pressureMin;
	}
	public int getVisibilityMax() {
		return visibilityMax;
	}
	public void setVisibilityMax(int visibilityMax) {
		this.visibilityMax = visibilityMax;
	}
	public int getVisibilityMean() {
		return visibilityMean;
	}
	public void setVisibilityMean(int visibilityMean) {
		this.visibilityMean = visibilityMean;
	}
	public int getVisibilityMin() {
		return visibilityMin;
	}
	public void setVisibilityMin(int visibilityMin) {
		this.visibilityMin = visibilityMin;
	}
	public int getWindSpeedMax() {
		return windSpeedMax;
	}
	public void setWindSpeedMax(int windSpeedMax) {
		this.windSpeedMax = windSpeedMax;
	}
	public int getWindSpeedMean() {
		return windSpeedMean;
	}
	public void setWindSpeedMean(int windSpeedMean) {
		this.windSpeedMean = windSpeedMean;
	}
	public int getGustSpeedMax() {
		return gustSpeedMax;
	}
	public void setGustSpeedMax(int gustSpeedMax) {
		this.gustSpeedMax = gustSpeedMax;
	}
	public float getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(float precipitation) {
		this.precipitation = precipitation;
	}
	public int getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(int cloudCover) {
		this.cloudCover = cloudCover;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(int windDirection) {
		this.windDirection = windDirection;
	}
	public Date getReadingDate() {
		return readingDate;
	}
	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}
	
	
}
