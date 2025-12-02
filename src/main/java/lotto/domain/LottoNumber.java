package lotto.domain;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final Map<Integer, LottoNumber> CACHE;

    private final int value;

    static {
        CACHE = IntStream.rangeClosed(MIN, MAX).boxed().collect(Collectors.toMap(i -> i, LottoNumber::new));
    }

    private LottoNumber(int input) {
        this.value = input;
    }

    public static LottoNumber of(int input) {
        validateRange(input);
        return CACHE.get(input);
    }

    public static LottoNumber of(String input) {
        return of(Integer.parseInt(input));
    }

    private static void validateRange(int input) {
        if (isInvalidRange(input)) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
    }

    private static boolean isInvalidRange(int input) {
        return input < MIN || input > MAX;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
