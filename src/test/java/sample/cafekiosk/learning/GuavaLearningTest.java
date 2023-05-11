package sample.cafekiosk.learning;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GuavaLearningTest {

    @DisplayName("list 파티셔닝")
    @Test
    void test() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 3);

        //then
        Assertions.assertThat(partition).hasSize(2)
                .isEqualTo(List.of(
                        List.of(1, 2, 3), List.of(4, 5, 6)
                ));
    }

    @DisplayName("list 파티셔닝")
    @Test
    void test2() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 4);

        //then
        Assertions.assertThat(partition).hasSize(2)
                .isEqualTo(List.of(
                        List.of(1, 2, 3, 4), List.of(5, 6)
                ));
    }


}
