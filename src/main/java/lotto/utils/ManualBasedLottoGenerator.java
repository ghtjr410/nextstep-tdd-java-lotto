package lotto.utils;

import java.util.List;
import lotto.domain.Lotto;

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
