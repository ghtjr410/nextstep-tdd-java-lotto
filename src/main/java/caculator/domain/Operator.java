package caculator.domain;

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

    public abstract int calculate(int left, int right);
}
