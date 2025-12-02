package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readPurchaseAmount() {
        System.out.println("구매금액 입력해 주세요.");

        return scanner.nextLine();
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
