package com.prenosrobe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prenosrobe.data.UserVehicle;

public interface UserVehicleRepository extends CrudRepository<UserVehicle, Integer>
{
	List<UserVehicle> findByUserId(Integer userId);

	@Query(value = "SELECT * FROM user_vehicle WHERE user_id = ? AND vehicle_id = ?;", nativeQuery = true)
	UserVehicle findByUserIdAndVehicleId(Integer userId, Integer vehicleId);
}
