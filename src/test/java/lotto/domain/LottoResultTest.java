package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoResultTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> new LottoResult(LottoRank.FIRST, LottoRank.FOURTH)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    void 생성자_빈값입력_예외발생(List<LottoRank> inputs) {
        assertThatThrownBy(() -> new LottoResult(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 등수는 1개 이상이어야 합니다.");
    }

    @Test
    void totalPrize_총상금계산() {
        assertThat(new LottoResult(LottoRank.FIRST, LottoRank.FOURTH).totalPrize())
                .isEqualTo(new Money(2_000_000_000 + 5_000));
    }

    @Test
    void rankResultForDisplay_등수별결과표시() {
        LottoResult result = new LottoResult(LottoRank.FOURTH, LottoRank.FOURTH);

        assertThat(result.rankResultForDisplay(LottoRank.FOURTH)).isEqualTo("3개 일치 (5000원)- 2개");
    }
}
