package lotto.domain;

import java.util.List;

public record Lottos(List<Lotto> values) {

    public Lottos(Lotto... inputs) {
        this(List.of(inputs));
    }

    public Lottos {
        validateNotBlank(values);
    }

    private void validateNotBlank(List<Lotto> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("구매한 로또는 1개 이상이어야 합니다.");
        }
    }

    public LottoResult result(WinningLotto winningLotto) {
        LottoResult result = new LottoResult();

        for (Lotto lotto : values) {
            result.updateRank(winningLotto.match(lotto));
        }

        return result;
    }
}
