package com.pluralsight.dao.contracts;

import com.pluralsight.model.contract.SalesContract;

import java.util.List;

public interface SalesContractDao {
    List<SalesContract> findAllSales();

}
