package com.ledboot.main.simi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: Fred
 * @date: 2024/3/16 20:42
 * @description: (类的描述)
 */
public class TextMatcher {

    public static void main(String[] args) {

        String filePath = "/Users/fred/Downloads/dialog";
        String text = "hello world";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            double highestSimilarity = 0;
            String mostSimilarText = "";

            CosineSimilarity cosineSimilarity = new CosineSimilarity();

            for (String line : lines) {
                double similarity = cosineSimilarity.cosineSimilarity(line, text);
                if (similarity > highestSimilarity) {
                    highestSimilarity = similarity;
                    mostSimilarText = line;
                }
            }

            System.out.println("The most similar text is: " + mostSimilarText);
            System.out.println("The similarity is: " + highestSimilarity);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}