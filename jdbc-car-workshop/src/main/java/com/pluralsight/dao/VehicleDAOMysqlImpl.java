package com.pluralsight.dao;

import com.pluralsight.model.vehicle.Vehicle;
import com.pluralsight.model.vehicle.VehicleforDummies;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
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
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

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
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

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

    @Override
    public List<VehicleforDummies> findVehiclesByPriceRange(double min, double max) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vehicles.vin, vehicle_type
                    FROM vehicles
                    WHERE price BETWEEN ? AND ?;
                    """);
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
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

    @Override
    public List<VehicleforDummies> findVehiclesByMakeModel(String userMake, String userModel) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findByMakeModel = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vin, vehicle_type
                    FROM vehicles
                    WHERE make = ? AND model = ?
                    """);

            findByMakeModel.setString(1, userMake);
            findByMakeModel.setString(2, userModel);
            ResultSet rs = findByMakeModel.executeQuery();

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

    @Override
    public List<VehicleforDummies> findVehiclesByYear(int minYear, int maxYear) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findByYear = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vin, vehicle_type
                    FROM vehicles
                    WHERE year BETWEEN ? AND ?;
                    """);
            findByYear.setInt(1, minYear);
            findByYear.setInt(2, maxYear);
            ResultSet rs = findByYear.executeQuery();

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

    @Override
    public List<VehicleforDummies> findVehicleByColor(String userColor) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findByColor = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vin, vehicle_type
                    FROM vehicles
                    WHERE color = ?;
                    """);
            findByColor.setString(1, userColor);
            ResultSet rs = findByColor.executeQuery();

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

    @Override
    public List<VehicleforDummies> findVehicleByMileRange(int userOdometer) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findByMileage = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vin, vehicle_type
                    FROM vehicles
                    WHERE odometer = ?;
                    """);
            findByMileage.setInt(1, userOdometer);

            ResultSet rs = findByMileage.executeQuery();

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

    @Override
    public List<VehicleforDummies> findVehicleByVehicleType(String userVehicleType) {
        List<VehicleforDummies> vehicles = new ArrayList<>();
        String make, model, color, vehicleType;
        int year, odometer, vin;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findByVehicleType = connection.prepareStatement("""
                    SELECT make, model, year, color, odometer, price, vin, vehicle_type
                    FROM vehicles
                    WHERE vehicle_type = ?;
                    """);
            findByVehicleType.setString(1, userVehicleType);

            ResultSet rs = findByVehicleType.executeQuery();

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
