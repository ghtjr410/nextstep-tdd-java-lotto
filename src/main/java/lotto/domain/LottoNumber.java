package lotto.domain;

public record LottoNumber(int value) {
    private static final int MIN = 1;
    private static final int MAX = 45;

    public LottoNumber(String input) {
        this(Integer.parseInt(input));
    }

    public LottoNumber {
        if (!isValidRange(value)) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
    }

    private boolean isValidRange(int input) {
        return input >= MIN && input <= MAX;
    }
}
