package example.algodata;

//https://velog.io/@cgw0519/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%ED%8A%B8%EB%9D%BC%EC%9D%B4
import java.util.HashMap;
import java.util.Map;

/**
 * Trie는 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조
 * 자동 완성, 사전 검색 등 문자열을 탐색하는 데 특화되어 있는 자료구조
 * == Radix Tree, Prefix Tree, Retrieval Tree
 * 문자열을 탐색할 때 하나하나씩 전부 비교하면서 탐색하는 것보다 시간 복잡도 측면에서 훨씬 더 효율적이다.
 * 각 노드에서 자식들에 대한 포인터를 배열로 모두 저장하고 있음 => 공간복잡도 큼
 *
 * Head의 자식 노드에 알파벳을 추가한다.
 * 자식 노드를 타고 내려가다가 중간에 끝에 다다르면 Data에 값을 추가한다.
 *
 */
public class Trie{
    static class Node{
        private Map<Character, Node> children = new HashMap<>();
        private boolean isLast;

        public Map<Character,Node> getChildren() {
            return children;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            this.isLast = last;
        }
    }

    private final Node rootNode;

    public Trie() {
        rootNode = new Node();
    }

    void insert(String word) {
        Node node = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            node = node.getChildren().computeIfAbsent(word.charAt(i), c -> new Node());
            node.setLast(true);
        }
    }

    boolean contains(String word) {
        Node node = this.rootNode;
        char cur;
        for (int i = 0; i < word.length(); i++) {
            cur = word.charAt(i);
            if (!node.getChildren().containsKey(cur)) {
                return false;
            }
            node = node.getChildren().get(cur);
        }
        return node.isLast;
    }
}
