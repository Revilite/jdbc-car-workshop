package com.pluralsight.dao.contracts;

import com.pluralsight.model.contract.LeaseContract;

import java.util.List;

public interface LeaseContractDao {
    void addLeaseContract(LeaseContract contract);
    List<LeaseContract> findAllLeaseContracts();
}
