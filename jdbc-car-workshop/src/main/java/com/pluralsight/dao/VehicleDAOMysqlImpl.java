package com.pluralsight.dao;

import com.pluralsight.model.vehicle.Vehicle;
import com.pluralsight.model.vehicle.VehicleforDummies;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOMysqlImpl implements VehicleDao {


    private final DataSource dataSource;

    public VehicleDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<VehicleforDummies> findAllVehicles() {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make;
        String model;
        int year;
        String color;
        int odometer;
        double price;
        int vin;
        String vehicleType;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findAllVehicles = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vin, vehicle_type
                    FROM vehicles;
                    """);
            findAllVehicles.executeQuery();

            ResultSet rs = findAllVehicles.getResultSet();

            while (rs.next()) {
                make = rs.getString("make");
                model = rs.getString("model");
                year = rs.getInt("year");
                color = rs.getString("color");
                odometer = rs.getInt("odometer");
                price = rs.getDouble("price");
                vin = rs.getInt("vin");
                vehicleType = rs.getString("vehicle_type");
                vehicles.add(new VehicleforDummies(vin, year, make, model, vehicleType, color, odometer, price));
            }

            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<VehicleforDummies> findVehiclesByDealership(int id) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make;
        String model;
        int year;
        String color;
        int odometer;
        double price;
        int vin;
        String vehicleType;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vehicles.vin, vehicle_type
                    FROM inventory
                    JOIN vehicles ON inventory.vin = vehicles.vin
                    WHERE dealership_id = ?;
                    """);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                make = rs.getString("make");
                model = rs.getString("model");
                year = rs.getInt("year");
                color = rs.getString("color");
                odometer = rs.getInt("odometer");
                price = rs.getDouble("price");
                vehicleType = rs.getString("vehicle_type");
                vin = rs.getInt("vin");
                vehicles.add(new VehicleforDummies(vin, year, make, model, vehicleType, color, odometer, price));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;

    }

}
