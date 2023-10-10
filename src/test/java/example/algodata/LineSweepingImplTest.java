package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class LineSweepingImplTest {
    @Test
    @DisplayName("Line sweeping 알고리즘으로 '그은 선의 총 길이'을 구한다.")
    void testLineSweeping() {
        // given
        int[][] input = new int[4][2];
        input[0] = new int[]{1, 3};
        input[1] = new int[]{2, 5};
        input[2] = new int[]{3, 5};
        input[3] = new int[]{6, 7};
        LineSweeping lineSweeping = new LineSweepingImpl(input);
        // when
        int length = lineSweeping.getLength();
        // then
        Assertions.assertThat(length).isEqualTo(5);
    }

}