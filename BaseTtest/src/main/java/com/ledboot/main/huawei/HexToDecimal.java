package com.ledboot.main.huawei;

/**
 * @author: Fred
 * @date: 2024/1/18 19:46
 * @description: (类的描述)
 */
import java.util.Scanner;

public class HexToDecimal {

    /**
     * Converts a hexadecimal string to a decimal integer.
     *
     * @param hexString the hexadecimal string
     * @return the decimal representation of the hexadecimal string
     */
    public static int hexToDecimal(String hexString) {
        return Integer.parseInt(hexString, 16);
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a hexadecimal number:");
//        String hexInput = scanner.nextLine();

        try {
            int decimalOutput = hexToDecimal("0xAA");
            System.out.println("Decimal representation: " + decimalOutput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid hexadecimal number.");
        }
    }
}
