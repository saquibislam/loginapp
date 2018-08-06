package com.saq.loginapp.mvcbeans;

public class Employee {

	private int id;
	private String username;
	private String password;
	private String name;
	private float salary;
	private Address address;

	public Employee(int id, String name, float salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void showEmployeeDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ID: ").append(this.id);
		sb.append(", NAME: ").append(this.name);
		sb.append(", SALARY: ").append(this.salary);
		sb.append(", ADDRESS: ").append(this.address.getCity()).append(" - ").append(this.address.getPinCode()).append("]");
		System.out.println(sb.toString());
	}
}