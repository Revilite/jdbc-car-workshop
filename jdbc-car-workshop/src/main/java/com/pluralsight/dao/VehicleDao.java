package com.pluralsight.dao;

import com.pluralsight.model.vehicle.VehicleforDummies;

import java.util.List;


public interface VehicleDao {
    List<VehicleforDummies> findAllVehicles();
    List<VehicleforDummies> findVehiclesByDealership(int id); //Not needed
    List<VehicleforDummies> findVehiclesByPriceRange(double min, double max);
    List<VehicleforDummies>
}
