package com.bussinburgers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    public static void writeReceipt(Order order) {
        try {
            Path folder = Paths.get(System.getProperty("user.dir"), "receipts");
            if (!Files.exists(folder)) Files.createDirectories(folder);

            String filename = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";
            Path file = folder.resolve(filename);

            try (FileWriter writer = new FileWriter(file.toFile())) {
                writer.write("======= Bussin Burgers Receipt =======\n\n");
                writer.write(order.toString());
                writer.write("\n----------------------------------------\n");
                writer.write("Thank you for visiting Bussin Burgers!\n");
            }

            System.out.println("Receipt saved successfully at: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save receipt: " + e.getMessage());
        }
    }
}
