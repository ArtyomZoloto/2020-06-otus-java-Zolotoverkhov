import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ComplexCollectionHuman {
    private List<Human> humans;

    public ComplexCollectionHuman(List<Human> humans) {
        this.humans = humans;
    }

    public List<Human> getHumans() {
        return humans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexCollectionHuman that = (ComplexCollectionHuman) o;
        return Objects.deepEquals(humans, that.humans);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(humans);
    }
}
