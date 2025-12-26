package lotto.domain;

import java.util.List;

public class ManualBasedLottosGenerator implements LottosGenerator {
    private final List<String> manualInputs;
    private final LottoNumberParser parser;

    public ManualBasedLottosGenerator(List<String> manualInputs) {
        this(manualInputs, new LottoNumberParser());
    }

    private ManualBasedLottosGenerator(List<String> manualInputs, LottoNumberParser parser) {
        this.manualInputs = manualInputs;
        this.parser = parser;
    }

    @Override
    public Lottos generate() {
        return new Lottos(generateLottos(manualInputs));
    }

    private List<Lotto> generateLottos(List<String> inputs) {
        return inputs.stream().map(parser::parse).map(Lotto::new).toList();
    }
}
