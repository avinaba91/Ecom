package com.hackerrank.weather.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.hackerrank.weather.model.Location;
import com.hackerrank.weather.model.Weather;

@Repository
public class WeatherRepository {

	private Map<Long, Weather> weatherInfos = new HashMap<Long, Weather>();

	public boolean add(Weather weather) {
		boolean flag = false;
		if (!weatherInfos.containsKey(weather.getId())) {
			weatherInfos.put(weather.getId(), weather);
			flag = true;
		}
		return flag;
	}

	public boolean deleteAll() {
		weatherInfos = new HashMap<>();
		return true;
	}

	public void delete (Date startDate, Date endDate, float lat, float lon) {
		if (!CollectionUtils.isEmpty(weatherInfos)) {
			Iterator<Long> itr = weatherInfos.keySet().iterator();
			while (itr.hasNext()) {
				long id = itr.next();
				Weather weather = weatherInfos.get(id);
				if (weather.getDate().compareTo(startDate) >= 0 && weather.getDate().compareTo(endDate) <= 0) {
					Location location = weather.getLocation();
					if (lat == location.getLat() && lon == location.getLon()) {
						itr.remove();
					}
				}
			}
		}
	}

	public List<Weather> getAll() {
		return new ArrayList<Weather>(weatherInfos.values());
	}

	public Weather getWeather(float lat, float lon) {
		Weather weather = null;
		if (!CollectionUtils.isEmpty(weatherInfos)) {
			Iterator<Long> itr = weatherInfos.keySet().iterator();
			while (itr.hasNext()) {
				long id = itr.next();
				Weather weatherDB = weatherInfos.get(id);
				Location location = weatherDB.getLocation();
				if (lat == location.getLat() && lon == location.getLon()) {
					weather = weatherDB;
				}
			}
		}
		return weather;
	}
	
	public List<Weather> getWeathers (Date startDate, Date endDate) {
		List<Weather> weathers = null;
		if (!CollectionUtils.isEmpty(weatherInfos)) {
			weathers = new ArrayList<>();
			Iterator<Long> itr = weatherInfos.keySet().iterator();
			while (itr.hasNext()) {
				long id = itr.next();
				Weather weather = weatherInfos.get(id);
				if (weather.getDate().compareTo(startDate) >= 0 && weather.getDate().compareTo(endDate) <= 0) {
					weathers.add(weather);
				}
			}
		}
		return weathers;
	}
}
