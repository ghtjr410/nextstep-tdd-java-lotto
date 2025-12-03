package lotto.domain;

public record ManualLottoCount(int value) {

    public ManualLottoCount(String input) {
        this(toInt(input));
    }

    private static int toInt(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("수동 구매 개수는 필수입니다.");
        }

        return Integer.parseInt(input);
    }

    public ManualLottoCount {
        validateNonNegative(value);
    }

    private static void validateNonNegative(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상이어야 합니다.");
        }
    }
}
