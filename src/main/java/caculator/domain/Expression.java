package caculator.domain;

public record Expression(Operand initValue, CalculationSequence sequence) {

    public int calculate() {
        return sequence.applyAll(initValue).value();
    }
}
