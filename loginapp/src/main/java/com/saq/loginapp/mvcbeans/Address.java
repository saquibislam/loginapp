package com.saq.loginapp.mvcbeans;

public class Address {

	private int addressId;
	private String city;
	private int pinCode;

	public Address(int addressId, String city, int pinCode) {
		this.addressId = addressId;
		this.city = city;
		this.pinCode = pinCode;
	}

	public Address() {
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
}