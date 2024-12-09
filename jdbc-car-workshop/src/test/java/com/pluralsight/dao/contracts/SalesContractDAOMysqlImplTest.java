package com.pluralsight.dao.contracts;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractDAOMysqlImplTest {
    static BasicDataSource dataSource = new BasicDataSource();


    @BeforeAll
    static void setup(){
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");
        dataSource.setUsername("dealershipTest");
    }

    @Test
    void findAllSalesContracts() {
    }
}