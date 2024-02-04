package com.ledboot.main.d01;

import java.util.Scanner;

/**
 * 华为OD 第三题
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int wa = 0;
        int wb = 0;
        int wt = 0;
        int pa = 0;
        int pb = 0;
        try {
            wa = scanner.nextInt();
            wb = scanner.nextInt();
            wt = scanner.nextInt();
            pa = scanner.nextInt();
            pb = scanner.nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (wa < 1 || wa > 10000 || wb < 1 || wb > 10000 || wt < 0 || wt > 100000 || pa < 0 || pa > 1000 || pb < 0 || pb > 1000) {
            throw new IllegalArgumentException("Invalid input");
        }

        int maxProfit = calculateMaxProfit(wa, wb, wt, pa, pb);

        System.out.println(maxProfit);
        scanner.close();

    }


    public static int calculateMaxProfit(int wa, int wb, int wt, int pa, int pb) {
        int maxProfit = 0;
        for (int aCount = 1; aCount * wa <= wt; aCount++) {
            for (int bCount = 1; bCount * wb + aCount * wa <= wt; bCount++) {
                if (aCount * wa + bCount * wb == wt) {
                    int profit = aCount * pa + bCount * pb;
                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }
        }
        return maxProfit;
    }

    public static int calculateMaxProfit2(int wa, int wb, int wt, int pa, int pb) {
        int maxProfit = 0;
        int maxA = wt / wa;
        //int maxB = wt / wb;

        for (int aCount = 0; aCount <= maxA; aCount++) {
            int remainingWeight = wt - aCount * wa;
            int bCount = remainingWeight / wb;
            if (aCount * wa + bCount * wb == wt) {
                int profit = aCount * pa + bCount * pb;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        return maxProfit;
    }

}
