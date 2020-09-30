package com.ikubinfo.suggestion.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ikubinfo.suggestion.mapper.CarRowMapper;
import com.ikubinfo.suggestion.mapper.ClientRowMapper;
import com.ikubinfo.suggestion.mapper.DriverRowMapper;
import com.ikubinfo.suggestion.mapper.RideRowMapper;
import com.ikubinfo.suggestion.model.Car;
import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.model.Driver;
import com.ikubinfo.suggestion.model.Ride;



@Repository
public class AdminDao {
	
	//private static final String SearchQUERY = "Select project.name,project.description,project.createdtime,project.modifiedtime,project.email from project where name LIKE LOWER(?)";
	private static final String CreateQUERYClient = "insert into client(firstname,lastname,username,email,password,phonenumber) values (?,?,?,?,?,?)";
	private static final String CreateQUERYCar = "insert into car(licenseplate,modelname,seatnumber,location,isactive,driving_license_number) values (?,?,?,?,?,?)";
	private static final String CreateQUERYDriver = "insert into driver(firstname,lastname,username,password,phonenumber,driving_license_number,isWorking) values (?,?,?,?,?,?,?)";
	
	private static final String CreateQUERYRide = "insert into ride(startaddress,finishaddress,starttime,status,price,client_id,driver_id,car_id) values (?,?,?,?,?,?,?,?)";
	
	private static final String ReadQUERYDriver = "select driver_id,firstname,lastname,username,password,phonenumber,driving_license_number,isWorking from driver";
	private static final String ReadQUERYClient = "Select client_id,firstName, lastName, username, email, phonenumber, password, cardnumber from client ";
	private static final String ReadQUERYCar = "Select car_id,licenseplate,modelname,seatnumber,location,isactive,driving_license_number from car ";
	
	private static final String ReadQUERYRide = "Select ride_id,startaddress,finishaddress,starttime,finishtime,date,status,price,lat,lng,client_id,driver_id,car_id,payment_id from ride ";
	
	private static final String UpdateQUERYClient = "update client set firstname = :firstname, lastname = :lastname, username = :username, email = :email, password = :password, phonenumber = :phonenumber, cardnumber = :cardnumber  where client_id = :client_id";
	private static final String UpdateQUERYDriver = "update driver set firstname = :firstname, lastname = :lastname, username = :username, password = :password,driving_license_number = :driving_license_number,isworking = :isworking, phonenumber = :phonenumber   where driver_id = :driver_id";
	private static final String UpdateQUERYCar = "update car set licenseplate = :licenseplate, modelname = :modelname, seatnumber = :seatnumber, location = :location,isactive = :isactive,driving_license_number = :driving_license_number   where car_id = :car_id";
	
	private static final String UpdateQUERYRide = "update ride set startaddress = :startaddress, finishaddress = :finishaddress, starttime = :starttime, status = :status, price = :price   where ride_id = :ride_id";
	
	private static final String DeleteQUERYClient = "delete from client where client_id = ? ";
	private static final String DeleteQUERYDriver = "delete from driver where driver_id = ? ";
	private static final String DeleteQUERYCar = "delete from car where car_id = ? ";
	
	private static final String DeleteQUERYRide = "delete from ride where ride_id = ? ";
	
	private String SearchQUERYClient = "Select client_id, firstName, lastName, username, email, phonenumber, password, cardnumber from client where 1=1 ";
	private String SearchQUERYDriver= "Select driver_id, firstName, lastName, username, password, driving_license_number,  phonenumber, isworking from driver where 1=1 ";
	private String SearchQUERYCar= "Select car_id, licenseplate, modelname, seatnumber, location, isactive,  driving_license_number  from car where 1=1 ";
	
	private String SearchQUERYRide= "Select ride_id, startaddress,finishaddress,date,status,price,lat,lng,client_id,driver_id,car_id,payment_id from ride where 1=1 ";
	
	
	//testing methods
	private static final String UpdateDriverWorkTrue = "update driver set isworking = true where driver_id = :driver_id";
	private static final String UpdateDriverWorkFalse = "update driver set isworking = false where driver_id = :driver_id";
	private static final String cordinates = "insert into ride (lat,lng) values (?,?) ";
	private static final String SelectQUERYDriver = "select lat,lng from order where order_id = ?";
	private static final String SelectLat = "select lat from ride where ride_id = ?";
	private static final String SelectLng = "select lng from ride where ride_id = ?";
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
    @Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
		
    
    
    
    //CRUD Create with jdbcTemplate for Client
	
