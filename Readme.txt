git clone  https://github.com/binnyms/BinnyWeatherForecast.git

Pre-requisites 
JDK -1.7
Maven - 3+

JAVA_HOME, MVN_HOME to be setup
set JAVA_HOME=
set MVN_HOME=
set path=%path%;JAVA_HOME/bin;MVH_HOME/bin


Steps to execute 

git clone  https://github.com/binnyms/BinnyWeatherForecast.git
cd BinnyWeatherForecast
mvn clean install
(Do maven settings appropriately if you are behind a proxy)
cd target
java -jar BinnyWeatherData-0.0.1-SNAPSHOT-jar-with-dependencies.jar <date in YYYY-M-D or YYYY-MM-DD>
java -jar BinnyWeatherData-0.0.1-SNAPSHOT-jar-with-dependencies.jar 2016-10-19
