package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FenwickTreeImplTest {
    @ParameterizedTest
    @DisplayName("sum 메소드를 테스트합니다.")
    @MethodSource("sumParameter")
    void sumTest(int[] array, int[] sumZone, int expectedSum) {
        // given
        FenwickTree fenwickTree = new FenwickTreeImpl(array);
        // when
        int sum = fenwickTree.sum(sumZone[0], sumZone[1]);
        // then
        Assertions.assertThat(sum).isEqualTo(expectedSum);
    }

    static Stream<Arguments> sumParameter() {
        return Stream.of(
                Arguments.arguments(
                        new int[]{1, 2, 3, 4, 5}, new int[]{1, 3}, 6
                )
        );
    }

}