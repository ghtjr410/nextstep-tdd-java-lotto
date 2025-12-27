package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CombinedLottosGeneratorTest {

    @Test
    void 여러_Generator를_조합하여_로또를_생성한다() {
        LottosGenerator gen1 = () -> new Lottos(new Lotto(1, 2, 3, 4, 5, 6));
        LottosGenerator gen2 = () -> new Lottos(new Lotto(7, 8, 9, 10, 11, 12));

        CombinedLottosGenerator combined = new CombinedLottosGenerator(List.of(gen1, gen2));

        assertThat(combined.generate().size()).isEqualTo(2);
    }
}
