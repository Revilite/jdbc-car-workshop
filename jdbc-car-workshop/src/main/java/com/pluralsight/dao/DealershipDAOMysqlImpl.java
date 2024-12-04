package com.pluralsight.dao;


import com.pluralsight.model.vehicle.Dealership;
import com.pluralsight.model.vehicle.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DealershipDAOMysqlImpl implements DealershipDAO {
    private final DataSource dataSource;

    public DealershipDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Dealership findDealershipById(int id) {
        String name = "";
        String phone = "";
        String address = "";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement getDealershipById = connection.prepareStatement("""
                       SELECT * 
                       FROM dealerships
                       WHERE dealership_id = ?;
                    """);
            getDealershipById.setInt(1, id);
            ResultSet rs = getDealershipById.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getString("phone");
            }

            return new Dealership(id, name, phone, address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dealership> findAllDealerships() {
        return List.of();
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return List.of();
    }
}
