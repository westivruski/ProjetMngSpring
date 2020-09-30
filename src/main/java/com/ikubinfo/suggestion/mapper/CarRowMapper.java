package com.ikubinfo.suggestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ikubinfo.suggestion.model.Car;

public class CarRowMapper implements RowMapper<Car> {
	@Override
	public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
		Car car = new Car();
		
		car.setCarId(rs.getInt("car_id"));
		car.setLicensePlate(rs.getString("licensePlate"));
		car.setModelName(rs.getString("modelName"));
		car.setSeatNumber(rs.getInt("seatNumber"));
		car.setLocation(rs.getString("location"));
		car.setIsActive(rs.getBoolean("isactive"));
		car.setDriving_license_number(rs.getString("driving_license_number"));

		return car;
	}
}
