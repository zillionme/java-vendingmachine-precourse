package vendingmachine;

import vendingmachine.constants.Item;
import vendingmachine.domain.MachineWallet;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void generate() {
        VendingMachine vendingMachine = createVendingMachine();
        int userMoney = getUserMoney();
        vendingMachine.putUserMoney(userMoney);
        //buy- while(모든 상품이 소진된경우, 남은 금액이 최저 가격보다 적거나)
        try {
            buyItems(vendingMachine);
        }catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        //잔돈돌려주기;
        returnChanges(vendingMachine);
    }

    public MachineWallet createWallet() {
        int machineMoney = getMachineMoney();
        MachineWallet machineWallet = new MachineWallet(machineMoney);
        outputView.printMachineWallet(machineWallet.getMachineWallet());

        return machineWallet;
    }

    public int getUserMoney() {
        try {
            return inputView.readUserMoney();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getMachineMoney();
    }

    public VendingMachine createVendingMachine() {
        MachineWallet machineWallet = createWallet();
        List<Item> itemList = getItems();
        return new VendingMachine(itemList, machineWallet);
    }

    public void returnChanges(VendingMachine vendingMachine) {
        outputView.printUserMoney(vendingMachine.getUserMoney());
        outputView.printChanges(vendingMachine.getChanges());

    }
    public void buyItems(VendingMachine vendingMachine) {
        while(vendingMachine.checkBuyable()){
            outputView.printUserMoney(vendingMachine.getUserMoney()); //투입금액 출력
            Item itemToBuy = getItemToBuy(vendingMachine);
            vendingMachine.buy(itemToBuy);
        }
    }

    public int getMachineMoney() {
        try {
            return inputView.readMachineMoney();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getMachineMoney();
    }

    public List<Item> getItems() {
        try {
            List<Item> itemList = new ArrayList<>();
            for(String item : inputView.readItems()) {
                itemList.add(new Item(item));
            }
            return itemList;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getItems();
    }

    public Item getItemToBuy(VendingMachine vendingMachine) {
        try {
            String input = inputView.readItemToBuy();
            return vendingMachine.getItem(input);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getItemToBuy(vendingMachine);
    }


}
