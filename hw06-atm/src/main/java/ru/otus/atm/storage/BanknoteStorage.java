package ru.otus.atm.storage;

import lombok.NonNull;
import ru.otus.atm.banknotes.*;
import ru.otus.atm.exceptions.NoBanknoteException;

import java.util.Collection;
import java.util.Optional;

/**
 * Встроенное хранилище банкнот для банкомата.
 */
public interface BanknoteStorage {

    /**
     * Суммарная стоимость загруженных купюр.
     * @return сколько денег в банкомате.
     */
    public int getBalance();

    /**
     * Добавляем любые купюры без разбора.
     * @param banknote купюра любого номинала.
     */
    void add(Banknote banknote);

    /**
     * Возвращает указанное количество банкнот определенного типа.
     *
     * @param type  номинал(тип) банкноты
     * @param count количество банкнот для выдачи
     * @return Купюры для выдачи
     */
    Optional<Collection<Banknote>> get(BanknoteType type, int count) throws NoBanknoteException;

    /**
     * Показывает, сколько купюр в данный момент находится в кассете.
     * @param banknoteType тип купюры.
     * @return количество заряженых банкнот.
     */
    int sizeOf(@NonNull BanknoteType banknoteType);
}
