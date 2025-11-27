package calculator.utils;

import calculator.domain.CalculationSequence;
import calculator.domain.Expression;
import calculator.domain.Operand;
import calculator.domain.Operation;
import java.util.ArrayList;
import java.util.List;

public class StringExpressionBuilder {

    public Expression build(String input) {
        return buildExpression(tokenize(input));
    }

    private String[] tokenize(String input) {
        validateNotBlank(input);
        String[] tokens = input.split(" ");
        validateTokens(tokens);
        return tokens;
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("수식은 필수입니다.");
        }
    }

    private void validateTokens(String[] tokens) {
        if (tokens.length % 2 == 0) {
            throw new IllegalArgumentException("올바르지 않은 수식입니다.");
        }
    }

    private Expression buildExpression(String[] tokens) {
        Operand initValue = new Operand(tokens[0]);
        CalculationSequence sequence = new CalculationSequence(parseOperations(tokens));
        return new Expression(initValue, sequence);
    }

    private List<Operation> parseOperations(String[] tokens) {
        List<Operation> operations = new ArrayList<>();

        for (int i = 1; i < tokens.length; i += 2) {
            operations.add(new Operation(tokens[i], tokens[i + 1]));
        }

        return operations;
    }
}
