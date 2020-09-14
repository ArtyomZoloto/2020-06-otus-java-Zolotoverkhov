package ru.otus.atm.storage;

import lombok.NonNull;
import ru.otus.atm.banknotes.*;

import java.util.Collection;

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
    Collection<Banknote> get(BanknoteType type, int count);

    /**
     * Куча перегруженных методов для добавляения каждой банкноты в свою ячейку.
     * Не используются, но полезные.
     * @param banknote
     */
    public void add(Banknote100 banknote);
    public void add(Banknote200 banknote);
    public void add(Banknote500 banknote);
    public void add(Banknote1000 banknote);
    public void add(Banknote2000 banknote);
    public void add(Banknote5000 banknote);

    /**
     * Показывает, сколько купюр в данный момент находится в кассете.
     * @param banknoteType тип купюры.
     * @return количество заряженых банкнот.
     */
    int sizeOf(@NonNull BanknoteType banknoteType);
}
