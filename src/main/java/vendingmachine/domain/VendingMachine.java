package vendingmachine.domain;

import vendingmachine.Coin;
import vendingmachine.constants.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static vendingmachine.util.ErrorCode.NOT_ENOUGH_MONEY;
import static vendingmachine.util.ErrorCode.NOT_VALID_ITEM;
import static vendingmachine.util.ErrorCode.NOT_VALID_ITEM_COUNT;

public class VendingMachine {
    private List<Item> itemRepository;
    private MachineWallet machineWallet;
    private int userMoney;

    public VendingMachine(List<Item> itemRepository, MachineWallet machineWallet) {
        this.itemRepository = itemRepository;
        this.machineWallet = machineWallet;
    }

    public void putUserMoney(int money) {
        userMoney = money;
    }

    public String getUserMoney() {
        return String.valueOf(userMoney);
    }

    public void buy(Item itemToBuy) {
        hasItemLeft(itemToBuy);
        isAffordable(itemToBuy);
        //구입 가능한지 확인 = 유저 머니> item보다 큰지, 그리고, 개수있는지

        userMoney -= itemToBuy.getPrice();
        itemToBuy.buy();
    }

    public Item getItem(String input) {
        for (Item item : itemRepository) {
            if (item.hasSameItemWith(input)) { //이름이 같고, 남아있으면,
                return item;
            }
        }
        throw  NOT_VALID_ITEM.throwError();
    }

    public void hasItemLeft(Item itemToBuy) {
        if(!itemToBuy.hasItemLeft()) {
            throw NOT_VALID_ITEM_COUNT.throwError();
        }
    }

    public void isAffordable(Item itemToBuy) {
        if(userMoney < itemToBuy.getPrice()) {
            throw NOT_ENOUGH_MONEY.throwError();
        }
    }

    public boolean checkBuyable() {
        return !isRepositoryEmpty() && isUserMoneyEnough();
    }

    public boolean isRepositoryEmpty() { //모든 상품이 소진된 경우
        return itemRepository.stream()
                .noneMatch(Item::hasItemLeft);
    }

    public boolean isUserMoneyEnough() { //남은 금액이 상품의 최저 가격보다 적은
        int minPrice = itemRepository.stream()
                .mapToInt(Item::getPrice)
                .min()
                .getAsInt();   //isPresent 체크하기

        return userMoney >= minPrice;
    }

    public List<String> getChanges() { //잔돈돌려주기
        Map<Integer, Integer> changesMap = machineWallet.returnChanges(userMoney); //돈, 개수
        List<String> changes = new ArrayList<>();

        for (Coin coin : Coin.values()) {
            int amount = coin.getAmount();
            if (changesMap.containsKey(amount)) { //돈 가지고 있으면
                int count = changesMap.get(amount);

                String str = amount + "원 - " + count + "개";
                changes.add(str);
            }
        }

        return changes;

    }

}
