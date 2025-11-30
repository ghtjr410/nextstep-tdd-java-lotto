package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoPurchaseAmountTest {

    @Test
    void 생성자_문자열입력_생성성공() {
        assertThatCode(() -> new LottoPurchaseAmount("1000")).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "입력:{0}원")
    @ValueSource(ints = {0, 999})
    void 생성자_최소금액미만_예외발생(int input) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1,000원 이상이어야 합니다.");
    }

    @ParameterizedTest(name = "입력:{0}원")
    @ValueSource(ints = {1_001, 1_500, 1_999})
    void 생성자_1000원단위위반_예외발생(int input) {
        assertThatThrownBy(() -> new LottoPurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1,000원 단위여야 합니다.");
    }
}
