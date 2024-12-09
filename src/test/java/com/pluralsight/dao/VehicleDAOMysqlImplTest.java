package com.pluralsight.dao;

import com.pluralsight.dao.vehicles.VehicleDAOMysqlImpl;
import com.pluralsight.model.vehicle.VehicleforDummies;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

class VehicleDAOMysqlImplTest {
    static BasicDataSource ds = new BasicDataSource();

    @BeforeAll
    static void setup() {
        ds.setUrl("jdbc:mysql://localhost:3306/dealership");
        ds.setPassword("yearup");
        ds.setUsername("dealershiptest");
    }

    @Test
    void findAllVehicles() {
        VehicleDAOMysqlImpl vd = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vd.findAllVehicles();
        assertEquals(28, results.size());
    }

    @Test
    void test_findVehiclesByDealership() {
        VehicleDAOMysqlImpl vd = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vd.findVehiclesByDealership(1);
        assertEquals(9, results.size());
    }

    @Test
    void test_findVehiclesByPriceRange() {
        VehicleDAOMysqlImpl vs = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vs.findVehiclesByPriceRange(1000, 10000);
        assertEquals(8, results.size());
    }


    @Test
    void findVehiclesByMakeModel() {
        VehicleDAOMysqlImpl vs = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vs.findVehiclesByMakeModel("Toyota", "Corolla");
        assertEquals(1, results.size());
    }

    @Test
    void findVehiclesByYear() {
        VehicleDAOMysqlImpl vs = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vs.findVehiclesByYear(2000, 2024);
        assertEquals(27, results.size());
    }

    @Test
    void findVehicleByColor() {
        VehicleDAOMysqlImpl vs = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vs.findVehicleByColor("yellow");
        assertEquals(3, results.size());
    }

    @Test
    void findVehicleByMileRange() {
        VehicleDAOMysqlImpl vs = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vs.findVehicleByMileRange(50, 100000);
        assertEquals(20, results.size());
    }

    @Test
    void findVehicleByVehicleType() {
        VehicleDAOMysqlImpl vs = new VehicleDAOMysqlImpl(ds);
        List<VehicleforDummies> results = vs.findVehicleByVehicleType("Hypercar");
        assertEquals(8, results.size());
    }


    @AfterAll
    static void tearDown() {
        try {
            ds.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}