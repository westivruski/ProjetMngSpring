package com.ikubinfo.suggestion.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ikubinfo.suggestion.mapper.ClientRowMapper;
import com.ikubinfo.suggestion.mapper.RideRowMapper;
import com.ikubinfo.suggestion.model.Client;
import com.ikubinfo.suggestion.model.Ride;

@Repository
public class ClientDao  {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	
	private static final String SelectQUERY = "Select * From client ";
	private static final String SelectQUERYById = "select client_id from client where username =?";
	
	private static final String CreateQUERYClient = "insert into client(firstname,lastname,username,email,password,phonenumber) values (?,?,?,?,?,?)";
	private static final String CreateQUERYRide = "insert into ride(startaddress, finishaddress, starttime, client_id) values (?,?,?,?)";
	
	private static final String ReadQUERYRide = "select ride_id, startaddress, finishaddress, starttime, finishtime, status, price, lat, lng, client_id, driver_id, car_id, payment_id from ride where  client_id = :client_id";
	private String SearchQUERYRide = "Select startaddress, finishaddress, starttime, status, price from ride where client_id = :client_id ";
	
	private static final String DeleteQUERYRide = "delete from ride where ride_id = ? ";
	
	
	@Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	
	public void registerClient(Client client) {
			System.out.println("insert method starts" + client);
	        jdbcTemplate.update(CreateQUERYClient,  client.getFirstName(), client.getLastName(),client.getUsername(),client.getEmail(), client.getPassword(), client.getPhonenumber());
    }
	
	public int selectClientId(String name){
	int client_id = (Integer) jdbcTemplate.queryForObject(SelectQUERYById,new Object[] { name }, Integer.class);
		return client_id;
	}

	public List<Client> getAllClients() {
		return namedParameterJdbcTemplate.query(SelectQUERY, new ClientRowMapper());
	}


	
	public List<Ride> getAllRides(int client_id) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("client_id", client_id);
		
		return namedParameterJdbcTemplate.query(ReadQUERYRide, params , new RideRowMapper());
	}

	
	public List<Ride> searchRideById(int client_id) {
		Map<String, Object> params = new HashMap<>();
		params.put("client_id", client_id);
		return namedParameterJdbcTemplate.query(SearchQUERYRide, params, new RideRowMapper());
	}

	public void deleteRide(int ride_id) {   
	    jdbcTemplate.update(DeleteQUERYRide, ride_id);
	}
	
 
	 
	 public void orderRide(Ride ride){
		 jdbcTemplate.update(CreateQUERYRide,  ride.getStartAddress(), ride.getFinishAddress(), ride.getStartTime(), ride.getClient_id());
	 }
	
	 
	 public  boolean login(String username,String password){
		 String sql = "select * from client where username = '" + username + "' and password ='"+ password +"'";
		 List<Client> users = jdbcTemplate.query(sql, new ClientRowMapper());
		 if( users.size() > 0 ){
             return true;
         } else {
             return false;
         } 
	 }
	 
	 
	 
	 
	 
	 
	 
	 public void validateUser(Client user) throws IOException {
		    String sql = "select * from client where username='" + user.getUsername() + "' and password='" + user.getPassword()
		    + "'";
		    List<Client> users = jdbcTemplate.query(sql, new ClientRowMapper());
		    
		    System.out.println("ds" + user.getUsername());
		    if( users.size() > 0 ){
		    	FacesContext.getCurrentInstance().getExternalContext().redirect("success.xhtml");
		    }else {
		    	FacesContext.getCurrentInstance().getExternalContext().redirect("failure.xhtml");
		    }
		    }
	
	 /*	  public void addMarker() {
	 		
	 	        Marker marker = new Marker(new LatLng(order.getLat(),order.getLng()));
	 	        emptyModel.addOverlay(marker);
	 	       
	 	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + order.getLat() + ", Lng:" + order.getLng()));
	 	    }*/
		
	
	/* public List<String> getRoleNames(Long userId) {
	        String sql = "Select authority " //
	                + " from users " //
	                + " where userid = ? ";
	 
	        Object[] params = new Object[] { userId };
	 
	        List<String> roles = this.jdbcTemplate.queryForList(sql, params, String.class);
	     System.out.println("checking authority from dao");
	 
	        return roles;
	    }


	 public  boolean login(String username, String password) {
		 String sql = "select * from users where firstname='" + username + "' and password='" + password + "'";
	                   
		 List<Client> users = jdbcTemplate.query(sql, new ClientRowMapper());
	           
		 if( users.size() > 0 ){
	                return true;
	            } else {
	                return false;
	            }  
	    }
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String sql = "select * from users where firstname='" + username + "'";
		List<UserModel> users = jdbcTemplate.query(sql, new UserRowMapper());
		return (UserDetails) users;
	}
	
    

	public Client getUserInfo(String username){
    	String sql = "SELECT username, password  FROM "+
    			     "users WHERE "+
    			     "username = ?";
    	Client userInfo = (Client)jdbcTemplate.queryForObject(sql, new Object[]{username},new ClientRowMapper());
    	System.out.println("getUsermethod" + userInfo);
    	return userInfo;
    }
	*/

	
}