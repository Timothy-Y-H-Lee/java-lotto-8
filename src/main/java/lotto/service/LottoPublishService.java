package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.repository.Lotto;
import lotto.enums.LottoRule;
import lotto.enums.UserInterfaceMessage;

public class LottoPublishService {
    private List<Lotto> publishedLottoNumbers = new ArrayList<>();
    private Integer lottoPrice;
    private Integer boughtLottoCount;

    private LottoPublishService() {
    }

    private static class LottoPublishServiceHolder {
        final static LottoPublishService LOTTO_PUBLISH_SERVICE = new LottoPublishService();
    }
    public static LottoPublishService getInstance() {
        return LottoPublishServiceHolder.LOTTO_PUBLISH_SERVICE;
    }

    public Integer getLottoPrice() {
        return lottoPrice;
    }

    /**
     * publishedLottoNumbers 초기화
     */
    public void clearPublishedLottoNumbers() {
        publishedLottoNumbers = new ArrayList<>();
    }

    /**
     * 구매 금액만큼, 로또 게임 당 로또 번호들을 생성하여 저장한다.
     * @param price : String
     */
    public void lottoPublish(String price) {
        lottoPrice = String2Integer(price);
        boughtLottoCount(lottoPrice);
        for (int i = 1; i <= boughtLottoCount; i++) {
            createLottoNumber();
        }
    }

    public List<Lotto> getPublishedLottoNumbers() {
        return publishedLottoNumbers;
    }

    private void createLottoNumber() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoRule.START.getValue(),
                LottoRule.END.getValue(),
                LottoRule.LOTTO_MAX_COUNT.getValue());
        publishedLottoNumbers.add(new Lotto(numbers));
    }

    private Integer String2Integer(String numberformat) {
        Integer price = 0;
        try {
            price = Integer.parseInt(numberformat);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(String.format(UserInterfaceMessage.ERROR_INPUT_BONUS_NUMBER_INCLUSIVE.getValue(),
                    LottoRule.START.getValue(), LottoRule.END.getValue()));
        }
        return price;
    }

    private void boughtLottoCount(Integer lottoPrice) {
        boughtLottoCount = lottoPrice / LottoRule.LOTTO_PRICE.getValue();
    }
}
