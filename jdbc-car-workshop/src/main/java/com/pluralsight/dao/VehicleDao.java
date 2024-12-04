package com.pluralsight.dao;

import com.pluralsight.model.vehicle.Vehicle;
import com.pluralsight.model.vehicle.VehicleforDummies;

import java.util.List;


public interface VehicleDao {
    List<VehicleforDummies> findAllVehicles();
}
