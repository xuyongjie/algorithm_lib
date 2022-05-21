package leetcode;

public class Solution1255 {

    public static void main(String[] args) {
        Solution1255 solution1255 = new Solution1255();
        System.out.println(solution1255.maxScoreWords(new String[]{"dog", "cat", "dad", "good"},
                new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'},
                new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    int maxScore = 0;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] letterCounter = letterCount(letters);
        int[] wordScore = new int[words.length];
        int[][] wordLetterCounter = new int[words.length][];
        for (int i = 0; i < words.length; i++) {
            wordScore[i] = calculateScore(words[i], score);
            wordLetterCounter[i] = letterCount(words[i].toCharArray());
        }
        backTracking(wordLetterCounter, wordScore, letterCounter, 0, 0);
        return maxScore;
    }

    private void backTracking(int[][] wordLetterCounter, int[] wordScore, int[] letterCounter, int index, int score) {
        if (score > maxScore) {
            maxScore = score;
        }
        for (int i = index; i < wordLetterCounter.length; i++) {
            if (canSelect(wordLetterCounter[i], letterCounter)) {
                //select
                for (int j = 0; j < letterCounter.length; j++) {
                    letterCounter[j] -= wordLetterCounter[i][j];
                }
                backTracking(wordLetterCounter, wordScore, letterCounter, index + 1, score + wordScore[i]);
                for (int j = 0; j < letterCounter.length; j++) {
                    letterCounter[j] += wordLetterCounter[i][j];
                }
            }
        }
    }

    private boolean canSelect(int[] wordLetterCounter, int[] letterCounter) {
        for (int i = 0; i < wordLetterCounter.length; i++) {
            if (wordLetterCounter[i] > letterCounter[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] letterCount(char[] letters) {
        int[] letterCounter = new int[26];
        for (int i = 0; i < letters.length; i++) {
            letterCounter[letters[i] - 'a']++;
        }
        return letterCounter;
    }

    private int calculateScore(String word, int[] score) {
        int s = 0;
        for (int i = 0; i < word.length(); i++) {
            s += score[word.charAt(i) - 'a'];
        }
        return s;
    }
}
