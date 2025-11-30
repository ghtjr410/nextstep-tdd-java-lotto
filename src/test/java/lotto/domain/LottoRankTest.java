package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoRankTest {

    @ParameterizedTest(name = "일치갯수:{0}, 등수:{1}")
    @CsvSource({"6, FIRST", "5, SECOND", "4, THIRD", "3, FOURTH", "2, MISS", "0, MISS"})
    void from_일치갯수로_등수반환(int input, LottoRank expected) {
        assertThat(LottoRank.from(input)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "일치갯수:{0} -> MISS")
    @ValueSource(ints = {-1, 7})
    void from_범위초과_MISS반환(int input) {
        assertThat(LottoRank.from(input)).isEqualTo(LottoRank.MISS);
    }

    @ParameterizedTest(name = "등수:{0}, 개수:{1} -> {2}원")
    @CsvSource({
        "THIRD, 1, 50000",
        "THIRD, 2, 100000",
        "MISS, 1, 0",
        "MISS, 100, 0",
    })
    void totalPrize_등수별_총상금계산(LottoRank rank, int count, long expected) {
        assertThat(rank.totalPrize(count).value()).isEqualTo(expected);
    }

    @Test
    void matchCountDisplay_일치개수_표시() {
        assertThat(LottoRank.FIRST.matchCountDisplay()).isEqualTo("6개 일치");
    }

    @Test
    void prizeDisplay_상금_표시() {
        assertThat(LottoRank.FIRST.prizeDisplay()).isEqualTo("2000000000원");
    }
}
