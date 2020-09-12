package ru.otus.atm;

import ru.otus.atm.result.AtmOperationResult;
import ru.otus.atm.result.AtmOperationStatus;

import java.util.*;

public class AtmImpl implements Atm {
    private Map<BanknoteType, Set<Banknote>> storage = new EnumMap<>(BanknoteType.class);
    private int balance;
    {
        for (BanknoteType type : BanknoteType.values()) {
            storage.put(type, new HashSet<>());
        }
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public AtmOperationResult withdraw(int amount) {
        if (amount > balance) {
            System.out.println("В банкомате недостаточно денег");
            return new AtmOperationResult(AtmOperationStatus.INSUFFICIENT_FUNDS, null);
        }
        Collection<Banknote> moneyToClient = withdrawBanknotes(amount);
        if (moneyToClient == null) {
            return new AtmOperationResult(AtmOperationStatus.NO_EXCHARGE, null);
        }
        return new AtmOperationResult(AtmOperationStatus.SUCCESS, moneyToClient);
    }

    @Override
    public void add(Collection<Banknote> banknotes) {
        for (Banknote banknote : banknotes) {
            storage.get(banknote.getType()).add(banknote);
            balance += banknote.getValue();
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
                int banknoteAvailability = storage.get(banknoteType).size() - integerPart;
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
            balance -= amount;
            return getBanknotesFromStorage(desiredBanknotes);
        }
        return null;
    }

    private boolean isTransferConfimed(Map<BanknoteType, Integer> desiredBanknotes, int amount) {
        int sum = desiredBanknotes.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
                .reduce(0,Integer::sum);
        return sum == amount;
    }

    private Collection<Banknote> getBanknotesFromStorage(Map<BanknoteType, Integer> desiredBanknotes) {
        Collection<Banknote> banknotesToReturn = new HashSet<>();
        for (Map.Entry<BanknoteType, Integer> entry : desiredBanknotes.entrySet()) {
            Set<Banknote> banknotesInStorageOfType = storage.get(entry.getKey());
            for (Banknote banknote : banknotesInStorageOfType) {
                banknotesToReturn.add(banknote);
                banknotesInStorageOfType.remove(banknote);
            }
        }
        return banknotesToReturn;
    }
}