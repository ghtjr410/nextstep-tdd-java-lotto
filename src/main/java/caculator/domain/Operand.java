package caculator.domain;

public record Operand(int value) {
    public Operand(String input) {
        this(parseValidInput(input));
    }

    private static int parseValidInput(String input) {
        validateNotBlank(input);
        return Integer.parseInt(input);
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("피연산자는 빈 값일 수 없습니다.");
        }
    }
}
