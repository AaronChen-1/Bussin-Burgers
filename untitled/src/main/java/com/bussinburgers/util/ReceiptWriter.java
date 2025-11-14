package com.bussinburgers.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptWriter {

    private static final String RECEIPT_FOLDER = "receipts";

    public static void writeReceipt(String receiptText) {

        try {
            // Create receipts folder if it doesn't exist
            File folder = new File(RECEIPT_FOLDER);
            if (!folder.exists()) {
                folder.mkdir();
            }

            // Filename based on current date/time
            String fileName = TimeUtil.getTimestamp() + ".txt";
            File receiptFile = new File(folder, fileName);

            // Write the receipt contents
            FileWriter writer = new FileWriter(receiptFile);
            writer.write(receiptText);
            writer.close();

            System.out.println("Receipt saved as: " + receiptFile.getName());

        } catch (IOException e) {
            System.out.println("Error writing receipt file.");
            e.printStackTrace();
        }
    }
}
