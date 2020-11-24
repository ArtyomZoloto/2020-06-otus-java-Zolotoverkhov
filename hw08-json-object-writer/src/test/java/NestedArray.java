import java.util.Arrays;

public class NestedArray {
    private final int[][] nestedArray;

    public NestedArray(int[][] nestedArray) {
        this.nestedArray = nestedArray;
    }

    public int[][] getNestedArray() {
        return nestedArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedArray that = (NestedArray) o;
        return Arrays.equals(nestedArray, that.nestedArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(nestedArray);
    }
}
