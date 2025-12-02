package lotto.domain;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record LottoNumber(int value) {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final Map<Integer, LottoNumber> CACHE;

    static {
        CACHE = IntStream.rangeClosed(MIN, MAX).boxed().collect(Collectors.toMap(i -> i, LottoNumber::new));
    }

    public static LottoNumber of(int input) {
        validateRange(input);
        return CACHE.get(input);
    }

    public static LottoNumber of(String input) {
        return of(Integer.parseInt(input));
    }

    public LottoNumber(String input) {
        this(Integer.parseInt(input));
    }

    public LottoNumber {
        validateRange(value);
    }

    private static void validateRange(int input) {
        if (isInvalidRange(input)) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
    }

    private static boolean isInvalidRange(int input) {
        return input < MIN || input > MAX;
    }
}
