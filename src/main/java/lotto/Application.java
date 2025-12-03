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
        LottoPurchaseAmount purchaseAmount = createPurchaseAmount();
        PurchasedLottos purchased = purchaseLottos(purchaseAmount);

        printPurchasedLottos(purchased);

        LottoResult result = purchased.result(createWinningLotto());

        printResult(result, purchaseAmount);
    }

    private static LottoPurchaseAmount createPurchaseAmount() {
        return retryUntilSuccess(() -> new LottoPurchaseAmount(readPurchaseAmount()));
    }

    private static PurchasedLottos purchaseLottos(LottoPurchaseAmount purchaseAmount) {
        LottoCount totalCount = purchaseAmount.lottoCount();
        LottoCount manualCount = createManualLottoCount(totalCount);
        LottoCount autoCount = totalCount.subtract(manualCount);

        printPurchaseCount(manualCount, autoCount);

        return mergeLottos(createManualLottos(manualCount), generateAutoLottos(autoCount));
    }

    private static LottoCount createManualLottoCount(LottoCount totalCount) {
        return retryUntilSuccess(() -> {
            LottoCount manualCount = new LottoCount(readManualLottoCount());
            totalCount.validateSubtractable(manualCount);
            return manualCount;
        });
    }

    private static List<Lotto> createManualLottos(LottoCount manualCount) {
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

    private static WinningLotto createWinningLotto() {
        Lotto lotto = createLottoForWinning();
        LottoNumber bonus = createBonusNumber(lotto);
        return new WinningLotto(lotto, bonus);
    }

    private static Lotto createLottoForWinning() {
        return retryUntilSuccess(() -> new Lotto(createWinningNumbers()));
    }

    private static List<LottoNumber> createWinningNumbers() {
        return new LottoNumberParser().parse(readWinningNumbers());
    }

    private static LottoNumber createBonusNumber(Lotto lotto) {
        return retryUntilSuccess(() -> {
            LottoNumber bonus = LottoNumber.of(readBonusNumber());
            validateBonusNotDuplicated(lotto, bonus);
            return bonus;
        });
    }

    private static void validateBonusNotDuplicated(Lotto lotto, LottoNumber bonus) {
        if (lotto.contains(bonus)) {
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
