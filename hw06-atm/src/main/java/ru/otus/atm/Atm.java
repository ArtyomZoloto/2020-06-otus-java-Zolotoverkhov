package ru.otus.atm;

import ru.otus.atm.result.AtmOperationResult;

import java.util.Collection;

/**
 * Интерфейс банкомата. Работает с пачками купюр, как настоящий.
 */
public interface Atm {
    /**
     * Положить деньги в банкомат
     * @param banknotes пачка купюр различного номинала
     */
    void add(Collection<Banknote> banknotes);

    /**
     * Снять деньги с банкомата
     * @param amount сумма для снятия
     * @return пачка купюр различного номинала, эквивалентная запрошеной сумме.
     */
    AtmOperationResult withdraw(int amount);

    /**
     * Узнать, сколько денег внутри банкомата
     * @return
     */
    int getBalance();
}
