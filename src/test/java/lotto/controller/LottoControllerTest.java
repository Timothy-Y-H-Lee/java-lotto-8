package lotto.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LottoControllerTest extends NsTest {

    LottoController lottoController;

    @BeforeEach
    void setUp() {
        lottoController = new LottoController();
    }

    // 개별 테스트는 통과하나, "./gradlew clean test" 명령어 사용 시,
    // ApplicationTest.기능_테스트() 의 동작으로, "8000" + "8000" 되기 때문에, "16개를 구매했습니다."가 리포트에 나옴.
    @Test
    @Disabled
    void 구매_금액_테스트() {
        assertSimpleTest(() -> {
            run("8000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains("8개");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
