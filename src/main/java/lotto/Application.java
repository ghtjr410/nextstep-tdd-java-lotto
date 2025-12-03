package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.*;
import lotto.utils.AutoBasedLottoGenerator;
import lotto.utils.LottoNumberParser;
import lotto.utils.ManualBasedLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseAmount purchaseAmount = getPurchaseAmount();
        PurchasedLottos purchased = purchaseLottos(purchaseAmount);

        ResultView.printPurchasedLottos(purchased);

        LottoResult result = purchased.result(getWinningLotto());

        ResultView.printResult(result, purchaseAmount);
    }

    private static LottoPurchaseAmount getPurchaseAmount() {
        return new LottoPurchaseAmount(InputView.readPurchaseAmount());
    }

    private static PurchasedLottos purchaseLottos(LottoPurchaseAmount purchaseAmount) {
        LottoCount totalCount = purchaseAmount.lottoCount();

        LottoCount manualCount = new LottoCount(InputView.readManualLottoCount());
        List<Lotto> manualLottos = generateManualLottos(manualCount);

        LottoCount autoCount = totalCount.subtract(manualCount);
        List<Lotto> autoLottos = generateAutoLottos(autoCount);

        ResultView.printPurchaseCount(manualCount, autoCount);

        return mergeLottos(manualLottos, autoLottos);
    }

    private static List<Lotto> generateManualLottos(LottoCount manualCount) {
        List<String> manualInputs = InputView.readManualLottoNumbers(manualCount.value());
        List<Lotto> manualLottos = new ManualBasedLottoGenerator().generate(manualInputs);
        return manualLottos;
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
        String winningNumbers = InputView.readWinningNumbers();
        String bonusNumber = InputView.readBonusNumber();

        List<LottoNumber> numbers = new LottoNumberParser().parse(winningNumbers);
        return new WinningLotto(numbers, bonusNumber);
    }
}
