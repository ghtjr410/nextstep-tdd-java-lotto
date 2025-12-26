package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoBasedLottosGenerator implements LottosGenerator {
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_PICK_SIZE = 6;

    private final List<LottoNumber> numbers;
    private final LottoCount count;

    public AutoBasedLottosGenerator(LottoCount count) {
        this.count = count;
        this.numbers = IntStream.rangeClosed(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public Lottos generate() {
        return new Lottos(generateLottos(count.value()));
    }

    private List<Lotto> generateLottos(int count) {
        return IntStream.range(0, count).mapToObj(i -> generateLotto()).toList();
    }

    private Lotto generateLotto() {
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(0, LOTTO_PICK_SIZE));
    }
}
