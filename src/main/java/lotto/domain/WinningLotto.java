package lotto.domain;

import java.util.List;

public record WinningLotto(Lotto lotto, LottoNumber bonus) {

    public WinningLotto(List<LottoNumber> numbers, LottoNumber bonus) {
        this(new Lotto(numbers), bonus);
    }

    public WinningLotto(Lotto lotto, int bonus) {
        this(lotto, LottoNumber.of(bonus));
    }

    public WinningLotto {
        validateBonusNotDuplicated(lotto, bonus);
    }

    private void validateBonusNotDuplicated(Lotto lotto, LottoNumber bonus) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank match(Lotto that) {
        return LottoRank.from(countMatches(that), hasBonusMatch(that));
    }

    private int countMatches(Lotto that) {
        return this.lotto.countMatches(that);
    }

    private boolean hasBonusMatch(Lotto that) {
        return that.contains(bonus);
    }
}
