package vendingmachine.constants;

import java.util.Arrays;
import java.util.List;

import static vendingmachine.util.ErrorCode.NOT_VALID_ITEM_INFO;
import static vendingmachine.util.ErrorCode.NOT_VALID_ITEM_PRICE;
import static vendingmachine.util.ErrorCode.NOT_VALID_MONEY;

public class Item {
    private static final int PRICE_MIN =100;
    private static final int PRICE_UNIT =10;

    private String name;
    private int price;
    private int count;

    public Item(String item) {
        setItem(item);
    }

    public void setItem(String item) {
        List<String> itemInfo = Arrays.asList(item.substring(1,item.length()-1).split(","));
        if(itemInfo.size() !=3) {
            throw NOT_VALID_ITEM_INFO.throwError();
        }
        name = itemInfo.get(0);
        price = setPrice(itemInfo.get(1));
        count = setCount(itemInfo.get(2));

    }

    public int setPrice(String input) {
        validateNumber(input);
        int number = Integer.parseInt(input);
        validatePrice(number);
        return number;
    }

    public int setCount(String input) {
        validateNumber(input);
        return Integer.parseInt(input);
    }

    public boolean hasSameItemWith(String other) {
        return name.equals(other); //이름이 같음
    }

    public boolean hasItemLeft() {
        return count > 0;
    }


    public void validateNumber(String input) {
        try {
            if(Integer.parseInt(input) < 0){
                throw NOT_VALID_MONEY.throwError();
            }
        } catch (NumberFormatException e) {
            throw NOT_VALID_MONEY.throwError();
        }
    }

    public void validatePrice(int input) {
        if(input<PRICE_MIN || input%PRICE_UNIT !=0) {
            throw NOT_VALID_ITEM_PRICE.throwError();
        }
    }

    public int getPrice() {
        return price;
    }

    public void buy() {
        count--;
    }
}
