package com.balatamilmani.restangular.model;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String id;
	String firstName;
	String lastName;
	Address address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString(){
		return new StringBuffer().append("ID->").append(this.id).append("\n")
				.append("First Name->").append(this.firstName).append("\n")
				.append("Last Name->").append(this.lastName).append("\n")
				.append("Address").append("\n")
				.append("\tStreet->").append(this.address.getStreet()).append("\n")
				.append("\tCity->").append(this.address.getCity()).append("\n")
				.append("\tState->").append(this.address.getState()).append("\n")
				.append("\tZip->").append(this.address.getZip()).toString();
	}
}
