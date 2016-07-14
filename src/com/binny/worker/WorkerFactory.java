package com.binny.worker;

/**
 * Worker factory implementation - Common for workers and forecast workers
 * @author binny
 *
 */
public class WorkerFactory {

	public WorkerFactory() {

	}
	
	   public Worker getWorker(String workerName){
		      if(workerName == null){
		         return null;
		      }		
		      if(workerName.equalsIgnoreCase("KMEANS")){
		         return new KMeansWorker();
		         
		      } 
		      
		      return null;
		   }
	   
	   public ForecastWorker getForecastWorker(String workerName){
		      if(workerName == null){
		         return null;
		      }		
		      if(workerName.equalsIgnoreCase("MEAN")){
		         return new MeanWorker();
		         
		      } 
		      else if(workerName.equalsIgnoreCase("TIMEOFDAY")){
		         return new TimeOfDayWorker();
		         
		      } 
		      else if(workerName.equalsIgnoreCase("GLOBALWARMING")){
			         return new GlobarWarmingWorker();
			         
			      } 
		      else if(workerName.equalsIgnoreCase("OCEAN")){
			         return new OceanEffectsWorker();
			         
			      } 
		      else if(workerName.equalsIgnoreCase("EVENT")){
			         return new EventWorker();
			         
			      } 
		      return null;
		   }

}
