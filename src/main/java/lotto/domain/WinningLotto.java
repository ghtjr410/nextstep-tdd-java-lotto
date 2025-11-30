package lotto.domain;

import java.util.List;

public record WinningLotto(Lotto lotto) {

    public WinningLotto(List<LottoNumber> numbers) {
        this(new Lotto(numbers));
    }

    public LottoRank match(Lotto that) {
        return LottoRank.from(countMatches(that));
    }

    private int countMatches(Lotto that) {
        return this.lotto.countMatches(that);
    }
}
