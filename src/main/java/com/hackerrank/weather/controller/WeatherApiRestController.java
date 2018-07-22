package com.hackerrank.weather.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherInfo;
import com.hackerrank.weather.service.WeatherService;

@RestController
public class WeatherApiRestController {

	@Autowired
	WeatherService weatherService;

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<String> dataException(HttpServletRequest request,
			Exception ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		ex.printStackTrace();
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/erase", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> erase() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		weatherService.deleteAll();
		return new ResponseEntity<String>(HttpStatus.OK.toString(), headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/erase", params = {"start", "end", "lat", "lon"}, method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> erase(@RequestParam(value = "start") String startDate,
			@RequestParam(value = "end") String endDate, @RequestParam(value = "lat") String latitude,
			@RequestParam(value = "lon") String longitude) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		return new ResponseEntity<String>(HttpStatus.OK.toString(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/weather", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> add(@RequestBody Weather weather) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		boolean flag = weatherService.add(weather);
		if (flag) {
			return new ResponseEntity<String>(HttpStatus.CREATED.toString(), headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST.toString(), headers, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/weather", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Weather>> get() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		List<Weather> weathers = weatherService.getAll();
		return new ResponseEntity<List<Weather>>(weathers, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/weather",  params = {"lat", "lon"}, method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> get(@RequestParam(value = "lat") String latitude,
			@RequestParam(value = "lon") String longitude) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		Weather weather = weatherService.getWeather(latitude, longitude);
		if (weather != null) {
			return new ResponseEntity<Object>(weather, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND.toString(), headers, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/weather/temperature", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<WeatherInfo>> getInfo(@RequestParam(value = "start") String startDate,
			@RequestParam(value = "end") String endDate) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		List<WeatherInfo> weatherInfos = null;
		try {
			weatherInfos = weatherService.getWeatherInfos(startDate, endDate);
		} catch (Exception e) {
		}
		return new ResponseEntity<List<WeatherInfo>>(weatherInfos, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getInfo() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		return new ResponseEntity<String>("My name is khan", headers, HttpStatus.OK);
	}
}
