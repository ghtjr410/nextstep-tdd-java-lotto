package calculator.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperationTest {

    @Test
    void 생성자_연산자와_피연산자로_생성성공() {
        assertThatCode(() -> new Operation("+", "1"));
    }
}
