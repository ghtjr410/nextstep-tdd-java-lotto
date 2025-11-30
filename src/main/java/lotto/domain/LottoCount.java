package lotto.domain;

public record LottoCount(int value) {
    private static final int MIN_COUNT = 1;

    public LottoCount {
        if (value < MIN_COUNT) {
            throw new IllegalArgumentException("로또 구매 개수는 %d개 이상이어야 합니다.".formatted(MIN_COUNT));
        }
    }
}
