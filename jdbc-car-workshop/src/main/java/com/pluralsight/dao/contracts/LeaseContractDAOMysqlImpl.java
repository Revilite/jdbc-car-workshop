package com.pluralsight.dao.contracts;

import com.pluralsight.model.contract.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseContractDAOMysqlImpl implements LeaseContractDao {
    private DataSource dataSource;

    public LeaseContractDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addLeaseContract(LeaseContract contract) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement addLeaseContract = connection.prepareStatement("""
                    INSERT INTO `lease_contracts`(`date`, `customer_name`, `customer_email`, `vin`, `total_price`, `expected_ending_value`, `lease_fee`, `monthly_payment`)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                    """);
            addLeaseContract.setString(1, contract.getDate());
            addLeaseContract.setString(2, contract.getCustomerName());
            addLeaseContract.setString(3, contract.getCustomerEmail());
            addLeaseContract.setInt(4, contract.getVehicleSold().getVin());
            addLeaseContract.setDouble(5, contract.getTotalPrice());
            addLeaseContract.setDouble(6, contract.getExpectedEndingValue());
            addLeaseContract.setDouble(7, contract.getLeaseFee());
            addLeaseContract.setDouble(8, contract.getMonthlyPayment());

            PreparedStatement updateVehicle = connection.prepareStatement("""
                    UPDATE vehicles
                    set sold = 1
                    WHERE vin = ?;
                    """);

            updateVehicle.setInt(1, contract.getVehicleSold().getVin());

            addLeaseContract.executeUpdate();
            updateVehicle.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
