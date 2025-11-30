package lotto.domain;

public record Money(int value) {
    public static final Money ZERO = new Money(0);

    public Money {
        validateNonNegative(value);
    }

    private void validateNonNegative(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("금액은 0원 이상이어야 합니다.");
        }
    }

    public boolean isLessThan(Money other) {
        return isLessThan(other.value);
    }

    public boolean isLessThan(int other) {
        return this.value < other;
    }

    public boolean isDivisible(Money unit) {
        return isDivisible(unit.value);
    }

    public boolean isDivisible(int unit) {
        return this.value % unit == 0;
    }

    public int divide(Money unit) {
        return divide(unit.value);
    }

    public int divide(int unit) {
        return this.value / unit;
    }

    public double ratio(Money other) {
        return ratio(other.value);
    }

    public double ratio(int other) {
        if (other == 0) {
            return 0.0;
        }
        return (double) this.value / other;
    }

    public Money multiply(int count) {
        return new Money(this.value * count);
    }

    public Money add(Money other) {
        return new Money(this.value + other.value);
    }

    public String toWonString() {
        return "%d원".formatted(this.value);
    }
}
