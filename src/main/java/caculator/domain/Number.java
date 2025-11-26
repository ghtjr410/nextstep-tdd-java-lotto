package caculator.domain;

public record Number(int value) {
    public Number(String input) {
        this(Integer.parseInt(input));
    }
}
