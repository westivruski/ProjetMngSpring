package com.ikubinfo.suggestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.suggestion.model.Ride;

public class RideRowMapper implements RowMapper<Ride> {
	@Override
	public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ride ride = new Ride();
		
		ride.setRide_id(rs.getInt("ride_id"));
		ride.setStartAddress(rs.getString("startaddress"));
		ride.setFinishAddress(rs.getString("finishaddress"));
		ride.setStartTime(rs.getTimestamp("starttime"));
		ride.setFinishTime(rs.getTimestamp("finishtime"));
		ride.setStatus(rs.getString("status"));
		ride.setPrice(rs.getString("price"));
		ride.setLat(rs.getDouble("lat"));
		ride.setLng(rs.getDouble("lat"));
		ride.setClient_id(rs.getInt("client_id"));
		ride.setDriver_id(rs.getInt("driver_id"));
		ride.setCar_id(rs.getInt("car_id"));
		ride.setClient_id(rs.getInt("payment_id"));
		
		return ride;
	}
}
