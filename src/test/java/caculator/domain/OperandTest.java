package caculator.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperandTest {

    @Test
    void 생성자_정수문지열_생성성공() {
        assertThatCode(() -> new Operand("1")).doesNotThrowAnyException();
    }
}
