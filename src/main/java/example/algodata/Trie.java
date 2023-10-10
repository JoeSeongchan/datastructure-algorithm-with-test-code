package example.algodata;

import java.util.HashMap;
import java.util.Map;

public class Trie implements TrieInterface{
    static class Node {
        private final Map<Character,Node> map;
        private boolean isFinal;

        public Node() {
            this.map = new HashMap<>();
        }

        public Map<Character, Node> getMap() {
            return map;
        }

        public boolean isFinal() {
            return isFinal;
        }

        public void setFinal(boolean aFinal) {
            isFinal = aFinal;
        }
    }

    private final Node rootNode;

    public Trie() {
        rootNode = new Node();
    }

    @Override
    public void insert(String str) {
        Node node = rootNode;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            node = node.getMap().computeIfAbsent(c, c1 -> new Node());
        }
        node.setFinal(true);
    }

    @Override
    public boolean contains(String str) {
        Node node = rootNode;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(!node.getMap().containsKey(c)) return false;
            node = node.getMap().get(c);
        }
        return node.isFinal();
    }
}
