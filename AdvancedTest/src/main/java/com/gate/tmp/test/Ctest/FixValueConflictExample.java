package com.gate.tmp.test.Ctest;

/**
 * @author: Fred
 * @date: 2023/9/7 01:00
 * @description: (类的描述)
 */
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

public class FixValueConflictExample {
    public static void main(String[] args) {
        List<String> aaa = List.of("AAA", "aaa", "ASA");

        // 修复值生成逻辑，确保值的唯一性
        Map<String, String> result = aaa.stream()
                .collect(toImmutableMap(subject -> subject.toLowerCase(), Function.identity(),(n,e)->n));

        System.out.println(result);
    }
}
