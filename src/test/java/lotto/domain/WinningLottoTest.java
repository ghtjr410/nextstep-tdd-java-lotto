package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinningLottoTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), 7)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_보너스번호_중복_예외발생() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
