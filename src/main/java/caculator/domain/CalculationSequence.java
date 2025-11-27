package caculator.domain;

import java.util.List;

public record CalculationSequence(List<Operation> operations) {
    public CalculationSequence {
        validateNotEmpty(operations);
        operations = List.copyOf(operations);
    }

    private static void validateNotEmpty(List<Operation> operations) {
        if (operations == null || operations.isEmpty()) {
            throw new IllegalArgumentException("계산 순서는 1개 이상의 연산이 필요합니다.");
        }
    }
}
