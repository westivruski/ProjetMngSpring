package com.ikubinfo.suggestion.service;

import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikubinfo.suggestion.dao.AdminDao;
import com.ikubinfo.suggestion.model.Car;
import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.model.Driver;
import com.ikubinfo.suggestion.model.Ride;

@Service
public class AdminService  {
	

	@Autowired
	private AdminDao adminDao;
	

	//Admin methods for Client Read, Search, Delete, Update
	public List<Client> getAllClients() {
		return adminDao.getAllClients();
	}
	
	public List<Client> searchClients(String firstName) {
		return adminDao.searchClientByName(firstName);
	}
	
	public void adminRegisterClient(Client client) {
		adminDao.registerClient(client);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adminPanel.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void adminDeleteClient(int projectId){
		adminDao.deleteClient(projectId);
	}
	
	public void adminUpdateClient(Client client){
		adminDao.updateClient(client);
	}

	
	//Admin methods for Driver Read, Search, Delete, Update
	public List<Driver> getAllDrivers() {
		return adminDao.getAllDrivers();
	}
	
	public List<Driver> searchDriver(String firstName) {
		return adminDao.searchDriverByName(firstName);
	}
	
	public void adminRegisterDriver(Driver driver) {
		adminDao.registerDriver(driver);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adminPanel.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void adminDeleteDriver(int driverId){
		adminDao.deleteDriver(driverId);
	}
	
	public void adminUpdateDriver(Driver driver){
		adminDao.updateDriver(driver);
	}
	
	

	
	//Admin methods for Driver Read, Search, Delete, Update
	public List<Car> getAllCars() {
		return adminDao.getAllCars();
	}
	
	public List<Car> searchCar(String plate) {
		return adminDao.searchCarByPlate(plate);
	}
	
	public void adminRegisterCar(Car car) {
		adminDao.registerCar(car);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adminPanel.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void adminDeleteCar(int carId){
		adminDao.deleteCar(carId);
	}
	
	public void adminUpdateCar(Car car){
		adminDao.updateCar(car);
	}
	
	
	
	//Admin methods for Ride Read, Search, Delete, Update
	public List<Ride> getAllRides() {
		return adminDao.getAllRides();
	}
	
	public List<Ride> searchRides(String street) {
		return adminDao.searchRideByStreet(street);
	}
	
	public void adminRegisterRide(Ride ride) {
		adminDao.registerRide(ride);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adminPanel.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void adminDeleteRide(int ride_id){
		adminDao.deleteRide(ride_id);
	}
	
	public void adminUpdateRide(Ride ride){
		adminDao.updateRide(ride);
	}
	
	
	
	
	
	
	
	
	//testing lat and lng ////////////
	public void coordinates(Ride order){
		adminDao.getCordinates(order);
	}
	
	public Double getLat(int ride_id){
		return adminDao.getLat(ride_id);
	}
	public Double getLng(int ride_id){
		return adminDao.getLng(ride_id);
	}
	
	
	//test methods
	
	public void updateDriverWorking(Driver driver){
		adminDao.updateDriverWorking(driver);
	}
	public void updateDriverNotWorking(Driver driver){
		adminDao.updateDriverNotWorking(driver);
	}
	
	//testing coordinates output
	public Driver selectFromId(int driver) {   
	    return adminDao.selectFromId(driver);
	}

	
  
	 

	
	 /*	 
	public void login(String username){
	  userDao.getUserInfo(username);
	  
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Client userInfo = userDao.getUserInfo(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), 
				userInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
	 */
}
