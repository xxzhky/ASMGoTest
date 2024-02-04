package com.ledboot.main.huawei;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int i = 0, cc = 0;
        List<Integer> numbers = new ArrayList<>();
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            i++;
            int a = in.nextInt();
            if (i == 1) {
                cc= a;
                continue;
            }
            numbers.add(a);
            if (i == a) {
                break;
            }
        }
        if (!numbers.isEmpty()){
            removeDuplicatesAndSort(numbers)
                    .forEach(System.out::println);
        }


    }
    public static List<Integer> removeDuplicatesAndSort(List<Integer> numbers) {
        // Using a HashSet to remove duplicates
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int number : numbers) {
            uniqueNumbers.add(number);
        }

        // Converting the Set to a List and sorting
        List<Integer> sortedList = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedList);

        return sortedList;
    }

}