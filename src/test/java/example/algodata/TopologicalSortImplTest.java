package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortImplTest {
    @Test
    @DisplayName("성공 케이스")
    void testCorrect() {
        // given
        TopologicalSort<String> ts = new TopologicalSortImpl<>(
                List.of(Map.entry("1", "2"),
                        Map.entry("2", "3"))
        );
        // when
        // then
        Assertions.assertThat(ts.correct(List.of("1", "2", "3")))
                .isTrue();
    }

    @Test
    @DisplayName("실패 케이스")
    void testCorrectFail() {
        // given
        TopologicalSort<String> ts = new TopologicalSortImpl<>(
                List.of(Map.entry("1", "2"),
                        Map.entry("2", "3"))
        );
        // when
        // then
        Assertions.assertThat(ts.correct(List.of("3", "2", "1")))
                .isFalse();
    }

}