package vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static vendingmachine.util.ErrorCode.NOT_VALID_COIN;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현
    public static List<Integer> getCoinList() {
        List<Integer> coinList = new ArrayList<>();
        Arrays.stream(Coin.values())
                .forEach(coin -> coinList.add(coin.amount));

        return coinList;
    }
//
//    public static Coin getCoinBy(int input) {
//        return Arrays.stream(Coin.values())
//                .filter((coin)-> coin.amount==input)
//                .findFirst()
//                .orElseThrow(NOT_VALID_COIN::throwError);
//
//    }

    public int getAmount() {
        return amount;
    }
}
