package com.ikubinfo.suggestion.model;

public class Car {
	
	private int carId;
	private String licensePlate;
	private String modelName;
	private int seatNumber;
	private String location;
	private Boolean isActive;
	private String driving_license_number;
	
	public Car() {
		super();
	}
	public Car(int carId, String licensePlate, String modelName, int seatNumber, String location, Boolean isActive,
			String driving_license_number) {
		this.carId = carId;
		this.licensePlate = licensePlate;
		this.modelName = modelName;
		this.seatNumber = seatNumber;
		this.location = location;
		this.isActive = isActive;
		this.driving_license_number = driving_license_number;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getDriving_license_number() {
		return driving_license_number;
	}
	public void setDriving_license_number(String driving_license_number) {
		this.driving_license_number = driving_license_number;
	}
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", licensePlate=" + licensePlate + ", modelName=" + modelName + ", seatNumber="
				+ seatNumber + ", location=" + location + ", isActive=" + isActive + ", driving_license_number="
				+ driving_license_number + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((driving_license_number == null) ? 0 : driving_license_number.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + seatNumber;
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
		Car other = (Car) obj;
		if (carId != other.carId)
			return false;
		if (driving_license_number == null) {
			if (other.driving_license_number != null)
				return false;
		} else if (!driving_license_number.equals(other.driving_license_number))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (seatNumber != other.seatNumber)
			return false;
		return true;
	}
	

	
}