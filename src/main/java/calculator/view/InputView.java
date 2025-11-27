package calculator.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringExpression() {
        System.out.println("수식을 입력해주세요");
        return scanner.nextLine();
    }
}
