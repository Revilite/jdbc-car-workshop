package com.pluralsight.dao.contracts;

import com.pluralsight.model.vehicle.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAOMysqlImpl {
    private final DataSource dataSource;

    public SalesContractDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> findAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String name;
        String phone;
        String address;
        int id;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement getAllDealerships = connection.prepareStatement("""
                    SELECT * 
                    FROM dealerships;
                    """);
            getAllDealerships.executeQuery();

            ResultSet rs = getAllDealerships.getResultSet();

            while (rs.next()) {
                id = rs.getInt("dealership_id");
                name = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getString("phone");
                dealerships.add(new Dealership(id, name, address, phone));
            }

            return dealerships;

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
