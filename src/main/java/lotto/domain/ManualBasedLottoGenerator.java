package lotto.domain;

import java.util.List;

public class ManualBasedLottoGenerator implements LottosGenerator {
    private final List<String> manualInputs;
    private final LottoNumberParser parser;

    public ManualBasedLottoGenerator(List<String> manualInputs) {
        this(manualInputs, new LottoNumberParser());
    }

    private ManualBasedLottoGenerator(List<String> manualInputs, LottoNumberParser parser) {
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
