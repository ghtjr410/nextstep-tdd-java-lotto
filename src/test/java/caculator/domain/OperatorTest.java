package caculator.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperatorTest {

    @Test
    void calculate_덧셈_연산결과반환() {
        assertThat(Operator.PLUS.calculate(4, 2)).isEqualTo(6);
    }

    @Test
    void calculate_뺄셈_연산결과반환() {
        assertThat(Operator.MINUS.calculate(4, 2)).isEqualTo(2);
    }

    @Test
    void calculate_곱셈_연산결과반환() {
        assertThat(Operator.MULTIPLY.calculate(4, 2)).isEqualTo(8);
    }

    @Test
    void calculate_나눗셈_연산결과반환() {
        assertThat(Operator.DIVIDE.calculate(4, 2)).isEqualTo(2);
    }

    @ParameterizedTest(name = "입력:{0} -> {1}")
    @CsvSource({
        "'+', PLUS",
        "'-', MINUS",
        "'*', MULTIPLY",
        "'/', DIVIDE",
    })
    void from_올바른연산자문자열_생성성공(String input, Operator expected) {
        assertThat(Operator.from(input)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "입력:{0}")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "a", "++", "+ ", " *"})
    void from_올바르지않은문자열_예외발생(String input) {
        assertThatThrownBy(() -> Operator.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 연산자입니다.");
    }
}
