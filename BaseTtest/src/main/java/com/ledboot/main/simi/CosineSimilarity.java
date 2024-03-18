package com.ledboot.main.simi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Fred
 * @date: 2024/3/16 20:52
 * @description: (类的描述)
 */
public class CosineSimilarity {
    public double cosineSimilarity(String text1, String text2) {
        Map<String, Integer> wordFrequencies1 = getWordFrequencies(text1);
        Map<String, Integer> wordFrequencies2 = getWordFrequencies(text2);

        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;

        for (String word : wordFrequencies1.keySet()) {
            int freq1 = wordFrequencies1.getOrDefault(word, 0);
            int freq2 = wordFrequencies2.getOrDefault(word, 0);

            dotProduct += freq1 * freq2;
            norm1 += Math.pow(freq1, 2);
        }

        for (int freq : wordFrequencies2.values()) {
            norm2 += Math.pow(freq, 2);
        }
        if (norm1 == 0 || norm2 == 0) {
            return 0;
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));

    }

    public Map<String, Integer> getWordFrequencies(String text) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        return wordFrequencies;
    }

}
