package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoReturnRateTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> new LottoReturnRate(0)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_음수입력_예외발생() {
        assertThatThrownBy(() -> new LottoReturnRate(-0.1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수익률은 0 이상이어야 합니다.");
    }

    @Test
    void returnRateForDisplay_수익률_표시() {
        assertThat(new LottoReturnRate(1.5).returnRateForDisplay()).isEqualTo("총 수익률은 1.50입니다.%n".formatted());
    }
}
