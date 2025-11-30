package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoneyTest {
    @ParameterizedTest(name = "정상값{0}")
    @ValueSource(ints = {0, 1000})
    void 생성자_정상입력_생성성공(int input) {
        assertThatCode(() -> new Money(input)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_음수_예외발생() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0원 이상이어야 합니다.");
    }

    @ParameterizedTest(name = "{0}원 < {1}원 = {2}")
    @CsvSource({
        "999, 1000, true",
        "1000, 1000, false",
        "1001, 1000, false",
    })
    void isLessThan_금액비교(int a, int b, boolean expected) {
        assertThat(new Money(a).isLessThan(new Money(b))).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}원 % {1}원 = 나누어떨어짐:{2}")
    @CsvSource({
        "1000, 1000, true",
        "2000, 1000, true",
        "1500, 1000, false",
        "0, 1000, true",
    })
    void isDivisible_나눗셈가능여부_확인(int a, int b, boolean expected) {
        assertThat(new Money(a).isDivisible(new Money(b))).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}원 / {1}원 = {2}")
    @CsvSource({
        "1000, 500, 2",
        "3000, 1000, 3",
        "0, 1000, 0",
    })
    void divide_나눗셈_몫반환(int a, int b, int expected) {
        assertThat(new Money(a).divide(new Money(b))).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}원 / {1}원 = {2}")
    @CsvSource({
        "1000, 1000, 1.0",
        "5000, 10000, 0.5",
        "15000, 10000, 1.5",
        "0, 1000, 0.0",
    })
    void ratio_비율계산(int a, int b, double expected) {
        assertThat(new Money(a).ratio(new Money(b))).isEqualTo(expected);
    }

    @Test
    void ratio_0으로나누기_0반환() {
        assertThat(new Money(1000).ratio(0)).isEqualTo(0.0);
    }

    @ParameterizedTest(name = "{0}원 * {1} = {2}원")
    @CsvSource({
        "1000, 3, 3000",
        "500, 0, 0",
        "0, 5, 0",
    })
    void multiply_곱셈(int value, int count, int expected) {
        assertThat(new Money(value).multiply(count)).isEqualTo(new Money(expected));
    }

    @ParameterizedTest(name = "{0}원 + {1}원 = {2}원")
    @CsvSource({
        "1000, 2000, 3000",
        "0, 1000, 1000",
        "0, 0, 0",
    })
    void add_덧셈(int a, int b, int expected) {
        assertThat(new Money(a).add(new Money(b))).isEqualTo(new Money(expected));
    }

    @ParameterizedTest(name = "{0}원 -> {1}")
    @CsvSource({
        "0, 0원",
        "1000, 1000원",
        "2000000000, 2000000000원",
    })
    void toWonString_원화문자열변환(int value, String expected) {
        assertThat(new Money(value).toWonString()).isEqualTo(expected);
    }
}
