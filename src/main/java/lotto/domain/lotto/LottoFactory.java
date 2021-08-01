package lotto.domain.lotto;

import java.util.Set;
import lotto.domain.lotto.exception.InvalidBonusLottoNumberException;
import lotto.domain.lotto.exception.InvalidLottoNumberException;
import lotto.domain.lotto.exception.InvalidTotalAmountException;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;

public class LottoFactory {

    private static final int LOTTO_PRICE = 1000;

    public static NormalLotto createNormal() {
        return new NormalLotto(LottoNumbers.create());
    }

    public static NormalLotto createNormal(Set<Integer> numbers) {
        return new NormalLotto(LottoNumbers.create(numbers));
    }

    public static WinningLotto createWinning(Set<Integer> numbers, int bonusNumber) {
        validationWinningLotto(numbers, bonusNumber);
        return new WinningLotto(LottoNumbers.create(numbers), bonusNumber);
    }

    private static void validationWinningLotto(Set<Integer> checkNumbers, int bonusNumber) {
        if (!LottoNumber.isValid(checkNumbers)) {
            throw new InvalidLottoNumberException();
        }

        if (checkNumbers.contains(bonusNumber)
            || !LottoNumber.isValid(bonusNumber)) {
            throw new InvalidBonusLottoNumberException();
        }
    }

    public static int possiblePurchaseLottoCount(int totalAmount) {
        if (totalAmount < LOTTO_PRICE) {
            throw new InvalidTotalAmountException(LOTTO_PRICE);
        }

        return totalAmount / LOTTO_PRICE;
    }

    public static int calculateTotalAmount(int purchasedLottoCount) {
        return purchasedLottoCount * LOTTO_PRICE;
    }
}
