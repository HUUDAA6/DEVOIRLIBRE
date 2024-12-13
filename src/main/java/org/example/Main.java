package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lombok.Data;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws InterruptedException, SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler("jdbc:mysql://localhost:3306/mydb", "root", "");
        FileHandler fileHandler = new FileHandler();
        while (true) {
            long startTime = System.currentTimeMillis();

            try {
                List<Order> orders = fileHandler.parseJsonFile("data/input.json");
                OrderProcessor processor = new OrderProcessor(orders, dbHandler, fileHandler, "data/output.json", "data/error.json");
                Thread thread = new Thread(processor);
                thread.start();
                thread.join(); // Wait for the thread to finish
            } catch (IOException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Cycle completed in " + (endTime - startTime) + " ms.");

            // Sleep for 1 hour
            Thread.sleep(3600000);
        }
    }
}
