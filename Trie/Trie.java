
public class Trie {

    // Each node holds 26 children (a-z) and an end-of-word flag
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert: O(L) where L = word length
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    // Search: returns true only if the full word exists
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return false;
            node = node.children[idx];
        }
        return node.isEnd;
    }

    // StartsWith: returns true if any word has this prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return false;
            node = node.children[idx];
        }
        return true;
    }

    // Delete: removes the word; keeps shared prefix nodes intact
    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int depth) {
        if (node == null) return false;

        if (depth == word.length()) {
            if (!node.isEnd) return false; // word doesn't exist
            node.isEnd = false;
            return !hasChildren(node); // safe to delete this node if no children
        }

        int idx = word.charAt(depth) - 'a';
        boolean shouldDelete = deleteHelper(node.children[idx], word, depth + 1);

        if (shouldDelete) {
            node.children[idx] = null;
            // delete current node only if it's not end of another word and has no other children
            return !node.isEnd && !hasChildren(node);
        }
        return false;
    }

    private boolean hasChildren(TrieNode node) {
        for (TrieNode child : node.children)
            if (child != null) return true;
        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert
        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");
        trie.insert("ball");

        // Search
        System.out.println(trie.search("apple"));      // true
        System.out.println(trie.search("app"));        // true
        System.out.println(trie.search("ap"));         // false (prefix only)
        System.out.println(trie.search("bat"));        // true
        System.out.println(trie.search("cat"));        // false

        // StartsWith
        System.out.println(trie.startsWith("app"));    // true
        System.out.println(trie.startsWith("ba"));     // true
        System.out.println(trie.startsWith("cat"));    // false

        // Delete
        trie.delete("apple");
        System.out.println(trie.search("apple"));      // false (deleted)
        System.out.println(trie.search("app"));        // true  (prefix "app" still exists)

        trie.delete("app");
        System.out.println(trie.search("app"));        // false
        System.out.println(trie.startsWith("app"));    // false (no "app*" words left)
    }
}
