package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Order> parseJsonFile(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Order.class));
    }

    public void writeJsonFile(List<Order> orders, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), orders);
    }

    public void writeJsonError(List<Order> orders, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), orders);
    }
}

