package com.ikubinfo.suggestion.managedbeans;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.ikubinfo.suggestion.model.Car;
import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.model.Driver;
import com.ikubinfo.suggestion.model.Ride;
import com.ikubinfo.suggestion.service.AdminService;


@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {


	private static final long serialVersionUID = 7373482172418784614L;
	
	@ManagedProperty(value = "#{adminService}")
	private AdminService adminService;
	
	
	private List<Client> clientList;
	private List<Car> carList;
	private List<Driver> driverList;
	private List<Ride> rideList;
	
	private Client client;
	private Driver driver;
	private Car car;
	private Ride ride;
	
	private MapModel emptyModel;
	private MapModel fullModel;


	@PostConstruct
	public void notInit() {
		client = new Client();
		driver = new Driver();
		car = new Car();
		ride = new Ride();
		search();
		
	}

	
	public void search() {
		clientList = adminService.getAllClients();
		driverList = adminService.getAllDrivers();
        carList = adminService.getAllCars();
        rideList = adminService.getAllRides();
        
	   setEmptyModel(new DefaultMapModel());
	   setFullModel(new DefaultMapModel());
	}
	
	
	//Admin methods CRUD for client
		public void searchClients( ){
			String name = client.getFirstName();
			clientList =  adminService.searchClients(name);
		}
		
		public void adminRegisterClient() {
			adminService.adminRegisterClient(client);
			notInit();
		}
		public void deleteClient(int id){
			adminService.adminDeleteClient(id);
			notInit();
		}

		
		//Admin methods CRUD for driver
		public void searchDriver(){
			String name = driver.getFirstName();
			driverList =  adminService.searchDriver(name);
		}
		
		public void adminRegisterDriver() {
			adminService.adminRegisterDriver(driver);
			notInit();
		}
		
		public void deleteDriver(int id){
			adminService.adminDeleteDriver(id);
			notInit();
		}
		
		
		//Admin methods CRUD for car
		public void searchCar(){
			String plate = car.getLicensePlate();
			carList =  adminService.searchCar(plate);
		}
		
		public void adminRegisterCar() {
			adminService.adminRegisterCar(car);
			notInit();
		}

		public void deleteCar(int id){
			adminService.adminDeleteCar(id);
			notInit();
		}

		
		
		//Admin methods CRUD for ride
		public void searchRide(){
			String street = ride.getStartAddress();
			rideList =  adminService.searchRides(street);
		}
		
		public void adminRegisterRide() {
			adminService.adminRegisterRide(ride);
			notInit();
		}

		public void deleteRide(int ride_id){
			adminService.adminDeleteRide(ride_id);
			notInit();
		}



		 
		 
		 //Update methods 
	
		public void onRowEdit(RowEditEvent<Client> event) {
			 
			Client us= (Client) event.getObject();
			adminService.adminUpdateClient(us);
			 
		    FacesMessage msg = new FacesMessage("Client edited successfully!");
		    FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		     
	
		public void onRowCancel(RowEditEvent<Client> event) {
		    FacesMessage msg = new FacesMessage("Row edit canceled");
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		 

		public void onRowEditDriver(RowEditEvent<Driver> event) {
				 
		    Driver us= (Driver) event.getObject();
		    adminService.adminUpdateDriver(us);
				 
			FacesMessage msg = new FacesMessage("Driver edited successfully!");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
			     
		 public void onRowCancelDriver(RowEditEvent<Driver> event) {
			 FacesMessage msg = new FacesMessage("Row edit canceled");
			 FacesContext.getCurrentInstance().addMessage(null, msg);
		 }
			    
				 
		 public void onRowEditCar(RowEditEvent event) {
					 
			 Car us= (Car) event.getObject();
			 adminService.adminUpdateCar(us);
					 
			 FacesMessage msg = new FacesMessage("Car edited successfully!");
			 FacesContext.getCurrentInstance().addMessage(null, msg);
   
		 }
				     
		public void onRowCancelCar(RowEditEvent event) {
			FacesMessage msg = new FacesMessage("Row edit canceled");
			FacesContext.getCurrentInstance().addMessage(null, msg);   
		}
		
		public void onRowEditRide(RowEditEvent event) {
			 
		   Ride us= (Ride) event.getObject();
		   adminService.adminUpdateRide(us);
					 
		   FacesMessage msg = new FacesMessage("Ride edited successfully!");
	       FacesContext.getCurrentInstance().addMessage(null, msg);
   
		 }
				     
		public void onRowCancelRide(RowEditEvent event) {
			FacesMessage msg = new FacesMessage("Row edit canceled");
			FacesContext.getCurrentInstance().addMessage(null, msg);   
		}
	
	

					/*public void addMarker(){

						userService.addMarker();
					}*/
					

		public void addMarker() {
							
	      Marker marker = new Marker(new LatLng(ride.getLat(),ride.getLng()));
		  emptyModel.addOverlay(marker);
	      adminService.coordinates(ride);
	      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + ride.getLat() + ", Lng:" + ride.getLng()));	   
		}
					  
		 public void readMarker(int ride_id) {
							
		 Marker marker = new Marker(new LatLng(adminService.getLat(ride_id),adminService.getLng(ride_id)));
		   fullModel.addOverlay(marker);
		   adminService.coordinates(ride);
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + ride.getLat() + ", Lng:" + ride.getLng()));
			      
		 }
					  
				    

	public List<Client> getClientList() {
		return clientList;
	}


	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}


	public List<Car> getCarList() {
		return carList;
	}


	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}


	public List<Driver> getDriverList() {
		return driverList;
	}


	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}


	public List<Ride> getRideList() {
		return rideList;
	}


	public void setRideList(List<Ride> rideList) {
		this.rideList = rideList;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Ride getRide() {
		return ride;
	}


	public void setRide(Ride ride) {
		this.ride = ride;
	}


	public AdminService getAdminService() {
		return adminService;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	public MapModel getEmptyModel() {
		return emptyModel;
	}


	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}


	public MapModel getFullModel() {
		return fullModel;
	}


	public void setFullModel(MapModel fullModel) {
		this.fullModel = fullModel;
	}

	
	
}
