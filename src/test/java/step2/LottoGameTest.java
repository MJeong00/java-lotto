package step2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoGameTest {

    LottoGame lottoGame = new LottoGame();
    Winner winner = new Winner();
    List<Ticket> tickets = new ArrayList<>();

    @BeforeEach
    void init(){
        tickets.add(new Ticket(Arrays.asList(5, 18, 27, 34, 39, 45)));
        tickets.add(new Ticket(Arrays.asList(5, 10, 15, 19, 34, 36)));
        tickets.add(new Ticket(Arrays.asList(1, 4, 6, 24, 30, 41)));
        tickets.add(new Ticket(Arrays.asList(15, 27, 29, 36, 37, 42)));
        tickets.add(new Ticket(Arrays.asList(2, 6, 7, 9, 18, 42)));
        tickets.add(new Ticket(Arrays.asList(9, 17, 27, 28, 34, 45)));
        tickets.add(new Ticket(Arrays.asList(1, 14, 32, 33, 35, 43)));
        tickets.add(new Ticket(Arrays.asList(3, 5, 6, 33, 35, 43)));
        tickets.add(new Ticket(Arrays.asList(2, 4, 5, 6, 31, 41)));
        /*
        * 3개 당첨 : 2개
        * 4개 당첨 : 1개
        * 5개 당첨 : 0개
        * 6개 당첨 : 0개
        * */
    }

    @DisplayName("로또 한장값(1000) 이하로 입력시 예외 발생")
    @Test
    void 로또_한장값_이하로_입력() {
        assertThatIllegalArgumentException()
                .isThrownBy(()-> {
                    lottoGame.makeTickets(100);
                }).withMessageMatching("1000원 이하로 입력했습니다.");
    }

    @DisplayName("입력한 가격만큼 로또 n개 생성")
    @Test
    void 로또_n장() {
        List<Ticket> tickets = lottoGame.makeTickets(7000);
        assertThat(tickets).hasSize(7);
    }

    @DisplayName("입력한 금액 대비 얻은 수익률 구하기")
    @Test
    void 수익률() {
        Map<String, Integer> winnerMap = winner.findWinner(tickets, "1, 2, 3, 4, 5, 6", 7);
        assertThat(lottoGame.resultRate(9000, winnerMap)).isEqualTo(6.66);
    }

}
