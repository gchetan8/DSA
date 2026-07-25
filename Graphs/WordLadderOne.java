import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderOne {

    // Internal class mapping the active structural word state alongside its level step depth
    static class WordTuple {
        String word;
        int steps;
        public WordTuple(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    public static int ladderLength(String startWord, String targetWord, List<String> wordList) {
        // Step 1: Push all target word entries inside a Set container for fast lookup
        Set<String> st = new HashSet<>(wordList);

        // Base case shortcut optimization: If target string doesn't exist in our dictionary framework, return 0
        if (!st.contains(targetWord)) return 0;

        // Queue configuration holding our compound WordTuple states
        Queue<WordTuple> q = new LinkedList<>();

        // Seed the starting word into the queue at step depth = 1
        q.offer(new WordTuple(startWord, 1));

        // Clean optimization: Remove word from the set to avoid circular lookups
        st.remove(startWord);

        // Step 2: Execute level-order BFS paths mutation scanning
        while (!q.isEmpty()) {
            WordTuple curr = q.poll();
            String word = curr.word;
            int steps = curr.steps;

            // Direct check condition: If we successfully pop the targetWord, return total steps
            if (word.equals(targetWord)) {
                return steps;
            }

            int wordLen = word.length();


            // Generate all potential single-letter permutations for the current word string
            for (int i = 0; i < wordLen; i++) {
                char[] replacedWordArray = word.toCharArray();

                // Mutate the active index slot from character 'a' to 'z'
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    replacedWordArray[i] = ch;
                    String newWord = new String(replacedWordArray);

                    // If the mutated string matches an item inside our available word dictionary set:
                    if (st.contains(newWord)) {
                        st.remove(newWord); // Clear out instantly to serve as a visited block
                        q.offer(new WordTuple(newWord, steps + 1));
                    }
                }

            }
        }

        // Return 0 if the word queue completely empties without establishing a valid path loop connection
        return 0;
    }

    public static void main(String[] args) {
        String startWord = "hit";
        String targetWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");

        int ans = ladderLength(startWord, targetWord, wordList);
        System.out.println("Shortest transformation sequence length: " + ans);
        // Expected Output: 5
    }
}