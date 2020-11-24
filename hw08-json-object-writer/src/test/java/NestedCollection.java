import java.util.List;
import java.util.Objects;

public class NestedCollection {
    List<List<List<String>>> list;

    public NestedCollection(List<List<List<String>>> list) {
        this.list = list;
    }

    public List<List<List<String>>> getList() {
        return list;
    }

    public void setList(List<List<List<String>>> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedCollection that = (NestedCollection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
