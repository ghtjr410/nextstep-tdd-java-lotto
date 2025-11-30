package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> createLotto(1, 2, 3, 4, 5, 6)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    void 생성자_빈값입력_예외발생(Set<LottoNumber> inputs) {
        assertThatThrownBy(() -> new Lotto(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또번호는 필수입니다.");
    }

    @ParameterizedTest(name = "{0}개")
    @ValueSource(ints = {5, 7})
    void 생성자_잘못된_개수_예외발생(int count) {
        assertThatThrownBy(() -> createLotto(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또번호는 6개여야 합니다.");
    }

    @Test
    void countMatches_다른로또_일치개수반환() {
        assertThat(createLotto(1, 2, 3, 4, 5, 6).countMatches(createLotto(1, 2, 3, 4, 5, 6)))
                .isEqualTo(6);
    }

    private LottoV2 createLotto(int... numbers) {
        return new LottoV2(createLottoNumbers(numbers));
    }

    private List<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers).mapToObj(LottoNumber::new).toList();
    }
}
