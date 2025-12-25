package lotto.view;

import lotto.domain.*;

public class ResultView {

    public static void printPurchaseCount(LottoCount manualCount, LottoCount auto) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualCount.value(), auto.value());
    }

    public static void printPurchasedLottos(Lottos purchased) {
        for (Lotto lottoV2 : purchased.values()) {
            System.out.println(lottoV2.sortedValuesForDisplay());
        }
    }

    public static void printResult(LottoResult result, LottoPurchaseAmount purchaseAmount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        printRankResult(LottoRank.FIFTH, result);
        printRankResult(LottoRank.FOURTH, result);
        printRankResult(LottoRank.THIRD, result);
        printRankResult(LottoRank.SECOND, result);
        printRankResult(LottoRank.FIRST, result);

        printReturnRate(new LottoReturnRate(purchaseAmount, result.totalPrize()));
    }

    private static void printRankResult(LottoRank rank, LottoResult result) {
        System.out.println(result.rankResultForDisplay(rank));
    }

    private static void printReturnRate(LottoReturnRate lottoReturnRate) {
        System.out.println(lottoReturnRate.returnRateForDisplay());
    }
}
