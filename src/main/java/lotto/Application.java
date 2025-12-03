package lotto;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lotto.domain.*;
import lotto.utils.AutoBasedLottoGenerator;
import lotto.utils.LottoNumberParser;
import lotto.utils.ManualBasedLottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseAmount purchaseAmount = getPurchaseAmount();
        PurchasedLottos purchased = purchaseLottos(purchaseAmount);

        printPurchasedLottos(purchased);

        LottoResult result = purchased.result(getWinningLotto());

        printResult(result, purchaseAmount);
    }

    private static LottoPurchaseAmount getPurchaseAmount() {
        return retryUntilSuccess(() -> new LottoPurchaseAmount(readPurchaseAmount()));
    }

    private static PurchasedLottos purchaseLottos(LottoPurchaseAmount purchaseAmount) {
        LottoCount totalCount = purchaseAmount.lottoCount();
        LottoCount manualCount = getManualLottoCount(totalCount);
        List<Lotto> manualLottos = generateManualLottos(manualCount);

        LottoCount autoCount = totalCount.subtract(manualCount);
        List<Lotto> autoLottos = generateAutoLottos(autoCount);

        printPurchaseCount(manualCount, autoCount);

        return mergeLottos(manualLottos, autoLottos);
    }

    private static LottoCount getManualLottoCount(LottoCount totalCount) {
        return retryUntilSuccess(() -> {
            LottoCount manualCount = new LottoCount(readManualLottoCount());
            totalCount.validateSubtractable(manualCount);
            return manualCount;
        });
    }

    private static List<Lotto> generateManualLottos(LottoCount manualCount) {
        return retryUntilSuccess(
                () -> new ManualBasedLottoGenerator().generate(readManualLottoNumbers(manualCount.value())));
    }

    private static List<Lotto> generateAutoLottos(LottoCount autoCount) {
        return new AutoBasedLottoGenerator().generate(autoCount);
    }

    private static PurchasedLottos mergeLottos(List<Lotto> manual, List<Lotto> auto) {
        List<Lotto> all = new ArrayList<>(manual);
        all.addAll(auto);
        return new PurchasedLottos(all);
    }

    private static WinningLotto getWinningLotto() {
        List<LottoNumber> numbers = getWinningNumbers();
        LottoNumber bonus = getBonusNumber(numbers);
        return new WinningLotto(numbers, bonus);
    }

    private static List<LottoNumber> getWinningNumbers() {
        return retryUntilSuccess(() -> new LottoNumberParser().parse(readWinningNumbers()));
    }

    private static LottoNumber getBonusNumber(List<LottoNumber> winningNumbers) {
        return retryUntilSuccess(() -> {
            LottoNumber bonus = LottoNumber.of(readBonusNumber());
            validateBonusNotDuplicated(winningNumbers, bonus);
            return bonus;
        });
    }

    private static void validateBonusNotDuplicated(List<LottoNumber> numbers, LottoNumber bonus) {
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private static <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
