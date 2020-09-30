package com.ikubinfo.suggestion.model;

public class Driver {
	
	private int driver_id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String driving_license_number;
	private String phoneNumber;
	private Boolean isWorking;
	
	
	public Driver() {
		super();
	}
	public Driver(int driver_id, String firstName, String lastName, String username, String password,
			String driving_license_number, String phoneNumber, Boolean isWorking) {
		this.driver_id = driver_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.driving_license_number = driving_license_number;
		this.phoneNumber = phoneNumber;
		this.isWorking = isWorking;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
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
	public String getDriving_license_number() {
		return driving_license_number;
	}
	public void setDriving_license_number(String driving_license_number) {
		this.driving_license_number = driving_license_number;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getIsWorking() {
		return isWorking;
	}
	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}
	@Override
	public String toString() {
		return "Driver [driver_id=" + driver_id + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", driving_license_number=" + driving_license_number
				+ ", phoneNumber=" + phoneNumber + ", isWorking=" + isWorking + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + driver_id;
		result = prime * result + ((driving_license_number == null) ? 0 : driving_license_number.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((isWorking == null) ? 0 : isWorking.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (driver_id != other.driver_id)
			return false;
		if (driving_license_number == null) {
			if (other.driving_license_number != null)
				return false;
		} else if (!driving_license_number.equals(other.driving_license_number))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isWorking == null) {
			if (other.isWorking != null)
				return false;
		} else if (!isWorking.equals(other.isWorking))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	

}
