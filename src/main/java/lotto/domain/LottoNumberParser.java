package lotto.domain;

import java.util.Arrays;
import java.util.List;

public class LottoNumberParser {
    private static final String DELIMITER = ",";

    public List<LottoNumber> parse(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::of)
                .toList();
    }
}
