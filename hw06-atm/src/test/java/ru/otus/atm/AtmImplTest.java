package ru.otus.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.atm.result.AtmOperationResult;
import ru.otus.atm.result.AtmOperationStatus;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class AtmImplTest {

    private Atm atm;

    @Test
    void withdraw() {
    }

    @BeforeEach
    void beforeEach() {
        atm = new AtmImpl();
    }


    @Test
    void addOneBanknote() {
        atm.add(Arrays.asList(new Banknote(BanknoteType.B100)));
        assertThat(atm.getBalance()).isEqualTo(100);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B200)));
        assertThat(atm.getBalance()).isEqualTo(300);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B500)));
        assertThat(atm.getBalance()).isEqualTo(800);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B1000)));
        assertThat(atm.getBalance()).isEqualTo(1800);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B2000)));
        assertThat(atm.getBalance()).isEqualTo(3800);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B5000)));
        assertThat(atm.getBalance()).isEqualTo(8800);
    }

    @Test
    void addMultiple() {
        atm.add(Arrays.asList(new Banknote(BanknoteType.B100), new Banknote(BanknoteType.B500)));
        assertThat(atm.getBalance()).isEqualTo(600);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B2000),
                new Banknote(BanknoteType.B5000),
                new Banknote(BanknoteType.B200)));
        assertThat(atm.getBalance()).isEqualTo(7800);
    }

    @Test
    void withdrawByMaxBanknoteValues() {
        Banknote b1 = new Banknote(BanknoteType.B100);
        Banknote b2 = new Banknote(BanknoteType.B100);
        Banknote b3 = new Banknote(BanknoteType.B200);
        atm.add(Arrays.asList(b1,b2,b3));
        AtmOperationResult result = atm.withdraw(200);
        assertThat(result.getStatus()).isEqualTo(AtmOperationStatus.SUCCESS);
        assertThat(result.getBanknotes()).size().isEqualTo(1);
        assertThat(result.getBanknotes()).contains(b3);
        assertThat(result.getBanknotes()).doesNotContain(b1);
        assertThat(result.getBanknotes()).doesNotContain(b2);
    }

    @Test
    void withdrawNoAvailableExchargeBanknotes() {
        Banknote b1 = new Banknote(BanknoteType.B500);
        Banknote b2 = new Banknote(BanknoteType.B100);
        atm.add(Arrays.asList(b1,b2));
        assertThat(atm.getBalance()).isEqualTo(600);
        AtmOperationResult result = atm.withdraw(400);
        assertThat(result.getStatus()).isEqualTo(AtmOperationStatus.NO_EXCHARGE);
        assertThat(result.getBanknotes()).isNull();
    }

    @Test
    void noMoneyInTheAtm() {
        Banknote b1 = new Banknote(BanknoteType.B5000);
        Banknote b2 = new Banknote(BanknoteType.B2000);
        atm.add(Arrays.asList(b1,b2));
        assertThat(atm.getBalance()).isEqualTo(7000);
        AtmOperationResult result = atm.withdraw(8000);
        assertThat(result.getStatus()).isEqualTo(AtmOperationStatus.INSUFFICIENT_FUNDS);
        assertThat(result.getBanknotes()).isNull();
    }
}