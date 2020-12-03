import java.util.Arrays;

public class ComplexArrayHuman {
    private final Human[] humans;

    public ComplexArrayHuman(Human[] humans) {
        this.humans = humans;
    }

    public Human[] getHumans() {
        return humans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexArrayHuman that = (ComplexArrayHuman) o;
        return Arrays.deepEquals(humans, that.humans);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(humans);
    }
}
