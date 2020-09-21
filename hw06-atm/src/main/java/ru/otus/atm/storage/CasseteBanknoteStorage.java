package ru.otus.atm.storage;

import lombok.NonNull;
import ru.otus.atm.banknotes.*;
import ru.otus.atm.exceptions.NoBanknoteException;

import java.util.*;

/**
 * Ящик-хранилище банковских куплюр. С кассетами под каждый тип купюры.
 */
public class CasseteBanknoteStorage implements BanknoteStorage {
    private List<Banknote100> cassete100 = new LinkedList<>();
    private List<Banknote200> cassete200 = new LinkedList<>();
    private List<Banknote500> cassete500 = new LinkedList<>();
    private List<Banknote1000> cassete1000 = new LinkedList<>();
    private List<Banknote2000> cassete2000 = new LinkedList<>();
    private List<Banknote5000> cassete5000 = new LinkedList<>();
    private int balance;

    public int getBalance() {
        return balance;
    }

    public int sizeOf(@NonNull BanknoteType banknoteType) {
        return getCasseteOfType(banknoteType).size();
    }

    public void add(Banknote100 banknote) {
        cassete100.add(banknote);
        balance += banknote.getValue();
    }

    public void add(Banknote200 banknote) {
        cassete200.add(banknote);
        balance += banknote.getValue();
    }

    public void add(Banknote500 banknote) {
        cassete500.add(banknote);
        balance += banknote.getValue();
    }

    public void add(Banknote1000 banknote) {
        cassete1000.add(banknote);
        balance += banknote.getValue();
    }

    public void add(Banknote2000 banknote) {
        cassete2000.add(banknote);
        balance += banknote.getValue();
    }

    public void add(Banknote5000 banknote) {
        cassete5000.add(banknote);
        balance += banknote.getValue();
    }

    public void add(Banknote banknote) {
        if (banknote instanceof Banknote100) {
            cassete100.add((Banknote100) banknote);
            balance += 100;
        } else if (banknote instanceof Banknote200) {
            cassete200.add((Banknote200)banknote);
            balance += 200;
        } else if (banknote instanceof Banknote500) {
            cassete500.add((Banknote500)banknote);
            balance += 500;
        } else if (banknote instanceof Banknote1000) {
            cassete1000.add((Banknote1000)banknote);
            balance += 1000;
        } else if (banknote instanceof Banknote2000) {
            cassete2000.add((Banknote2000)banknote);
            balance += 2000;
        } else {
            cassete5000.add((Banknote5000) banknote);
            balance += 5000;
        }
    }

    public Optional<Collection<Banknote>> get(BanknoteType type, int count) throws NoBanknoteException {
        if (sizeOf(type) < count) {
            return Optional.empty();
        }
        List<? extends Banknote> cassete = getCasseteOfType(type);
        Set<Banknote> banknotes = new HashSet<>();
        for (int i = 0; i < count; i++) {
            Banknote b;
            try {
                 b = cassete.get(0);
            } catch (IndexOutOfBoundsException e) {
                throw new NoBanknoteException("закончились купюры типа" + type);
            }
            banknotes.add(b);
            cassete.remove(b);
        }
        balance -= type.getValue() * count;
        return Optional.of(banknotes);
    }

    /**
     * Определяет ячейку для укзанного типа купюр.
     * @param type тип купюры.
     * @return ссылка на ячейку.
     */
    private List<? extends Banknote> getCasseteOfType(BanknoteType type) {
        switch (type) {
            case B100:
                return cassete100;
            case B200:
                return cassete200;
            case B500:
                return cassete500;
            case B1000:
                return cassete1000;
            case B2000:
                return cassete2000;
            case B5000:
                return cassete5000;
            default:
                throw new UnsupportedOperationException("Unknown banknote!!!");
        }
    }
}
