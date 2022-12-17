package vendingmachine.util;

public enum ErrorCode {
    NOT_VALID_INPUT("값을 입력하지 않았습니다."),
    NOT_VALID_MONEY("숫자를 입력하지 않았습니다."),
    NOT_VALID_COIN("해당하는 동전이 없습니다."),
    NOT_VALID_ITEM_INFO("상품의 이름, 가격, 개수가 제대로 입력되지 않았습니다."),
    NOT_VALID_ITEM_PRICE("상품의 가격이 유효하지 않습니다."),
    NOT_VALID_ITEM("상품이 존재하지 않습니다."),
    NOT_VALID_ITEM_COUNT("상품의 재고가 남아있지 않습니다"),
    NOT_ENOUGH_MONEY("상품을 사는데 돈이 부족합니다.");

    private static final String ERROR_BEGIN= "[ERROR] ";
    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = ERROR_BEGIN + errorMessage;
    }

    public IllegalArgumentException throwError() {
        return new IllegalArgumentException(this.errorMessage);
    }
}
