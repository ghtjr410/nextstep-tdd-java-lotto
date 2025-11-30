package lotto.domain;

public record LottoReturnRate(double value) {

    public LottoReturnRate(LottoPurchaseAmount purchaseAmount, Money totalPrize) {
        this(totalPrize.ratio(purchaseAmount.money()));
    }

    public LottoReturnRate {
        validateNonNegative(value);
    }

    private void validateNonNegative(double input) {
        if (input < 0) {
            throw new IllegalArgumentException("수익률은 0 이상이어야 합니다.");
        }
    }

    public String returnRateForDisplay() {
        return "총 수익률은 %.2f입니다.%n".formatted(this.value);
    }
}
