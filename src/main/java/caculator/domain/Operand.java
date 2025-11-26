package caculator.domain;

public record Operand(int value) {
    public Operand(String input) {
        this(Integer.parseInt(input));
    }
}
