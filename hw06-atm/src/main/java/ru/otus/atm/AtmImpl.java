package ru.otus.atm;

import ru.otus.atm.banknotes.Banknote;
import ru.otus.atm.banknotes.BanknoteType;
import ru.otus.atm.exceptions.NoBanknoteException;
import ru.otus.atm.result.AtmOperationResult;
import ru.otus.atm.result.AtmOperationStatus;
import ru.otus.atm.storage.*;

import java.util.*;

public class AtmImpl implements Atm {

    private BanknoteStorage banknoteStorage = new CasseteBanknoteStorage(); // агрегация - хранищиле без банкомата не существует.

    @Override
    public int getBalance() {
        return banknoteStorage.getBalance();
    }

    @Override
    public AtmOperationResult get(int amount) {
        if (amount > banknoteStorage.getBalance()) {
            return new AtmOperationResult(AtmOperationStatus.INSUFFICIENT_FUNDS, null);
        }
        Optional<Collection<Banknote>> moneyToClient;
        try {
            moneyToClient = withdrawBanknotes(amount);
        } catch (NoBanknoteException ex) {
            return new AtmOperationResult(AtmOperationStatus.NO_BANKNOTES, null);
        }
        return moneyToClient
                .map(banknotes -> new AtmOperationResult(AtmOperationStatus.SUCCESS, banknotes))
                .orElseGet(() -> new AtmOperationResult(AtmOperationStatus.NO_BANKNOTES, null));
    }

    @Override
    public void add(Banknote... banknotes) {
        for (Banknote banknote : banknotes) {
            banknoteStorage.add(banknote);
        }
    }

    private Optional<Collection<Banknote>> withdrawBanknotes(int amount) throws NoBanknoteException {
        Collection<Banknote> banknotes = null;
        Map<BanknoteType, Integer> minCountOfBanknotes = getMinCountOfBanknotesToAmount(amount);
        if (isTransferConfimed(minCountOfBanknotes, amount)) {
            banknotes = getBanknotesFromStorage(minCountOfBanknotes);
        }
        return Optional.ofNullable(banknotes);
    }

    /**
     * Возвращает минимальное количество купюр для удовлетворения желаемой суммы для снятия
     * @param amount сумма, запрошенная клиентом.
     * @return Map где ключ - тип купюры, значение - количество куплюр этого типа.
     */
    private Map<BanknoteType, Integer> getMinCountOfBanknotesToAmount(int amount) {
        double tmpAmount = (double) amount;
        Map<BanknoteType, Integer> minCountOfBanknotes = new EnumMap<>(BanknoteType.class);
        for (BanknoteType banknoteType : BanknoteType.values()) {
            double division = tmpAmount / banknoteType.getValue();
            if (division == 0) {
                break;
            }
            if (division >= 1) {
                int integerPart = (int) division;
                int banknoteAvailability = banknoteStorage.sizeOf(banknoteType) - integerPart;
                int banknotesCountToWithdraw = integerPart;
                if (banknoteAvailability < 0) {
                    banknotesCountToWithdraw = integerPart + banknoteAvailability;
                }
                if (banknotesCountToWithdraw == 0) {
                    break;
                }
                tmpAmount -= banknoteType.getValue() * banknotesCountToWithdraw;
                minCountOfBanknotes.put(banknoteType, banknotesCountToWithdraw);
            }
        }
        return minCountOfBanknotes;
    }

    /**
     * Проверка возможности снятия денег. Если денег в банкомате не хватает - откзаать в снятии.
     */
    private boolean isTransferConfimed(Map<BanknoteType, Integer> desiredBanknotes, int amount) {
        int sumValuesOfBanknotes = desiredBanknotes.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
                .reduce(0, Integer::sum);
        return sumValuesOfBanknotes == amount;
    }

    /**
     * Снимает деньги из хранилища.
     */
    private Collection<Banknote> getBanknotesFromStorage(Map<BanknoteType, Integer> desiredBanknotes) throws NoBanknoteException {
        Collection<Banknote> banknotesToReturn = new HashSet<>();
        for (Map.Entry<BanknoteType, Integer> entry : desiredBanknotes.entrySet()) {
            banknoteStorage.get(entry.getKey(), entry.getValue()).ifPresent(banknotesToReturn::addAll);
        }
        return banknotesToReturn;
    }
}