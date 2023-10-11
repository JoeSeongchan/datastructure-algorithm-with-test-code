package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class DijkstraImplTest {
    @ParameterizedTest
    @DisplayName("가장 빠른 길의 길이를 구하는 메소드를 테스트한다.")
    @MethodSource("parameterFindShortestRouteLength")
    void testFindShortestRouteLength(List<int[]>[] rawGraph, int startIdx,int endIdx,int expectedDist) {
        // given
        Dijkstra dijkstra = new DijkstraImpl(rawGraph, startIdx);
        // when
        int shortestRouteLength = dijkstra.findShortestRouteLength(endIdx);
        // then
        Assertions.assertThat(shortestRouteLength).isEqualTo(expectedDist);
    }

    static Stream<Arguments> parameterFindShortestRouteLength() {
        return Stream.of(Arguments.arguments(
                new ArrayList[]{
                        // 0
                        new ArrayList<>(List.of(
                                new int[]{1,2},
                                new int[]{2,5},
                                new int[]{3,3}
                        )),
                        // 1
                        new ArrayList<>(List.of(
                                new int[]{0,2},
                                new int[]{2,7},
                                new int[]{5,10}
                        )),
                        // 2
                        new ArrayList<>(List.of(
                                new int[]{0,5},
                                new int[]{1,7},
                                new int[]{3,1},
                                new int[]{4,2},
                                new int[]{5,5}
                        )),
                        // 3
                        new ArrayList<>(List.of(
                                new int[]{0,3},
                                new int[]{2,1},
                                new int[]{4,7}
                        )),
                        // 4
                        new ArrayList<>(List.of(
                                new int[]{2,2},
                                new int[]{3,7},
                                new int[]{5,2}
                        )),
                        // 5
                        new ArrayList<>(List.of(
                                new int[]{1,10},
                                new int[]{2,5},
                                new int[]{4,2}
                        ))
                },0,5,8
        ));
    }

}