package ru.otus.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.atm.result.AtmOperationResult;
import ru.otus.atm.result.AtmOperationStatus;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Проверка работоспособности банкомата.")
class AtmImplTest {

    private Atm atm;


    @BeforeEach
    void beforeEach() {
        atm = new AtmImpl();
    }

    @DisplayName("Добавляем по одной банкноте и сравниваем всю сумму с внесенной.")
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

    @DisplayName("Добавляем по несколько банкнот и сравниваем всю сумму с внесенной.")
    @Test
    void addMultiple() {
        atm.add(Arrays.asList(new Banknote(BanknoteType.B100), new Banknote(BanknoteType.B500)));
        assertThat(atm.getBalance()).isEqualTo(600);
        atm.add(Arrays.asList(new Banknote(BanknoteType.B2000),
                new Banknote(BanknoteType.B5000),
                new Banknote(BanknoteType.B200)));
        assertThat(atm.getBalance()).isEqualTo(7800);
    }

    @DisplayName("Добавляем 400(100+100+200) рублей, снимаем 200. Должен выдать 1 банкноту в 200рублей.")
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

    @DisplayName("Добавляем 600(100+500) рублей, пытаемся снять 400. Должен откзать, т.к. нет размена.")
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

    @DisplayName("Пытаемся снять больше, чем есть денег в банкомате. Ожидается отказ операции.")
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