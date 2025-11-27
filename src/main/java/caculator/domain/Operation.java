package caculator.domain;

public class Operation {
    private final Operator operator;
    private final Operand operand;

    public Operation(String operator, String operand) {
        this(Operator.from(operator), new Operand(operand));
    }

    public Operation(Operator operator, Operand operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
