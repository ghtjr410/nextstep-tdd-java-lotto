package lotto.domain;

import java.util.List;

public class ManualBasedLottoGenerator {

    private final LottoNumberParser parser;

    public ManualBasedLottoGenerator() {
        this(new LottoNumberParser());
    }

    private ManualBasedLottoGenerator(LottoNumberParser parser) {
        this.parser = parser;
    }

    public List<Lotto> generate(List<String> inputs) {
        return inputs.stream().map(parser::parse).map(Lotto::new).toList();
    }
}
