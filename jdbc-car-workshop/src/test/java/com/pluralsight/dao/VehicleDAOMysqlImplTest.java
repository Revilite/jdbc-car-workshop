package com.pluralsight.dao;

import com.pluralsight.model.vehicle.VehicleforDummies;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleDAOMysqlImplTest {

    @Test
    void findAllVehicles() {
    }

    @Test
    void test_findVehiclesByDealership(){
        BasicDataSource ds = new BasicDataSource();

        ds.setUrl("jdbc:mysql://localhost:3306/dealership");
        ds.setUsername("root");
        ds.setPassword("yearup");


        VehicleDAOMysqlImpl vd = new VehicleDAOMysqlImpl(ds);

        List<VehicleforDummies> results = vd.findVehiclesByDealership(1);

        assertEquals(9, results.size());
        try {
            ds.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}