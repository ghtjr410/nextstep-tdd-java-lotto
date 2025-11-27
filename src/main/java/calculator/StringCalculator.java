package calculator;

import calculator.utils.StringExpressionBuilder;

public class StringCalculator {

    public static int calculate(String input) {
        return new StringExpressionBuilder().build(input).calculate();
    }
}
