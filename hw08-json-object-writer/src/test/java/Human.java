import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Human {
    private final String name;
    private final Integer age;
    private final List<String> friends;
    private final float height;
    private final byte[] marks;

    public Human(String name, Integer age, List<String> friends, float height, byte[] marks) {
        this.name = name;
        this.age = age;
        this.friends = friends;
        this.height = height;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getFriends() {
        return friends;
    }

    public float getHeight() {
        return height;
    }

    public byte[] getMarks() {
        return marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Float.compare(human.height, height) == 0 &&
                Objects.equals(name, human.name) &&
                Objects.equals(age, human.age) &&
                Objects.equals(friends, human.friends) &&
                Arrays.equals(marks, human.marks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, age, friends, height);
        result = 31 * result + Arrays.hashCode(marks);
        return result;
    }
}
