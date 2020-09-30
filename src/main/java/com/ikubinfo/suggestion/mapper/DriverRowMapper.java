package com.ikubinfo.suggestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ikubinfo.suggestion.model.Driver;



public class DriverRowMapper implements RowMapper<Driver> {
	@Override
	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		Driver driver = new Driver();

		
		driver.setDriver_id(rs.getInt("driver_id"));
		driver.setFirstName(rs.getString("firstname"));
		driver.setLastName(rs.getString("lastname"));
		driver.setUsername(rs.getString("username"));
		driver.setPassword(rs.getString("password"));
		driver.setPhoneNumber(rs.getString("phonenumber"));
		driver.setDriving_license_number(rs.getString("driving_license_number"));
		driver.setIsWorking((rs.getBoolean("isworking")));

		return driver;
	}
}