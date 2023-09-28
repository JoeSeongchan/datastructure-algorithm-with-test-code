package example.algodata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BinarySearchTest {

    @Test
    @DisplayName("찾고자 하는 값이 리스트 안에 있으면, 그 값의 index를 정확히 찾는다.")
    void test1() {
        // given
        List<Integer> list = List.of(1,2,3,4);
        BinarySearch binarySearch = new BinarySearch(list);
        // when
        int idx = binarySearch.search(3);
        // then
        assertThat(idx).isEqualTo(2);
    }

    @Test
    @DisplayName("찾고자 하는 값이 리스트에 없으면, 그보다 큰 값 중에서 가장 index가 작은 값의 index를 반환한다. (총 개수: 짝수)")
    void test2() {
        // given
        List<Integer> list = List.of(1,3,4,5);
        BinarySearch binarySearch = new BinarySearch(list);
        // when
        int idx = binarySearch.search(2);
        // then
        assertThat(idx).isEqualTo(1);
    }


    @Test
    @DisplayName("찾고자 하는 값이 리스트에 없으면, 그보다 큰 값 중에서 가장 index가 작은 값의 index를 반환한다. (총 개수: 홀수)")
    void test3() {
        // given
        List<Integer> list = List.of(1,3,5);
        BinarySearch binarySearch = new BinarySearch(list);
        // when
        int idx = binarySearch.search(2);
        // then
        assertThat(idx).isEqualTo(1);
    }

    @Test
    @DisplayName("찾고자 하는 값이 리스트의 가장 작은 값보다 작은 경우, 0을 리턴한다.")
    void test4() {
        // given
        List<Integer> list = List.of(1,3,5);
        BinarySearch binarySearch = new BinarySearch(list);
        // when
        int idx = binarySearch.search(0);
        // then
        assertThat(idx).isEqualTo(0);
    }

    @Test
    @DisplayName("찾고자 하는 값이 리스트의 가장 큰 값보다 큰 경우, MAX_VALUE를 리턴한다.")
    void test5() {
        // given
        List<Integer> list = List.of(1,3,5);
        BinarySearch binarySearch = new BinarySearch(list);
        // when
        int idx = binarySearch.search(6);
        // then
        assertThat(idx).isEqualTo(Integer.MAX_VALUE);
    }
}