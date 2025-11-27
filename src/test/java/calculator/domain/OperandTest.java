package calculator.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperandTest {

    @Test
    void 생성자_정수문지열_생성성공() {
        assertThat(new Operand("1")).isEqualTo(new Operand(1));
    }

    @Test
    void 생성자_정수아닌문자열_예외발생() {
        assertThatThrownBy(() -> new Operand("가")).isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void 생성자_빈문자열_예외발생(String input) {
        assertThatThrownBy(() -> new Operand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("피연산자는 필수입니다.")
                .isNotInstanceOf(NumberFormatException.class);
    }
}
