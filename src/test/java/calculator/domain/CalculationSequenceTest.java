package calculator.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculationSequenceTest {

    @Test
    void 생성자_올바른값_생성성공() {
        assertThatCode(() -> new CalculationSequence(List.of(new Operation("+", "2"))));
    }

    @ParameterizedTest(name = "빈값: {0}")
    @NullAndEmptySource
    void 생성자_null_예외발생(List<Operation> inputs) {
        assertThatThrownBy(() -> new CalculationSequence(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("계산 순서는 1개 이상의 연산이 필요합니다.");
    }
}
