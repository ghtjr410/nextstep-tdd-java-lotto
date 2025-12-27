package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoCountTest {

    @ParameterizedTest(name = "입력값:{0}")
    @ValueSource(ints = {0, 5})
    void 생성자_정상값_생성성공(int input) {
        assertThatCode(() -> new LottoCount(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void 생성자_빈문자열_예외발생(String input) {
        assertThatThrownBy(() -> new LottoCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 개수는 필수입니다.");
    }

    @Test
    void 생성자_음수입력_예외발생() {
        assertThatThrownBy(() -> new LottoCount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 개수는 0이상이어야 합니다.");
    }

    @ParameterizedTest(name = "원본:{0} - 차감:{1} = 결과:{2}")
    @CsvSource({"10, 3, 7", "5, 5, 0"})
    void subtract_정상값_차감(int original, int subtrahend, int expected) {
        assertThat(new LottoCount(original).subtract(new LottoCount(subtrahend)))
                .isEqualTo(new LottoCount(expected));
    }

    @Test
    void subtract_초과값_예외발생() {
        assertThatThrownBy(() -> new LottoCount(3).subtract(new LottoCount(5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 수 없습니다.");
    }

    @Test
    void validateSubtractable_정상값_예외없음() {
        assertThatCode(() -> new LottoCount(10).validateSubtractable(new LottoCount(5)))
                .doesNotThrowAnyException();
    }

    @Test
    void validateSubtractable_초과값_예외발생() {
        assertThatThrownBy(() -> new LottoCount(3).validateSubtractable(new LottoCount(5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 수 없습니다.");
    }
}
