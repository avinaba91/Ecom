package com.tcs.fresco.rest;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.fresco.model.Employee;
import com.tcs.fresco.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeRest {
	
    @Autowired
    EmployeeService empService;
    
    
    @ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<String> dataException(HttpServletRequest request, Exception ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		ex.printStackTrace();
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> getEmp(@PathVariable int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		Employee emp = empService.getEmp(id);
		return new ResponseEntity<Employee>(emp, headers, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/bank/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> getBank(@PathVariable int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		Employee emp = empService.getBank(id);
		return new ResponseEntity<Employee>(emp, headers, HttpStatus.OK);
	}
}
