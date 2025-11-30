package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, new Money(2_000_000_000)),
    SECOND(5, new Money(1_500_000)),
    THIRD(4, new Money(50_000)),
    FOURTH(3, new Money(5_000)),
    MISS(0, Money.ZERO);

    private final int matchCount;
    private final Money prize;

    LottoRank(int matchCount, Money prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank from(int matchCount) {
        if (matchCount < 3) {
            return MISS;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(LottoRank.MISS);
    }

    public Money totalPrize(int count) {
        return this.prize.multiply(count);
    }

    public String matchCountDisplay() {
        return "%d개 일치".formatted(matchCount);
    }

    public String prizeDisplay() {
        return prize.toWonString(); // "2,000,000,000원"
    }
}
