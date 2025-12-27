package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoNumberParserTest {

    @Test
    void parse_문자열_로또넘버생성() {
        assertThat(new LottoNumberParser().parse("1, 2, 3, 4, 5, 6")).isEqualTo(createLottoNumbers(1, 2, 3, 4, 5, 6));
    }

    private List<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers).mapToObj(LottoNumber::of).toList();
    }
}
