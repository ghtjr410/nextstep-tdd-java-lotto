package caculator.domain;

import java.util.Arrays;

public enum Operator {
    PLUS("+") {
        @Override
        public int calculate(int left, int right) {
            return left + right;
        }
    },
    MINUS("-") {
        @Override
        public int calculate(int left, int right) {
            return left - right;
        }
    },
    MULTIPLY("*") {
        @Override
        public int calculate(int left, int right) {
            return left * right;
        }
    },
    DIVIDE("/") {
        @Override
        public int calculate(int left, int right) {
            return left / right;
        }
    };

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public Operand calculate(Operand left, Operand right) {
        return new Operand(calculate(left.value(), right.value()));
    }

    public abstract int calculate(int left, int right);

    public static Operator from(String input) {
        return Arrays.stream(values())
                .filter(o -> o.matches(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자입니다."));
    }

    private boolean matches(String symbol) {
        return this.symbol.equals(symbol);
    }
}
