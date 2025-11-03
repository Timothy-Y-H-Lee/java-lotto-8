package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.enums.UserInterfaceMessage;

public class OutputView {
    public void printPublishCount(int size) {
        System.out.printf(UserInterfaceMessage.COUNT_BOUGHT.getValue() + "\n", size);
    }
}
