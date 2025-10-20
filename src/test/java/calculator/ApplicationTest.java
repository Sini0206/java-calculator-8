package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트_음수_입력_불가() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_구분자_제외한_문자_입력_불가() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(":1a,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_0_입력_불가() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_문자열_맨_앞_커스텀_구분자_패턴_부재() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("4//!\n2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_문자열_부정확한_커스텀_구분자_패턴_위치() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//!\n4//*\n2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_기본_구분자_커스텀_구분자_혼용() {
        assertSimpleTest(() -> {
            run("//;\\n:1,4:;10");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Test
    void 예외_테스트_구분자_부재() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1/4/10"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트__커스텀_구분자_패턴_사이_복수_문자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;;\\n:1,4:;10"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
