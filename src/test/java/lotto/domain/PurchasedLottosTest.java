package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PurchasedLottosTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> new PurchasedLottos(createLotto(1, 2, 3, 4, 5, 6))).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "입력:{0}")
    @NullAndEmptySource
    void 생성자_빈값입력_예외발생(List<Lotto> inputs) {
        assertThatThrownBy(() -> new PurchasedLottos(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매한 로또는 1개 이상이어야 합니다.");
    }

    @Test
    void purchaseCountForDisplay_구매개수_표시() {
        PurchasedLottos purchased = new PurchasedLottos(createLotto(1, 2, 3, 4, 5, 6));

        assertThat(purchased.purchaseCountForDisplay()).isEqualTo("1개를 구매했습니다.");
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(createLottoNumbers(numbers));
    }

    private Set<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers).mapToObj(LottoNumber::new).collect(Collectors.toSet());
    }
}
