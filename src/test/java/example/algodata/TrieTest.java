package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class TrieTest {
    @Test
    @DisplayName("문자열을 Trie에 저장한다.")
    void testInsert() {
        // given
        TrieInterface trie = new Trie();
        // when
        trie.insert("seongchan");
        // then
        Assertions.assertThat(trie.contains("seongchan")).isTrue();
    }

    @Test
    @DisplayName("Trie에 저장하지 않은 문자열이 존재하는지 확인하면 false를 리턴한다.")
    void testContain() {
        // given
        TrieInterface trie = new Trie();
        // when
        trie.insert("seongchan");
        // then
        Assertions.assertThat(trie.contains("seongchancho")).isFalse();

    }
}