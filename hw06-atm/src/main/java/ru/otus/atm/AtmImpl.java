package ru.otus.atm;

import ru.otus.atm.banknotes.Banknote;
import ru.otus.atm.banknotes.BanknoteType;
import ru.otus.atm.result.AtmOperationResult;
import ru.otus.atm.result.AtmOperationStatus;

import java.util.*;

public class AtmImpl implements Atm {

    private Storage storage = new Storage(); // агрегация - хранищиле без банкомата не существует.

    @Override
    public int getBalance() {
        return storage.getBalance();
    }

    @Override
    public AtmOperationResult withdraw(int amount) {
        if (amount > storage.getBalance()) {
            return new AtmOperationResult(AtmOperationStatus.INSUFFICIENT_FUNDS, null);
        }
        Collection<Banknote> moneyToClient = withdrawBanknotes(amount);
        if (moneyToClient == null) {
            return new AtmOperationResult(AtmOperationStatus.NO_EXCHARGE, null);
        }
        return new AtmOperationResult(AtmOperationStatus.SUCCESS, moneyToClient);
    }

    @Override
    public void add(Banknote... banknotes) {
        for (Banknote banknote : banknotes) {
            storage.add(banknote);
        }
    }

    private Collection<Banknote> withdrawBanknotes(int amount) {
        double tmpAmount = (double) amount;
        Map<BanknoteType, Integer> desiredBanknotes = new EnumMap<>(BanknoteType.class);
        for (BanknoteType banknoteType : BanknoteType.values()) {
            double division = tmpAmount / banknoteType.getValue();
            if (division == 0) {
                break;
            }
            if (division >= 1) {
                int integerPart = (int) division;
                int banknoteAvailability = storage.sizeOf(banknoteType) - integerPart;
                int banknotesCountToWithdraw = integerPart;
                if (banknoteAvailability < 0) {
                    banknotesCountToWithdraw = integerPart + banknoteAvailability;
                }
                if (banknotesCountToWithdraw == 0) {
                    break;
                }
                tmpAmount -= banknoteType.getValue() * banknotesCountToWithdraw;
                desiredBanknotes.put(banknoteType, banknotesCountToWithdraw);
            }
        }
        if (isTransferConfimed(desiredBanknotes, amount)) {
            return getBanknotesFromStorage(desiredBanknotes);
        }
        return null;
    }

    private boolean isTransferConfimed(Map<BanknoteType, Integer> desiredBanknotes, int amount) {
        int sumValuesOfBanknotes = desiredBanknotes.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
                .reduce(0, Integer::sum);
        return sumValuesOfBanknotes == amount;
    }

    private Collection<Banknote> getBanknotesFromStorage(Map<BanknoteType, Integer> desiredBanknotes) {
        Collection<Banknote> banknotesToReturn = new HashSet<>();
        for (Map.Entry<BanknoteType, Integer> entry : desiredBanknotes.entrySet()) {
            banknotesToReturn.addAll(storage.get(entry.getKey(),entry.getValue()));
        }
        return banknotesToReturn;
    }
}