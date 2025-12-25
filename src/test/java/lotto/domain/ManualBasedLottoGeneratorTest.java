package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ManualBasedLottoGeneratorTest {

    @Test
    void generate_문자열목록_생성() {
        assertThat(new ManualBasedLottoGenerator().generate(List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12")))
                .isEqualTo(List.of(new Lotto(1, 2, 3, 4, 5, 6), new Lotto(7, 8, 9, 10, 11, 12)));
    }
}
