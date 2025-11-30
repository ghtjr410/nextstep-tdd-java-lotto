package lotto.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Lotto(Set<LottoNumber> numbers) {
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lotto(List<LottoNumber> inputs) {
        this(toValidatedSet(inputs));
    }

    private static Set<LottoNumber> toValidatedSet(List<LottoNumber> inputs) {
        validateNotEmpty(inputs);

        Set<LottoNumber> uniqueNumbers = Set.copyOf(inputs);

        if (uniqueNumbers.size() != inputs.size()) {
            throw new IllegalArgumentException("중복된 로또번호가 존재합니다.");
        }

        return uniqueNumbers;
    }

    public Lotto {
        validateNotEmpty(numbers);
        validateSize(numbers);
    }

    private static void validateNotEmpty(Collection<LottoNumber> inputs) {
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
