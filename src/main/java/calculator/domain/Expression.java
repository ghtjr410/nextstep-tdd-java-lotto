package calculator.domain;

import java.util.List;

public record Expression(Operand initValue, CalculationSequence sequence) {

    public Expression(int initValue, Operation... operations) {
        this(new Operand(initValue), new CalculationSequence(List.of(operations)));
    }

    public int calculate() {
        return sequence.applyAll(initValue).value();
    }
}
