DROP DATABASE IF EXISTS dealership;

CREATE DATABASE dealership;
USE dealership;

CREATE TABLE `dealerships`(
	`dealership_id` INT AUTO_INCREMENT,
    `name` VARCHAR(50),
    `address` VARCHAR(50),
    `phone` VARCHAR(12),
    PRIMARY KEY (dealership_id)
    );

INSERT INTO dealerships VALUES(null, 'D & B Used Cars', '111 Old Benbrook Rd', '817-555-5555');
INSERT INTO dealerships VALUES(null, 'Scott Clark Toyota', '13052 Marie Garris Rd', '855-314-6444');
INSERT INTO dealerships VALUES(null, 'Keffer Hyundai', '9010 E Independence Blvd', '704-343-8238');


CREATE TABLE `vehicles`(
    `vin` INT,
    `year` INT,
    `make` VARCHAR(30),
    `model` VARCHAR(30),
    `vehicle_type` VARCHAR(10),
    `color` VARCHAR(10),
    `odometer` INT,
    `price` DECIMAL(10, 2),
    `sold` BOOL,
    PRIMARY KEY(vin)
);

INSERT INTO vehicles VALUES(101234, 1993, 'Porsche', 'GT3-RS', 'Hypercar', 'Yellow', 1500, 265000.00, 0);
INSERT INTO vehicles VALUES(101240, 2009, 'Toyota', 'Corolla', 'Car', 'Silver', 201000, 5000.00, 0);
INSERT INTO vehicles VALUES(101261, 2022, 'Aston Martin', 'Valhalla', 'Hypercar', 'Orange', 50, 2050000.00, 0);
INSERT INTO vehicles VALUES(101257, 2022, 'Pagani', 'Huayra', 'Hypercar', 'Red', 1000, 3050000.00, 0);
INSERT INTO vehicles VALUES(101258, 2023, 'Koenigsegg', 'Gemera', 'Hypercar', 'Red', 100, 1800000.00, 0);
INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price, sold) VALUES
(101236, 2001, 'Ford', 'Ranger', 'Truck', 'Yellow', 172544, 1995.00, 0),
(101237, 2001, 'Dodge', 'Dakota', 'Truck', 'Green', 150000, 2100.00, 0),
(101242, 2008, 'Honda', 'Pilot', 'SUV', 'Silver', 200000, 4000.00, 0),
(101243, 2008, 'Subaru', 'Outback', 'SUV', 'Red', 180000, 4500.00, 0),
(101241, 2009, 'Hyundai', 'Elantra', 'Car', 'White', 90000, 5200.00, 0),
(101238, 2012, 'Honda', 'Civic', 'Car', 'Gray', 103221, 6995.00, 0),
(101239, 2012, 'Nissan', 'Sentra', 'Car', 'Black', 85000, 8000.00, 0),
(101244, 2012, 'Toyota', 'Tacoma', 'Truck', 'Blue', 149000, 12000.00, 0),
(101254, 2014, 'Hyundai', 'Elantra', 'Car', 'Green', 70000, 12000.00, 0),
(101255, 2014, 'Volkswagen', 'Jetta', 'Car', 'Silver', 80000, 12500.00, 0),
(101245, 2012, 'Ford', 'F-150', 'Truck', 'Silver', 120000, 13000.00, 0),
(101246, 2015, 'Chevrolet', 'Malibu', 'Car', 'Black', 65000, 15000.00, 0),
(101247, 2015, 'Kia', 'Optima', 'Car', 'Red', 70000, 15500.00, 0),
(101250, 2018, 'Nissan', 'Rogue', 'SUV', 'Blue', 30000, 25000.00, 0),
(101251, 2018, 'Ford', 'Escape', 'SUV', 'Gray', 35000, 26000.00, 0),
(101252, 2017, 'Chevrolet', 'Silverado', 'Truck', 'Red', 40000, 32000.00, 0),
(101253, 2017, 'Ram', '1500', 'Truck', 'Black', 30000, 33000.00, 0),
(101248, 2020, 'Tesla', 'Model 3', 'Car', 'White', 25000, 35000.00, 0),
(129000, 2023, 'Porsche', 'GT3-RS', 'Hypercar', 'Yellow', 1500, 265000.00, 0),
(101259, 2023, 'McLaren', 'Artura', 'Hypercar', 'Blue', 100, 1850000.00, 0),
(101260, 2022, 'McLaren', 'Sabre', 'Hypercar', 'Silver', 100, 2000000.00, 0),
(101256, 2022, 'Bugatti', 'Chiron', 'Hypercar', 'Blue', 1500, 3000000.00, 0),
(378469, 2021, 'Chevrolet', 'Silverado', 'truck', 'Black', 2750, 31995.00, 0);


CREATE TABLE `inventory`(
	`inventory_id` INT AUTO_INCREMENT,
    `dealership_id` INT,
    `vin` INT,
    PRIMARY KEY(inventory_id),
    FOREIGN KEY(vin) REFERENCES vehicles(vin),
    FOREIGN KEY(dealership_id) REFERENCES dealerships(dealership_id)
);

INSERT INTO inventory VALUES (null, 1, 101234);
INSERT INTO inventory VALUES (null, 2, 101240);
INSERT INTO inventory VALUES (null, 1, 101261);
INSERT INTO inventory VALUES (null, 3, 101257);
INSERT INTO inventory VALUES (null, 3, 101258);
INSERT INTO inventory (inventory_id, dealership_id, vin)
VALUES
(NULL, 2, 101236),
(NULL, 3, 101237),
(NULL, 1, 101242),
(NULL, 2, 101243),
(NULL, 3, 101241),
(NULL, 1, 101238),
(NULL, 2, 101239),
(NULL, 3, 101244),
(NULL, 1, 101254),
(NULL, 2, 101255),
(NULL, 3, 101245),
(NULL, 1, 101246),
(NULL, 2, 101247),
(NULL, 3, 101250),
(NULL, 1, 101251),
(NULL, 2, 101252),
(NULL, 3, 101253),
(NULL, 1, 101248),
(NULL, 2, 129000),
(NULL, 3, 101259),
(NULL, 1, 101260),
(NULL, 2, 101256),
(NULL, 3, 378469);



CREATE TABLE `sales_contract`(
    `sales_contract_id` INT NOT NULL AUTO_INCREMENT,
    `sales_tax_amount` DECIMAL(10, 2),
    `recording_fee` DECIMAL(6, 2),
    `processing_fee` DECIMAL(6, 2),
    `total_price` DECIMAL(10, 2),
    `date` DATE,
    `customer_name` VARCHAR(50),
    `customer_email` VARCHAR(50),
    `vin` INT,
    `is_financing` BOOL,

    PRIMARY KEY (sales_contract_id),
    FOREIGN KEY (vin) REFERENCES vehicles(vin)

);

INSERT INTO sales_contract VALUES(null, 1525000.00, 100.00, 491.00, 21350000.00, '2024-10-31', 'Jacob Lockhart', 'prcooder165@gmail.com', 101257, 0);
INSERT INTO sales_contract VALUES(null, 2050000.00, 100.00, 630.00, 2370000.00, '2024-11-03', 'Jacob Lockhart', 'prcooder165@gmail.com', 101261, 0);
INSERT INTO sales_contract VALUES(null, 5000.00, 100.00, 115.00, 7500.00, '2024-11-10', 'Jacob Lockhart', 'prcooder165@gmail.com', 101257, 0);
