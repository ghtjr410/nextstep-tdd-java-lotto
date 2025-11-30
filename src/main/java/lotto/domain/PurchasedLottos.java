package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public record PurchasedLottos(List<Lotto> values) {

    public PurchasedLottos(Lotto... inputs) {
        this(List.of(inputs));
    }

    public PurchasedLottos {
        validateNotBlank(values);
    }

    private void validateNotBlank(List<Lotto> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("구매한 로또는 1개 이상이어야 합니다.");
        }
    }

    public String purchaseCountForDisplay() {
        return "%d개를 구매했습니다.".formatted(size());
    }

    private int size() {
        return values.size();
    }
}
