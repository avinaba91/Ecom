package com.hackerrank.weather.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherInfo;
import com.hackerrank.weather.repository.WeatherRepository;

@Component
public class WeatherService {

	@Autowired
	WeatherRepository weatherRepository;

	public void deleteAll() {
		weatherRepository.deleteAll();
	}

	public void delete(String startDate, String endDate, String latitude, String longitude) throws Exception {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDT = formatter.parse(startDate);
		Date endDT = formatter.parse(endDate);
		float lat = Float.parseFloat(latitude);
		float lon = Float.parseFloat(longitude);
		weatherRepository.delete(startDT, endDT, lat, lon);
	}

	public boolean add(Weather weather) {
		boolean flag = weatherRepository.add(weather);
		return flag;
	}

	public List<Weather> getAll() {
		List<Weather> weathers = weatherRepository.getAll();
		weathers.sort(new Comparator<Weather>() {
			@Override
			public int compare(Weather o1, Weather o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return weathers;
	}

	public Weather getWeather(String latitude, String longitude) {
		float lat = Float.parseFloat(latitude);
		float lon = Float.parseFloat(longitude);
		Weather weather = weatherRepository.getWeather(lat, lon);
		return weather;
	}

	public List<WeatherInfo> getWeatherInfos (String startDate, String endDate) throws Exception {
		List<WeatherInfo> weatherInfos = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDT = formatter.parse(startDate);
		Date endDT = formatter.parse(endDate);
		List<Weather> weathers = weatherRepository.getWeathers(startDT, endDT);
		if (!CollectionUtils.isEmpty(weathers)) {
			weatherInfos = new ArrayList<>();
			for (Weather weather : weathers) {
				WeatherInfo weatherInfo = new WeatherInfo();
				weatherInfo.setCity (weather.getLocation().getCity());
				weatherInfo.setState(weather.getLocation().getState());
				weatherInfo.setLat(weather.getLocation().getLat());
				weatherInfo.setLon(weather.getLocation().getLon());
				if (weather.getTemperature() != null) {
					List<Float> tempList = Arrays.asList(weather.getTemperature());
					weatherInfo.setHighest(Collections.max(tempList));
					weatherInfo.setLowest(Collections.min(tempList));
				} else {
					weatherInfo.setMessage("There is no weather data in the given date range");
				}
				weatherInfos.add(weatherInfo);
			};
			weatherInfos.sort(new Comparator<WeatherInfo>() {
				@Override
				public int compare(WeatherInfo o1, WeatherInfo o2) {
					return o1.getCity().compareTo(o2.getCity());
				}
			});
			weatherInfos.sort(new Comparator<WeatherInfo>() {
				@Override
				public int compare(WeatherInfo o1, WeatherInfo o2) {
					return o1.getState().compareTo(o2.getState());
				}
			});
			
		}
		return weatherInfos;
	}
}
