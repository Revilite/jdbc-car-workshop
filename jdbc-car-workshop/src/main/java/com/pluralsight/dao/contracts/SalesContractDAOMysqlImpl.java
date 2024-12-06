package com.pluralsight.dao.contracts;

import com.pluralsight.model.contract.SalesContract;
import com.pluralsight.model.vehicle.Dealership;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAOMysqlImpl implements SalesContractDao {
    private final DataSource dataSource;

    public SalesContractDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void saveSalesContract(SalesContract salesContract) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement saveContract = connection.prepareStatement("""
                    INSERT INTO sales_contract (sales_contract_id, sales_tax_amount, recording_fee, processing_fee, total_price, date, customer_name, customer_email, vin, is_financing)
                    VALUES
                    (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    
                    """);
            saveContract.setDouble(1, salesContract.getSalesTaxAmount());
            saveContract.setDouble(2, salesContract.getRecordingFee());
            saveContract.setDouble(3, salesContract.getProcessingFee());
            saveContract.setDouble(4, salesContract.getTotalPrice());
            saveContract.setString(5, salesContract.getDate());
            saveContract.setString(6, salesContract.getCustomerName());
            saveContract.setString(7, salesContract.getCustomerEmail());
            saveContract.setInt(8, salesContract.getVehicleSold().getVin());
            saveContract.setBoolean(9, salesContract.isFinancing());


            saveContract.executeUpdate();

        } catch (SQLException e) {
            System.out.println("VIN does not exist");
            throw new RuntimeException(e);
        }
    }
}