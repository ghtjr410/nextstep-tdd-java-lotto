package lotto;

import java.util.List;
import lotto.domain.*;
import lotto.utils.AutoBasedLottoGenerator;
import lotto.utils.LottoNumberParser;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseAmount purchaseAmount = getPurchaseAmount();
        PurchasedLottos purchased = new PurchasedLottos(generateLottos(purchaseAmount));

        ResultView.printPurchaseCount(purchased);
        ResultView.printPurchasedLottos(purchased);

        LottoResult result = purchased.result(getWinningLotto());

        ResultView.printResult(result, purchaseAmount);
    }

    private static LottoPurchaseAmount getPurchaseAmount() {
        return new LottoPurchaseAmount(InputView.readPurchaseAmount());
    }

    private static List<Lotto> generateLottos(LottoPurchaseAmount purchaseAmount) {
        return new AutoBasedLottoGenerator().generate(purchaseAmount.lottoCount());
    }

    private static WinningLotto getWinningLotto() {
        String input = InputView.readWinningNumbers();
        List<LottoNumber> numbers = new LottoNumberParser().parse(input);
        return new WinningLotto(numbers);
    }
}
