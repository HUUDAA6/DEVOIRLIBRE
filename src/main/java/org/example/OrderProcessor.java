package org.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor implements Runnable {
    private List<Order> orders;
    private DatabaseHandler dbHandler;
    private FileHandler fileHandler;
    private String outputFilePath;
    private String errorFilePath;

    public OrderProcessor(List<Order> orders, DatabaseHandler dbHandler, FileHandler fileHandler, String outputFilePath, String errorFilePath) {
        this.orders = orders;
        this.dbHandler = dbHandler;
        this.fileHandler = fileHandler;
        this.outputFilePath = outputFilePath;
        this.errorFilePath = errorFilePath;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        List<Order> outputOrders = new ArrayList<>();
        List<Order> errorOrders = new ArrayList<>();

        for (Order order : orders) {
            try {
                if (dbHandler.customerExists(order.getCustomerId())) {
                    dbHandler.insertOrder(order);
                    outputOrders.add(order);
                } else {
                    errorOrders.add(order);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            fileHandler.writeJsonFile(outputOrders, outputFilePath);
            fileHandler.writeJsonError(errorOrders, errorFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Processing took " + (endTime - startTime) + " ms.");
    }
}
