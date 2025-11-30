package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoCountTest {

    @Test
    void 생성자_정상값_생성성공() {
        assertThatCode(() -> new LottoCount(1)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_1미만입력_예외발생() {
        assertThatThrownBy(() -> new LottoCount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구매 개수는 1개 이상이어야 합니다.");
    }
}
