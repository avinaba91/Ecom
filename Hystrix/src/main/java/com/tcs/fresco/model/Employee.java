package com.tcs.fresco.model;

public class Employee {
	private int id;
	private String name;
	private String education;
	private String location;
	private int yearOfEmployment;
	private String bankName;
	
	public Employee(int id, String name, String education, String location, int yearsOfEmployment, String bankName) {
		this.id= id;
		this.name=name;
		this.education= education;
		this.location= location;
		this.yearOfEmployment= yearsOfEmployment;
		this.bankName= bankName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getYearOfEmployment() {
		return yearOfEmployment;
	}
	public void setYearOfEmployment(int yearOfEmployment) {
		this.yearOfEmployment = yearOfEmployment;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", name=").append(name).append(", education=")
				.append(education).append(", location=").append(location).append(", yearOfEmployment=")
				.append(yearOfEmployment).append(", bankName=").append(bankName).append("]");
		return builder.toString();
	}
}
