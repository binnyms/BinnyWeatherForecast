package com.binny.worker;

import java.util.ArrayList;


import com.binny.data.WeatherHistory;
/**
 * Class EventWorker generates events (predicts if it rains/snow/fog etc) based on weather conditions
 * 
 * @author Binny
 * 
 */
public class EventWorker implements ForecastWorker {

	
	//public String[] eventList = {"SUNNY", "CLEAR" , "RAIN" , "THUNDERSTORMS" , "SNOW", "FOG", "HEATWAVE", "SNOW" , "HAIL", "TORNADO"};
	public EventWorker() {
	}

	@Override
	public WeatherHistory forecast(WeatherHistory wh) {
			ArrayList<String> events = new ArrayList();
			
			if (wh.getHumiditytMean() > 95 )
			{
				events.add("RAIN");
			} else if (wh.getHumiditytMean() >= 85 && wh.getVisibilityMean() >10 )
			{
				events.add("RAIN");
			}
	
			if (wh.getTempMin() < 8 )
			{
				events.add("FOG");
			} else if (wh.getTempMean() < 15 && wh.getVisibilityMean() >10 )
			{
				events.add("FOG");
			}
			
			if (
				(wh.getWindDirection()< 5 || wh.getWindDirection() <230) && 
				wh.getCloudCover()> 5 && wh.getTempMean() < 12 && wh.getHumiditytMean() >70)
			{
				events.add("HAIL");
			}
			if (wh.getWindSpeedMax() > 30 && wh.getGustSpeedMax() > 40)
			{
				events.add("THUNDERSTORM");
			}
			
			if (wh.getTempMean() > 25 && wh.getVisibilityMean() < 5)
			{
				events.add("SUNNY");
			}
			
			String eventStr ="";
			if (! events.isEmpty())
			{
				String[] eventArr = (String[]) events.toArray(new String[0]);
				for (int i =0; i< eventArr.length; i++)
				{
					eventStr = eventStr + " " + eventArr[i];
				}
				
			} else eventStr ="  ";
			wh.setEvent(eventStr);
			
		return wh;
	}

}
