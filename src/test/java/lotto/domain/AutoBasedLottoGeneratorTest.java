package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AutoBasedLottoGeneratorTest {

    @Test
    void generate_지정개수_생성() {
        assertThat(new AutoBasedLottoGenerator().generate(new LottoCount(5))).hasSize(5);
    }
}
