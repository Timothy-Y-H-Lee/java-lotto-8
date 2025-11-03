package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.repository.Lotto;
import lotto.enums.UserInterfaceMessage;

public class OutputView {
    public void printPublishCount(int size) {
        System.out.println(String.format(UserInterfaceMessage.COUNT_BOUGHT.getValue() + "\n", size));
    }

    public void printPublishedLottoNumbers(List<Lotto> publishedLottoNumbers) {
        for (Lotto publishedLotto : publishedLottoNumbers) {
            System.out.println(publishedLotto.getNumbers());
        }
    }
}
