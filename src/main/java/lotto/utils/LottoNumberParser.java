package lotto.utils;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;

public class LottoNumberParser {
    private static final String DELIMITER = ",";

    public List<LottoNumber> parse(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
    }
}
