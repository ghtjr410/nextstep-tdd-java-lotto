package caculator.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperatorTest {

    @Test
    void calculate_덧셈() {
        assertThat(Operator.PLUS.calculate(4, 2)).isEqualTo(6);
    }

    @Test
    void calculate_뺄셈() {
        assertThat(Operator.MINUS.calculate(4, 2)).isEqualTo(2);
    }

    @Test
    void calculate_곱셈() {
        assertThat(Operator.MULTIPLY.calculate(4, 2)).isEqualTo(8);
    }

    @Test
    void calculate_나눗셈() {
        assertThat(Operator.DIVIDE.calculate(4, 2)).isEqualTo(2);
    }
}
