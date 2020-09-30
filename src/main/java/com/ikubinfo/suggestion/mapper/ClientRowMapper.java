package com.ikubinfo.suggestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.suggestion.model.Client;



public class ClientRowMapper implements RowMapper<Client> {
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Client client = new Client();

		
		client.setClient_id(rs.getInt("client_id"));
		client.setFirstName(rs.getString("firstname"));
		client.setLastName(rs.getString("lastname"));
		client.setUsername(rs.getString("username"));
		client.setEmail(rs.getString("email"));
		client.setPassword(rs.getString("password"));
		client.setPhonenumber(rs.getString("phonenumber"));
		client.setCardnumber(rs.getInt("cardnumber"));
		

		return client;
	}
}