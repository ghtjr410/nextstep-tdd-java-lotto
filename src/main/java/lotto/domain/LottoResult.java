package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record LottoResult(Map<LottoRank, Long> rankCounts) {

    public LottoResult(LottoRank... ranks) {
        this(List.of(ranks));
    }

    public LottoResult(List<LottoRank> ranks) {
        this(convert(ranks));
    }

    private static Map<LottoRank, Long> convert(List<LottoRank> ranks) {
        validateNotEmpty(ranks);

        return ranks.stream().collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    private static void validateNotEmpty(List<LottoRank> ranks) {
        if (ranks == null || ranks.isEmpty()) {
            throw new IllegalArgumentException("로또 등수는 1개 이상이어야 합니다.");
        }
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
