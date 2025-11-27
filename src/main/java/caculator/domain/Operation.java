package caculator.domain;

public record Operation(Operator operator, Operand operand) {

    public Operation(String operator, String operand) {
        this(Operator.from(operator), new Operand(operand));
    }

    public Operand apply(Operand currentValue) {
        return operator.calculate(currentValue, operand);
    }
}