	public List<Client> getAllClients() {
		return namedParameterJdbcTemplate.query(ReadQUERYClient, new ClientRowMapper());
	}

	
	public List<Client> searchClientByName(String firstName) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("firstName", "%" + firstName +"%");
		
		if (!Objects.isNull(firstName)) {
			SearchQUERYClient = SearchQUERYClient.concat(" AND LOWER(firstName) LIKE LOWER(:firstName) ");
		}
		return namedParameterJdbcTemplate.query(SearchQUERYClient, params, new ClientRowMapper());
	}
	
	
    public void registerClient(Client client) {
        jdbcTemplate.update(CreateQUERYClient,  client.getFirstName(), client.getLastName(),client.getUsername(),client.getEmail(), client.getPassword(), client.getPhonenumber());
    }
	
	
	public void deleteClient(int clientId) {   
	    jdbcTemplate.update(DeleteQUERYClient, clientId);
	}
	
	
	public void updateClient(Client client){
		
		   MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		   
		   namedParameters.addValue("firstname", client.getFirstName());
		   namedParameters.addValue("lastname", client.getLastName());
		   namedParameters.addValue("username", client.getUsername());
		   namedParameters.addValue("email", client.getEmail());
		   namedParameters.addValue("password", client.getPassword() );
		   namedParameters.addValue("phonenumber", client.getPhonenumber());
		   namedParameters.addValue("cardnumber", client.getCardnumber());
		   namedParameters.addValue("client_id", client.getClient_id());
		   
		   this.namedParameterJdbcTemplate.update(UpdateQUERYClient,namedParameters);
	   }
		
	
	//CRUD Select from id with jdbcTemplate for Driver

	public List<Driver> getAllDrivers() {
		return namedParameterJdbcTemplate.query(ReadQUERYDriver, new DriverRowMapper());
	}
	
	public List<Driver> searchDriverByName(String firstName) {
		Map<String, Object> params = new HashMap<>();
		params.put("firstName", "%" + firstName +"%");
		
		if (!Objects.isNull(firstName)) {
			SearchQUERYDriver = SearchQUERYDriver.concat(" AND LOWER(firstName) LIKE LOWER(:firstName) ");
		}
		return namedParameterJdbcTemplate.query(SearchQUERYDriver, params, new DriverRowMapper());
	}


	public void registerDriver(Driver driver) {
        jdbcTemplate.update(CreateQUERYDriver,  driver.getFirstName(), driver.getLastName(),driver.getUsername(), driver.getPassword(), driver.getPhoneNumber(), driver.getDriving_license_number(), driver.getIsWorking());
    }

	
	public void deleteDriver(int driverId) {   
	    jdbcTemplate.update(DeleteQUERYDriver, driverId);
	}
	
	public void updateDriver(Driver driver){
		
		   MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		   
		   namedParameters.addValue("firstname", driver.getFirstName());
		   namedParameters.addValue("lastname", driver.getLastName());
		   namedParameters.addValue("username", driver.getUsername());
		   namedParameters.addValue("password", driver.getPassword() );
		   namedParameters.addValue("phonenumber", driver.getPhoneNumber());
		   namedParameters.addValue("driving_license_number", driver.getDriving_license_number());
		   namedParameters.addValue("isworking", driver.getIsWorking());
		   namedParameters.addValue("driver_id", driver.getDriver_id());
		   
		   this.namedParameterJdbcTemplate.update(UpdateQUERYDriver,namedParameters);
	   }
	
	public Driver selectFromId(long id) {
	     Driver driver = (Driver)jdbcTemplate.queryForObject(SelectQUERYDriver,new Object[]{id},new DriverRowMapper());
	     return driver;
	    }
	
	
	//testing method for boolean isWorking
	public void updateDriverWorking(Driver driver){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		 namedParameters.addValue("driver_id", driver.getDriver_id());
		 this.namedParameterJdbcTemplate.update(UpdateDriverWorkTrue,namedParameters);
	}
	
	public void updateDriverNotWorking(Driver driver){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		 namedParameters.addValue("driver_id", driver.getDriver_id());
		 this.namedParameterJdbcTemplate.update(UpdateDriverWorkFalse,namedParameters);
	}
	


	//CRUD jdbc for Car
	
	   public void registerCar(Car car) {
	        jdbcTemplate.update(CreateQUERYCar,  car.getLicensePlate(), car.getModelName(),car.getSeatNumber(),car.getLocation(), car.getIsActive(), null);
	    }
	    

		public List<Car> getAllCars() {
			return namedParameterJdbcTemplate.query(ReadQUERYCar, new CarRowMapper());
		}
		

		public List<Car> searchCarByPlate(String plate) {
			Map<String, Object> params = new HashMap<>();
			params.put("licenseplate", "%" + plate +"%");
			
			if (!Objects.isNull(plate)) {
				SearchQUERYCar = SearchQUERYCar.concat(" AND LOWER(licenseplate) LIKE LOWER(:licenseplate) ");
			}
			return namedParameterJdbcTemplate.query(SearchQUERYCar, params, new CarRowMapper());
		}

		public void deleteCar(int carID) {   
			System.out.println("delete method starts" + carID);
		    jdbcTemplate.update(DeleteQUERYCar, carID);
		}
		
		public void updateCar(Car car){
			
			   MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			   
			   namedParameters.addValue("licenseplate", car.getLicensePlate());
			   namedParameters.addValue("modelname", car.getModelName());
			   namedParameters.addValue("seatnumber", car.getSeatNumber());
			   namedParameters.addValue("location", car.getLocation());
			   namedParameters.addValue("isactive", car.getIsActive());
			   namedParameters.addValue("driving_license_number", car.getDriving_license_number());
			   namedParameters.addValue("car_id", car.getCarId());
			   
			   this.namedParameterJdbcTemplate.update(UpdateQUERYCar,namedParameters);
		   }
		
	
		
		
		//CRUD jdbc for Ride
		
		   public void registerRide(Ride ride) {
		        jdbcTemplate.update(CreateQUERYRide,  ride.getStartAddress(), ride.getFinishAddress(), ride.getStartTime(), ride.getStatus(), ride.getPrice(), null,null,null);
		    }
		    

			public List<Ride> getAllRides() {
				return namedParameterJdbcTemplate.query(ReadQUERYRide, new RideRowMapper());
			}
			

			public List<Ride> searchRideByStreet(String street) {
				Map<String, Object> params = new HashMap<>();
				params.put("startaddress", "%" + street +"%");
				
				if (!Objects.isNull(street)) {
					SearchQUERYRide = SearchQUERYRide.concat(" AND LOWER(stardaddress) LIKE LOWER(:startaddress) ");
				}
				return namedParameterJdbcTemplate.query(SearchQUERYRide, params, new RideRowMapper());
			}

			public void deleteRide(int ride_id) {   
				System.out.println("delete method starts" + ride_id);
			    jdbcTemplate.update(DeleteQUERYRide, ride_id);
			}
			
			
			public void updateRide(Ride ride){
				
				   MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				   
				   namedParameters.addValue("startaddress", ride.getStartAddress());
				   namedParameters.addValue("finishaddress", ride.getFinishAddress());
				   namedParameters.addValue("starttime", ride.getStartTime());
				   namedParameters.addValue("status", ride.getStatus());
				   namedParameters.addValue("price", ride.getPrice());
				   namedParameters.addValue("client_id", ride.getClient_id());
				   namedParameters.addValue("driver_id", ride.getDriver_id());
				   namedParameters.addValue("payment_id", ride.getClient_id());				
				   namedParameters.addValue("ride_id", ride.getRide_id());
				   
				   this.namedParameterJdbcTemplate.update(UpdateQUERYRide,namedParameters);
			   }
	
	
		//test method
		public void getDriverInfo(RowCallbackHandler driver_id){
	    	String sql = "SELECT lat, lng  FROM driver WHERE driver_id = ?";
	    	jdbcTemplate.query(sql, driver_id);
	    	System.out.println("getUsermethod");
	    }
		
		public Double getLat(int ride_id){
			System.out.println("ride_id");
			 return (double) jdbcTemplate.update(SelectLat,ride_id);	
		}
		
		public Double getLng(int ride_id){
			System.out.println("ride_id");
			 return (double) jdbcTemplate.update(SelectLng,ride_id);	
		}
		
		public void getCordinates(Ride ride){
			jdbcTemplate.update(cordinates,  ride.getLat(),ride.getLng());
		}
		
	
}
