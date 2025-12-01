package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, new Money(2_000_000_000)),
    SECOND(5, true, new Money(30_000_000)),
    THIRD(5, false, new Money(1_500_000)),
    FOURTH(4, false, new Money(50_000)),
    FIFTH(3, false, new Money(5_000)),
    MISS(0, false, Money.ZERO);

    private final int matchCount;
    private final boolean requireBonus;
    private final Money prize;

    LottoRank(int matchCount, boolean requireBonus, Money prize) {
        this.matchCount = matchCount;
        this.requireBonus = requireBonus;
        this.prize = prize;
    }

    public static LottoRank from(int matchCount, boolean requireBonus) {
        if (matchCount < 3) {
            return MISS;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.requireBonus == requireBonus)
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
        return prize.toWonString();
    }
}
