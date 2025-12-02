package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Long> rankCounts;

    public LottoResult(LottoRank... ranks) {
        this();
        for (LottoRank rank : ranks) {
            updateRank(rank);
        }
    }

    public LottoResult() {
        this(new EnumMap<>(LottoRank.class));
    }

    public LottoResult(Map<LottoRank, Long> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public void updateRank(LottoRank rank) {
        rankCounts.merge(rank, 1L, Long::sum);
    }

    public Money totalPrize() {
        return rankCounts.entrySet().stream().map(this::calculatePrize).reduce(Money.ZERO, Money::add);
    }

    private Money calculatePrize(Map.Entry<LottoRank, Long> entry) {
        LottoRank rank = entry.getKey();
        int count = entry.getValue().intValue();
        return rank.totalPrize(count);
    }

    public String rankResultForDisplay(LottoRank rank) {
        return "%s (%s)- %d개".formatted(rank.matchCountDisplay(), rank.prizeDisplay(), count(rank));
    }

    private long count(LottoRank rank) {
        return rankCounts.getOrDefault(rank, 0L);
    }
}
