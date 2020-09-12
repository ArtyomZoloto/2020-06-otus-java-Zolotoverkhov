package ru.otus.atm.result;

import lombok.Value;
import ru.otus.atm.Banknote;

import java.util.Collection;

@Value
public class AtmOperationResult {
    AtmOperationStatus status;
    Collection<Banknote> banknotes;

    @Override
    public String toString() {
        return "AtmOperationResult{" +
                "status=" + status +
                ", banknotes=" + banknotes +
                '}';
    }
}
