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
}
