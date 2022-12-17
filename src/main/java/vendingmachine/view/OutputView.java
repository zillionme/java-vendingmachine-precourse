package vendingmachine.view;

import java.util.List;

public class OutputView {
    private static final String MESSAGE_MACHINE_WALLET ="\n자판기가 보유한 동전";
    private static final String MESSAGE_USER_MONEY ="\n투입 금액: %s원";
    private static final String MESSAGE_CHANGES ="잔돈";

    public void printError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void printMachineWallet(List<String> machineWallet) {
        System.out.println(MESSAGE_MACHINE_WALLET);
        for(String coinAndCount : machineWallet) {
            System.out.println(coinAndCount);
        }
    }

    public void printUserMoney(String userMoney) {
        System.out.println(String.format(MESSAGE_USER_MONEY, userMoney));

    }

    public void printChanges(List<String> changeList) {
        System.out.println(MESSAGE_CHANGES);
        for(String change : changeList) {
            System.out.println(change);
        }

    }
}
