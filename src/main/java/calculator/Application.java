package calculator;

import calculator.utils.StringExpressionBuilder;
import calculator.view.InputView;
import calculator.view.ResultView;

public class Application {
    public static void main(String[] args) {
        String StringExpression = InputView.getStringExpression();

        int result = getCalculateResult(StringExpression);

        ResultView.printResult(result);
    }

    private static int getCalculateResult(String StringExpression) {
        return new StringExpressionBuilder().build(StringExpression).calculate();
    }
}
