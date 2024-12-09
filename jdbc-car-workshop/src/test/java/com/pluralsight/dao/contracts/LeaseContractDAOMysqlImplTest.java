package com.pluralsight.dao.contracts;

import com.pluralsight.model.contract.LeaseContract;
import com.pluralsight.model.contract.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.dgc.Lease;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaseContractDAOMysqlImplTest {

    static BasicDataSource dataSource = new BasicDataSource();

    @BeforeAll
    static void setup() {
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");
        dataSource.setUsername("dealershiptest");
        dataSource.setPassword("yearup");
    }

    @Test
    void findAllLeaseContracts() {
        LeaseContractDAOMysqlImpl leaseDB = new LeaseContractDAOMysqlImpl(dataSource);
        List<LeaseContract> contracts = leaseDB.findAllLeaseContracts();
        assertEquals(5, contracts.size());
    }

    @AfterAll
    static void tearDown() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}