package lotto.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.enums.LottoRule;
import lotto.enums.UserInterfaceMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        Set<Integer> lottoNumbers = new HashSet<>(numbers);
        if (numbers.size() != LottoRule.LOTTO_MAX_COUNT.getValue()
                || lottoNumbers.size() != LottoRule.LOTTO_MAX_COUNT.getValue()) {
            throw new IllegalArgumentException(String.format(UserInterfaceMessage.REQUEST_INPUT_PRICE_INFO.getValue(),
                    LottoRule.START.getValue(), LottoRule.END.getValue()));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
