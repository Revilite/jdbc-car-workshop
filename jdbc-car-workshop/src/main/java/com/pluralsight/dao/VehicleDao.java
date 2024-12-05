package com.pluralsight.dao;

import com.pluralsight.model.vehicle.VehicleforDummies;

import java.util.List;


    public interface VehicleDao {
        List<VehicleforDummies> findAllVehicles();
        List<VehicleforDummies> findVehiclesByDealership(int id); //Not needed
        List<VehicleforDummies> findVehiclesByPriceRange(double min, double max);
        List<VehicleforDummies> findVehiclesByMakeModel(String make, String model);
        List<VehicleforDummies> findVehiclesByYear(int minYear, int maxYear);
        List<VehicleforDummies> findVehicleByColor(String color);
        List<VehicleforDummies> findVehicleByMileRange(int odometer);
        List<VehicleforDummies> findVehicleByVehicleType(String vehicleType);


}
