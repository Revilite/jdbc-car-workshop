package com.pluralsight;


import com.pluralsight.view.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;


public class App {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");

        new UserInterface(dataSource).display();
    }
}
