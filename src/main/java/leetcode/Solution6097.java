package leetcode;

import java.util.Arrays;

public class Solution6097 {

    public static void main(String[] args) {
        System.out.println(new Solution6097().discountPrices("there are $1 $2 and 5$ candies in the shop", 50));
    }

    public String discountPrices(String sentence, int discount) {
        String[] splited = sentence.split(" ");
        for (int i = 0; i < splited.length; i++) {
            splited[i] = parseWord(splited[i], discount);
        }
        return String.join(" ", Arrays.asList(splited));
    }

    private String parseWord(String word, int discount) {
        if (!word.startsWith("$")) {
            return word;
        }
        try {
            double price = Double.parseDouble(word.substring(1));
            double newPrice = price * (100 - discount) / 100.0;
            return String.format("$%.2f", newPrice);
        } catch (Exception e) {
            return word;
        }
    }
}
