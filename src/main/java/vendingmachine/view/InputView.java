package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static vendingmachine.util.ErrorCode.NOT_VALID_INPUT;
import static vendingmachine.util.ErrorCode.NOT_VALID_MONEY;

public class InputView {
    private static final String MESSAGE_MACHINE_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String MESSAGE_ITEMS = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String MESSAGE_USER_MONEY = "투입 금액을 입력해 주세요.";
    private static final String MESSAGE_ITEM_TO_BUY = "구매할 상품명을 입력해 주세요.";


    public int readMachineMoney() {
        System.out.println(MESSAGE_MACHINE_MONEY);
        return readMoney();
    }

    public int readUserMoney() {
        System.out.println(MESSAGE_USER_MONEY);
        return readMoney();
    }

    public int readMoney() {
        String input = Console.readLine();
        validateInput(input);
        validateMoney(input);

        return Integer.parseInt(input);
    }

    public void validateInput(String input) {
        if(input.isEmpty()) {
            throw NOT_VALID_INPUT.throwError();
        }
    }

    public void validateMoney(String input) {
        try {
            if(Integer.parseInt(input) < 0){
                throw NOT_VALID_MONEY.throwError();
            }
        } catch (NumberFormatException e) {
            throw NOT_VALID_MONEY.throwError();
        }
    }

    public List<String> readItems() {
        System.out.println(MESSAGE_ITEMS);
        String input = Console.readLine();
        validateInput(input);

        return Arrays.asList(input.split(";"));
    }
    public String readItemToBuy() {
        System.out.println(MESSAGE_ITEM_TO_BUY);
        String input = Console.readLine();
        validateInput(input);

        return input;

    }
}
