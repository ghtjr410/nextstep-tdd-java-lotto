package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ManualLottoCountTest {

    @ParameterizedTest(name = "입력값:{0}")
    @ValueSource(ints = {0, 5})
    void 생성자_문자열_생성성공(int input) {
        assertThatCode(() -> new ManualLottoCount(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void 생성자_빈문자열_예외발생(String input) {
        assertThatThrownBy(() -> new ManualLottoCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 필수입니다.");
    }

    @Test
    void 생성자_음수문자열_예외발생() {
        assertThatThrownBy(() -> new ManualLottoCount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 0 이상이어야 합니다.");
    }
}
