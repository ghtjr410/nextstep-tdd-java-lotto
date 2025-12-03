package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readPurchaseAmount() {
        System.out.println("구매금액 입력해 주세요.");

        return scanner.nextLine();
    }

    public static String readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return scanner.nextLine();
    }

    public static List<String> readManualLottoNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        return IntStream.range(0, count).mapToObj(i -> scanner.nextLine()).toList();
    }

    public static String readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return scanner.nextLine();
    }

    public static String readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return scanner.nextLine();
    }
}
