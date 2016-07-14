package com.binny.worker;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.binny.data.WeatherHistory;

/**
 * Class TimeOfDayWorker adjusts readings as per time of Day
 * 
 * @author binny
 *
 */
public class TimeOfDayWorker implements ForecastWorker {

	public TimeOfDayWorker() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public WeatherHistory forecast(WeatherHistory wh) {

		Date dt = wh.getReadingDate();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		String sDate = sdf.format(dt); // Convert to String first
		String[] hourMin = sDate.split(":");
		int hour = convertToInt(hourMin[0]);
		int min = convertToInt(hourMin[1]);

		int timeInMin = (hour * 60) + min;

		float tempDiff = (wh.getTempMax() - wh.getTempMin()) / 12;

		float pressDiff = (wh.getPressureMax() - wh.getPressureMin()) / 4;

		float temp = 0;
		float pres = 0;

		if (hour <= 6) {
			temp = wh.getTempMin();
			pres = wh.getPressureMax();
		} else if (hour >= 6 && hour < 8) {
			temp = wh.getTempMin() + 4 * tempDiff;
			pres = wh.getPressureMax();
		} else if (hour >= 8 && hour < 10) {
			temp = wh.getTempMin() + (8 * tempDiff);
			pres = wh.getPressureMax() - pressDiff;
		} else if (hour >= 10 && hour < 12) {
			temp = wh.getTempMin() + (10 * tempDiff);
			pres = wh.getPressureMax() - pressDiff;
		} else if (hour >= 12 && hour < 14) {
			temp = wh.getTempMin() + (12 * tempDiff);
			pres = wh.getPressureMax() - pressDiff;
		} else if (hour >= 14 && hour < 16) {
			temp = wh.getTempMin() + (10 * tempDiff);
			pres = wh.getPressureMax() - (pressDiff * 2);
		} else if (hour >= 16 && hour < 18) {
			temp = wh.getTempMin() + (8 * tempDiff);
			pres = wh.getPressureMax() - (pressDiff * 2);
		} else if (hour >= 18 && hour < 20) {
			temp = wh.getTempMin() + 4 * tempDiff;
			pres = wh.getPressureMax() - (pressDiff * 3);
		} else if (hour >= 20) {
			temp = wh.getTempMin();
			pres = wh.getPressureMax() - (pressDiff * 4);
		}

		int temp2 = Math.round(temp);
		wh.setTempMean(temp2);

		int pres2 = Math.round(pres);
		wh.setPressureMean(pres2);
		// System.out.println("Date :" + dt.toString());
		// System.out.println("sDate :" + sDate);
		return wh;
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
