package lotto;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.*;
import lotto.domain.LottoNumberParser;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseAmount purchaseAmount = createPurchaseAmount();
        Lottos purchased = purchaseLottos(purchaseAmount);

        printPurchasedLottos(purchased);

        LottoResult result = purchased.result(createWinningLotto());

        printResult(result, purchaseAmount);
    }

    private static LottoPurchaseAmount createPurchaseAmount() {
        return retryUntilSuccess(() -> new LottoPurchaseAmount(readPurchaseAmount()));
    }

    private static Lottos purchaseLottos(LottoPurchaseAmount purchaseAmount) {
        LottoCount totalCount = purchaseAmount.lottoCount();
        LottoCount manualCount = createManualLottoCount(totalCount);
        LottoCount autoCount = totalCount.subtract(manualCount);

        printPurchaseCount(manualCount, autoCount);

        return createLottos(purchaseAmount, manualCount);
    }

    private static Lottos createLottos(LottoPurchaseAmount purchaseAmount, LottoCount manualCount) {
        return retryUntilSuccess(() -> {
            List<String> manualInputs = readManualLottoNumbers(manualCount.value());
            return new CombinedLottosGenerator(purchaseAmount, manualInputs).generate();
        });
    }

    private static LottoCount createManualLottoCount(LottoCount totalCount) {
        return retryUntilSuccess(() -> {
            LottoCount manualCount = new LottoCount(readManualLottoCount());
            totalCount.validateSubtractable(manualCount);
            return manualCount;
        });
    }

    private static WinningLotto createWinningLotto() {
        return retryUntilSuccess(() -> new WinningLotto(
                new Lotto(new LottoNumberParser().parse(readWinningNumbers())), LottoNumber.of(readBonusNumber())));
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
