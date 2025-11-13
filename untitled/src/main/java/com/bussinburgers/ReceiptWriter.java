package com.bussinburgers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ReceiptWriter {

    public static void writeReceipt(Order order) {
        try {
            Path folder = Path.of("receipts");
            if (!Files.exists(folder)) Files.createDirectory(folder);

            String filename = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";
            Path file = folder.resolve(filename);

            try (FileWriter writer = new FileWriter(file.toFile())) {
                writer.write("Bussin Burgers - Receipt\n");
                writer.write(order.toString());
                writer.write(System.lineSeparator());
                writer.write("Thank you for visiting Bussin Burgers!");
            }
            System.out.println("Receipt saved: " + file.toString());
        } catch (IOException e) {
            System.err.println("Failed to save receipt: " + e.getMessage());
        }
    }
}
