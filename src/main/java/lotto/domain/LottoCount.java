package lotto.domain;

public record LottoCount(int value) {

    public LottoCount(String input) {
        this(toInt(input));
    }

    private static int toInt(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("구매 개수는 필수입니다.");
        }
        return Integer.parseInt(input);
    }

    public LottoCount {
        if (value < 0) {
            throw new IllegalArgumentException("구매 개수는 0이상이어야 합니다.");
        }
    }
}
