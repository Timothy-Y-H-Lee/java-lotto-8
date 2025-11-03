package lotto.controller;

import java.util.List;
import lotto.repository.Lotto;
import lotto.service.LottoBonusService;
import lotto.service.LottoInputDrawNumberService;
import lotto.service.LottoNumberCompareService;
import lotto.service.LottoPublishService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private lotto.service.LottoNumberCompareService lottoNumberCompareService = LottoNumberCompareService.getInstance();

    LottoPublishService lottoPublishService = LottoPublishService.getInstance();
    LottoInputDrawNumberService lottoInputDrawNumberService = LottoInputDrawNumberService.getInstance();
    LottoBonusService lottoBonusService = LottoBonusService.getInstance();

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void startGame() {
        buyLotto();
        lottoPublish();
        saveDrawNumbers();
        saveBonusNumbers();
        processDrawResult();
        printResult();
    }

    private void printResult() {
        outputView.printResultMessage();
        outputView.printRewardResult(lottoNumberCompareService.getMatchResult());
        outputView.printEarnings(lottoNumberCompareService.getEarnings());
    }

    private void processDrawResult() {
        lottoNumberCompareService.inputNumber(lottoPublishService.getPublishedLottoNumbers(),
                lottoInputDrawNumberService.getLottoDrawNumber(), lottoBonusService.getLottoBonusNumber(),
                lottoPublishService.getLottoPrice());

        lottoNumberCompareService.initMatchResult();
        lottoNumberCompareService.calcResult();
    }
    private void saveDrawNumbers() {
        try {
            lottoInputDrawNumberService.saveDrawNumber(inputView.requestInputDrawNumbers());
        } catch (IllegalArgumentException e) {
            inputView.printMessage(e.getMessage());
            saveDrawNumbers();
        }
    }

    private void buyLotto() {
        requestInputPrice();
    }

    private void requestInputPrice() {
        try {
            lottoPublishService.lottoPublish(inputView.requestInputPrice());
        } catch (IllegalArgumentException e) {
            inputView.printMessage(e.getMessage());
            requestInputPrice();
        }
    }

    private void lottoPublish() {
        List<Lotto> lottos = lottoPublishService.getPublishedLottoNumbers();
        outputView.printPublishCount(lottos.size());
        outputView.printPublishedLottoNumbers(lottoPublishService.getPublishedLottoNumbers());
    }

    private void saveBonusNumbers() {
        try {
            lottoBonusService.setLottoBonusNumber(inputView.requestInputBonusNumbers(),
                    lottoInputDrawNumberService.getLottoDrawNumber());
        } catch (IllegalArgumentException e) {
            inputView.printMessage(e.getMessage());
            this.saveBonusNumbers();
        }
    }
}

