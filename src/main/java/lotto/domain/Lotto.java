package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Lotto(Set<LottoNumber> numbers) {
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lotto(int... inputs) {
        this(toSet(inputs));
    }

    private static Set<LottoNumber> toSet(int... inputs) {
        return Arrays.stream(inputs).mapToObj(LottoNumber::new).collect(Collectors.toSet());
    }

    public Lotto(List<LottoNumber> inputs) {
        this(Set.copyOf(inputs));
    }

    public Lotto {
        validateNotEmpty(numbers);
        validateSize(numbers);
    }

    private void validateNotEmpty(Set<LottoNumber> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("로또번호는 필수입니다.");
        }
    }

    private void validateSize(Set<LottoNumber> inputs) {
        if (inputs.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또번호는 %d개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
    }

    public int countMatches(Lotto that) {
        return (int) this.numbers.stream().filter(that::contains).count();
    }

    private boolean contains(LottoNumber that) {
        return this.numbers.contains(that);
    }

    public String sortedValuesForDisplay() {
        String joined = sortedValues().stream().map(String::valueOf).collect(Collectors.joining(", "));

        return "[" + joined + "]";
    }

    private List<Integer> sortedValues() {
        return numbers.stream().map(LottoNumber::value).sorted().toList();
    }
}
