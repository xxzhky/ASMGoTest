package com.ledboot.main.bits;

import java.util.BitSet;

/**
 * @author: Fred
 * @date: 2023/11/22 21:09
 * @description: (类的描述)
 */
public class BitSetTest {

    public static void main(String[] args) {

        and();
    }

    public static void and(){
        BitSet bitset1 = new BitSet(8);
        BitSet bitset2 = new BitSet(8);

        // assign values to bitset1
        bitset1.set(0);
        bitset1.set(1);
        bitset1.set(2);
        bitset1.set(3);
        bitset1.set(4);
        bitset1.set(5);

        // assign values to bitset2
        bitset2.set(2);
        bitset2.set(4);
        bitset2.set(6);
        bitset2.set(8);
        bitset2.set(10);

        // print the sets
        System.out.println("Bitset1:" + bitset1);
        System.out.println("Bitset2:" + bitset2);

        // check if bitset1 intersects with bitset2

        System.out.println("" + bitset1.intersects(bitset2));
        bitset1.and(bitset2);
        System.out.println(bitset1);
    }

    public static void or() {
        BitSet bitsetA = new BitSet(8);
        BitSet bitsetB = new BitSet(8);

// 假设 bitsetA 包含了 [0, 1, 2, 3]，bitsetB 包含了 [1, 2, 3, 4]
        bitsetA.set(0);
        bitsetA.set(1);
        bitsetA.set(2);
        bitsetA.set(3);

        bitsetB.set(1);
        bitsetB.set(2);
        bitsetB.set(3);
        bitsetB.set(4);

        BitSet intersection = new BitSet(8); // 用于存储交集结果的 BitSet
        intersection.and(bitsetA); // 将 bitsetA 的内容加入到 intersection 中
        intersection.and(bitsetB); // 将 bitsetB 的内容加入到 intersection 中

        System.out.println("Intersection: " + intersection); // 输出交集结果，假设输出为 [1, 2, 3]
    }
}
