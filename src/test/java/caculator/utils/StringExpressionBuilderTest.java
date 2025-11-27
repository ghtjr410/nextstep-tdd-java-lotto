package caculator.utils;

import static org.assertj.core.api.Assertions.*;

import caculator.domain.Expression;
import caculator.domain.Operation;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringExpressionBuilderTest {

    @Test
    void build_정상입력_수식반환() {
        assertThat(new StringExpressionBuilder().build("2 + 3")).isEqualTo(new Expression(2, new Operation("+", "3")));
    }

    @ParameterizedTest(name = "빈값: {0}")
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void build_빈값입력_예외발생(String input) {
        assertThatThrownBy(() -> new StringExpressionBuilder().build(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 필수입니다.");
    }

    @Test
    void 생성자_올바르지않은_수식() {
        assertThatThrownBy(() -> new StringExpressionBuilder().build("1 +"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 수식입니다.");
    }
}
