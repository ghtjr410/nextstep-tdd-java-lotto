package lotto.domain;

public record LottoPurchaseAmount(Money money) {
    private static final int LOTTO_PRICE = 1_000;
    private static final Money LOTTO_UNIT_PRICE = new Money(LOTTO_PRICE);

    public LottoPurchaseAmount(String input) {
        this(Integer.parseInt(input));
    }

    public LottoPurchaseAmount(int input) {
        this(new Money(input));
    }

    public LottoPurchaseAmount {
        validateMinimumAmount(money);
        validateDivisible(money);
    }

    private static void validateMinimumAmount(Money money) {
        if (money.isLessThan(LOTTO_UNIT_PRICE)) {
            throw new IllegalArgumentException("구매 금액은 1,000원 이상이어야 합니다.");
        }
    }

    private static void validateDivisible(Money money) {
        if (!money.isDivisible(LOTTO_UNIT_PRICE)) {
            throw new IllegalArgumentException("구매 금액은 1,000원 단위여야 합니다.");
        }
    }

    public LottoCount lottoCount() {
        return new LottoCount(calculateLottoCount());
    }

    private int calculateLottoCount() {
        return this.money.divide(LOTTO_UNIT_PRICE);
    }
}
