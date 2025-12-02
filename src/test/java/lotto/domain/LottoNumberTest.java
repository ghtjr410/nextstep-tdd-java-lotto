package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoNumberTest {
    @ParameterizedTest(name = "정상값:{0}")
    @ValueSource(ints = {1, 45})
    void of_정상값_생성성공(int input) {
        assertThatCode(() -> LottoNumber.of(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "입력값: {0}")
    @ValueSource(ints = {0, 46})
    void of_잘못된값_예외발생(int input) {
        assertThatThrownBy(() -> LottoNumber.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45사이여야 합니다.");
    }

    @ParameterizedTest(name = "정상값:{0}")
    @ValueSource(ints = {1, 45})
    void 생성자_정상값_생성성공(int input) {
        assertThatCode(() -> new LottoNumber(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "입력값: {0}")
    @ValueSource(ints = {0, 46})
    void 생성자_잘못된값_예외발생(int input) {
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45사이여야 합니다.");
    }
}
