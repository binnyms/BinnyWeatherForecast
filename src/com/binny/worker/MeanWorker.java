package com.binny.worker;

import com.binny.data.WeatherHistory;

/**
 * Class MeanWorker provides mean value for a set of values
 * 
 * @author binny
 *
 */
public class MeanWorker implements ForecastWorker {

	public MeanWorker() {

	}

	@Override
	public WeatherHistory forecast(WeatherHistory wh) {

		int meanTemp = (wh.getTempMax() + wh.getTempMin()) / 2;
		wh.setTempMean(meanTemp);
		int meanDevPt = (wh.getDewPointHigh() + wh.getDewPointMin()) / 2;
		wh.setDewPointMean(meanDevPt);

		int meanHum = (wh.getHumidityHigh() + wh.getHumidityMin()) / 2;
		wh.setHumiditytMean(meanHum);

		int meanPres = (wh.getPressureMax() + wh.getPressureMin()) / 2;
		wh.setPressureMean(meanPres);

		int meanVisib = (wh.getVisibilityMax() + wh.getVisibilityMin()) / 2;
		wh.setVisibilityMean(meanVisib);

		return wh;
	}

}
