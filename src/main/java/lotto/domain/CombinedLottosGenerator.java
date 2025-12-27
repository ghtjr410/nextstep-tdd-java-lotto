package lotto.domain;

import java.util.List;

public class CombinedLottosGenerator implements LottosGenerator {
    private final List<LottosGenerator> generators;

    public CombinedLottosGenerator(LottoPurchaseAmount amount, List<String> manualInputs) {
        this(toGenerators(amount, manualInputs));
    }

    private static List<LottosGenerator> toGenerators(LottoPurchaseAmount amount, List<String> manualInputs) {
        LottoCount totalCount = amount.lottoCount();
        LottoCount manualCount = new LottoCount(manualInputs.size());
        LottoCount autoCount = totalCount.subtract(manualCount);

        return List.of(new ManualBasedLottosGenerator(manualInputs), new AutoBasedLottosGenerator(autoCount));
    }

    public CombinedLottosGenerator(List<LottosGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public Lottos generate() {
        return generators.stream()
                .map(LottosGenerator::generate)
                .reduce(Lottos::merge)
                .orElseThrow(() -> new IllegalStateException("Generator가 없습니다."));
    }
}
