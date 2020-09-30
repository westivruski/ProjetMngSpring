package com.ikubinfo.suggestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.suggestion.model.Admin;


public class AdminRowMapper implements RowMapper<Admin> {
	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();

		
		admin.setPassword(rs.getString("password"));
		admin.setUsername(rs.getString("username"));
		admin.setAuthorities(rs.getString("authorities"));
	  /*user.setLastName(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("phonenumber"));
		user.setAddress(rs.getString("address"));
		user.setTown(rs.getString("town"));
		user.setRegion(rs.getString("region"));
		user.setZip(rs.getString("zip"));
		user.setActive(rs.getBoolean("active"));*/

		return admin;
	}
}