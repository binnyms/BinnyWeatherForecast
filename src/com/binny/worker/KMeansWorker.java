package com.binny.worker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.binny.data.WeatherHistory;
import com.binny.util.KMeans;

/**
 * Class KMeansWorker to calculate KMean values
 * 
 * @author binny
 * 
 */
public class KMeansWorker implements Worker {

	private int NUM_CLUSTERS; // Total clusters.
	private int TOTAL_DATA; // Total data points.

	public KMeansWorker() {
	}

	/**
	 * Method execute to calculate Kmean for each item
	 * 
	 */
	@Override
	public WeatherHistory execute(HashMap dataMap) {
		// System.out.println("In KMeans Worker");

		WeatherHistory wfcast = new WeatherHistory();
		NUM_CLUSTERS = 2;
		TOTAL_DATA = dataMap.size();
		double SAMPLES_TEMP[][] = new double[TOTAL_DATA][2];
		double SAMPLES_DEW[][] = new double[TOTAL_DATA][2];
		double SAMPLES_HUMIDITY[][] = new double[TOTAL_DATA][2];
		double SAMPLES_PRES[][] = new double[TOTAL_DATA][2];
		double SAMPLES_VISIB[][] = new double[TOTAL_DATA][2];

		double SAMPLES_PREC[] = new double[TOTAL_DATA];
		double SAMPLES_CLOUD[] = new double[TOTAL_DATA];

		double SAMPLES_WIND[] = new double[TOTAL_DATA];
		double SAMPLES_GUST[] = new double[TOTAL_DATA];
		double SAMPLES_WIND_DIR[] = new double[TOTAL_DATA];

		Set yearSet = (Set) dataMap.keySet();
		Iterator iter = yearSet.iterator();
		int i = 0;
		while (iter.hasNext()) {
			String yearStr = (String) iter.next();
			WeatherHistory hist = (WeatherHistory) dataMap.get(yearStr);
			// System.out.println("IN worker");

			double[] temp = { hist.getTempMax(), hist.getTempMin() };
			SAMPLES_TEMP[i] = temp;

			double[] temp1 = { hist.getDewPointHigh(), hist.getDewPointMin() };
			SAMPLES_DEW[i] = temp1;

			double[] temp2 = { hist.getHumidityHigh(), hist.getHumidityMin() };
			SAMPLES_HUMIDITY[i] = temp2;

			double[] temp3 = { hist.getPressureMax(), hist.getPressureMin() };
			SAMPLES_PRES[i] = temp3;

			double[] temp4 = { hist.getVisibilityMax(), hist.getVisibilityMin() };
			SAMPLES_VISIB[i] = temp4;

			// double[] temp5 = { hist.getPrecipitation(), 0};
			SAMPLES_PREC[i] = hist.getPrecipitation();

			// double[] temp6 = { hist.getCloudCover(), 0};
			SAMPLES_CLOUD[i] = hist.getCloudCover();

			SAMPLES_WIND[i] = hist.getWindSpeedMean();
			SAMPLES_GUST[i] = hist.getGustSpeedMax();
			SAMPLES_WIND_DIR[i] = hist.getWindDirection();

			i++;
		}
		KMeans kmeans = new KMeans();
		double data[] = kmeans.calculateKmeans(NUM_CLUSTERS, TOTAL_DATA,
				SAMPLES_TEMP);
		wfcast.setTempMax((int) data[0]);
		wfcast.setTempMin((int) data[1]);

		kmeans = new KMeans();
		double data1[] = kmeans.calculateKmeans(NUM_CLUSTERS, TOTAL_DATA,
				SAMPLES_DEW);
		wfcast.setDewPointHigh((int) data1[0]);
		wfcast.setDewPointMin((int) data1[1]);

		kmeans = new KMeans();
		double data2[] = kmeans.calculateKmeans(NUM_CLUSTERS, TOTAL_DATA,
				SAMPLES_HUMIDITY);
		wfcast.setHumidityHigh((int) data2[0]);
		wfcast.setHumidityMin((int) data2[1]);

		kmeans = new KMeans();
		double data3[] = kmeans.calculateKmeans(NUM_CLUSTERS, TOTAL_DATA,
				SAMPLES_PRES);
		wfcast.setPressureMax((int) data3[0]);
		wfcast.setPressureMin((int) data3[1]);

		kmeans = new KMeans();
		double data4[] = kmeans.calculateKmeans(NUM_CLUSTERS, TOTAL_DATA,
				SAMPLES_VISIB);
		wfcast.setVisibilityMax((int) data4[0]);
		wfcast.setVisibilityMin((int) data4[1]);
		// SAMPLES= new double[TOTAL_DATA][2];

		kmeans = new KMeans();
		double data5 = kmeans.calculateMedian(SAMPLES_PREC);
		wfcast.setPrecipitation((int) data5);

		kmeans = new KMeans();
		double data6 = kmeans.calculateMedian(SAMPLES_CLOUD);
		wfcast.setCloudCover((int) data6);

		kmeans = new KMeans();
		double data7 = kmeans.calculateMedian(SAMPLES_CLOUD);
		wfcast.setWindSpeedMean((int) data7);

		kmeans = new KMeans();
		double data8 = kmeans.calculateMedian(SAMPLES_GUST);
		wfcast.setGustSpeedMax((int) data8);

		kmeans = new KMeans();
		double data9 = kmeans.calculateMedian(SAMPLES_WIND_DIR);
		wfcast.setCloudCover((int) data9);

		return wfcast;
	}

}
