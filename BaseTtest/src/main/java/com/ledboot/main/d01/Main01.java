package com.ledboot.main.d01;

import java.util.Scanner;

/**
 * @author: Fred
 * 华为OD 第一题
 */
public class Main01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int[] factors = primeFactorization(a);
            System.out.println(factors[0] + " " + factors[1]);
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] primeFactorization(int num) {
        int[] factors = {-1, -1};
        if (isPrime(num)) {
            return factors;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0 && isPrime(i)) {
                int anotherFactor = num / i;
                if (isPrime(anotherFactor)) {
                    factors[0] = i;
                    factors[1] = anotherFactor;
                    return factors;
                }
                num /= i;
            }
        }
        return factors;
    }

}
