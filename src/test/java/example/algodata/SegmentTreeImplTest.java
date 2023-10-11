package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SegmentTreeImplTest {

    @DisplayName("sum을 테스트한다.")
    @ParameterizedTest
    @MethodSource("sumParameter")
    void testSum(int[] array, int[] sumZone, int expectedSum) {
        // given
        SegmentTree segmentTree = new SegmentTreeImpl(array);

        // when
        int sum = segmentTree.sum(sumZone[0], sumZone[1]);

        // then
        Assertions.assertThat(sum).isEqualTo(expectedSum);
    }

    public static Stream<Arguments> sumParameter() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4, 5}, new int[]{0, 1}, 3),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5}, new int[]{0, 2}, 6),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2}, 5)
        );
    }

    @DisplayName("update을 테스트한다.")
    @ParameterizedTest
    @MethodSource("updateParameter")
    void testUpdate(int[] array, int targetIdx, int newValue, int[] sumZone, int expectedSum) {
        // given
        SegmentTree segmentTree = new SegmentTreeImpl(array);

        // when
        segmentTree.update(targetIdx,newValue);

        // then
        Assertions.assertThat(segmentTree.sum(sumZone[0],sumZone[1])).isEqualTo(expectedSum);
    }

    public static Stream<Arguments> updateParameter() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4, 5}, 0, 7, new int[]{0, 1}, 9)
        );
    }

}