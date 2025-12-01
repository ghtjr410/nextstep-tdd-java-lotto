package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoResultTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> new LottoResult(LottoRank.FIRST, LottoRank.FOURTH)).doesNotThrowAnyException();
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
