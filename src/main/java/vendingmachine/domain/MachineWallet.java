package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MachineWallet {
    private final List<Integer> coinList = Coin.getCoinList();
    private Map<Integer, Integer> machineWallet; //돈, 개수

    public MachineWallet(int money) {
        createWallet();
        createCoin(money);
    }

    public void createWallet() {
        machineWallet = new HashMap<>();
        Arrays.stream(Coin.values())
                .forEach(coin -> machineWallet.put(coin.getAmount(), 0));
    }

    public void createCoin(int money) {
        int coinSum = 0;

        while (coinSum != money) {
            if (coinSum > money) {
                coinSum = 0;
                createWallet();
            }

            int coin = Randoms.pickNumberInList(coinList);
            int count = machineWallet.getOrDefault(coin, 0);
            machineWallet.put(coin, count + 1);
            coinSum += coin;
        }
    }

    public List<String> getMachineWallet() {
        List<String> machineWalletString = new ArrayList<>();

        for (Integer coin : coinList) {
            int count = machineWallet.get(coin);

            String str = coin + "원 - " + count + "개";
            machineWalletString.add(str);
        }
        return machineWalletString;
    }


    public Map<Integer, Integer> returnChanges(int userMoney) {
        List<Integer> usableCoins = getUsableCoins();
        Map<Integer, Integer> changes = new HashMap<>();

        for (int coin : usableCoins) {
            userMoney = drawMoneyFromMachineWallet(coin, userMoney, changes);
        }
        return changes;
    }

    public List<Integer> getUsableCoins() {
        return machineWallet.entrySet().stream()
                .filter(coin -> coin.getValue() > 0)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder()) //내림차순
                .collect(Collectors.toList());
    }

    public int drawMoneyFromMachineWallet(int coin, int userMoney, Map<Integer, Integer> changes) {
        int count = machineWallet.get(coin); //동전개수
        while (coin <= userMoney && count > 0) {//투입금액이 동전보다 크고 개수까지, 돈빼기
            userMoney -= coin;
            count--;
            int changesCount = changes.getOrDefault(coin, 0);
            changes.put(coin, changesCount + 1);
        }
        machineWallet.put(coin, count);
        return userMoney;
    }

}
