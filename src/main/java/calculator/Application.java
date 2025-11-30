package calculator;

import calculator.utils.StringExpressionBuilder;
import calculator.view.InputView;
import calculator.view.ResultView;

public class Application {
    public static void main(String[] args) {
        String stringExpression = InputView.getStringExpression();

        int result = getCalculateResult(stringExpression);

        ResultView.printResult(result);
    }

    private static int getCalculateResult(String StringExpression) {
        return new StringExpressionBuilder().build(StringExpression).calculate();
    }
}
