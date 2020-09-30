package com.ikubinfo.suggestion.model;

import java.sql.Timestamp;
import java.util.Date;

public class Ride {
	
	  private int ride_id;
	  private String startAddress;
	  private String finishAddress;
	  private Date startTime;
	  private Timestamp finishTime;
	  private String status;
	  private String price;
	  private double lat;	   
	  private double lng;
	  private int client_id;
	  private int driver_id;
	  private int car_id;
	  private int payment_id;
	  
	  
	public Ride() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ride(int ride_id, String startAddress, String finishAddress, Date startTime, Timestamp finishTime,
			String status, String price, double lat, double lng, int client_id, int driver_id, int car_id,
			int payment_id) {
		super();
		this.ride_id = ride_id;
		this.startAddress = startAddress;
		this.finishAddress = finishAddress;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.status = status;
		this.price = price;
		this.lat = lat;
		this.lng = lng;
		this.client_id = client_id;
		this.driver_id = driver_id;
		this.car_id = car_id;
		this.payment_id = payment_id;
	}
	public int getRide_id() {
		return ride_id;
	}
	public void setRide_id(int ride_id) {
		this.ride_id = ride_id;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getFinishAddress() {
		return finishAddress;
	}
	public void setFinishAddress(String finishAddress) {
		this.finishAddress = finishAddress;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Timestamp getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	@Override
	public String toString() {
		return "Ride [ride_id=" + ride_id + ", startAddress=" + startAddress + ", finishAddress=" + finishAddress
				+ ", startTime=" + startTime + ", finishTime=" + finishTime + ", status=" + status + ", price=" + price
				+ ", lat=" + lat + ", lng=" + lng + ", client_id=" + client_id + ", driver_id=" + driver_id
				+ ", car_id=" + car_id + ", payment_id=" + payment_id + "]";
	}
	  

	  
	
}
