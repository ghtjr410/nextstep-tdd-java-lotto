package lotto.domain;

public record LottoCount(int value) {
    public LottoCount {
        if (value < 1) {
            throw new IllegalArgumentException("로또 구매 개수는 1개 이상이어야 합니다.");
        }
    }
}
