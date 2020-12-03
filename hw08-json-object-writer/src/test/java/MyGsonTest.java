import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class MyGsonTest {

    private MyGson myGson;
    private Gson gson;

    @BeforeEach
    void setUp() {
        myGson = new MyGson1();
        gson = new Gson();
    }

    @Test
    @DisplayName("тест из домашнего задания")
    void toJsonTest() {
        AnyObject obj = new AnyObject(true, 20, "Fedor", null, new int[]{1, 2, 3}, List.of("a", "b"));
        String myJson = myGson.toJson(obj);
        AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
        assertThat(obj).isEqualTo(obj2);
    }


    @Test
    @DisplayName("Дополнительный тест, проверящий работу вложенных коллекций.")
    void nestedCollectionTest() {
        List<List<String>> names_names = List.of(List.of("Irina", "Maria", "Vera"), List.of("Vladimir", "Anton", "Yaroslav"));
        NestedCollection nestedCollection = new NestedCollection(names_names);
        String json = myGson.toJson(nestedCollection);
        NestedCollection fromJson = gson.fromJson(json, NestedCollection.class);
        assertThat(nestedCollection).isEqualTo(fromJson);
    }

    @Test
    //@Disabled
    @DisplayName("Тест вложенных массивов. Не получается сделать, хоть в задании не было сказано соблюсти рекурсию.")
    void nestedArrayTest() {
        int[][] value = new int[][]{{1, 2, 3}, {4, 5, 6}};
        NestedArray nestedArray = new NestedArray(value);
        String json = myGson.toJson(nestedArray);
        NestedArray restored = gson.fromJson(json, NestedArray.class);
        assertThat(nestedArray).isEqualTo(restored);
    }


    @Test
    @DisplayName("Тест на отдельном классе, для разнообразия.")
    void toJson() {
        Human human = new Human("Roman", 38, List.of("Alex", "Rita", "Vlad"), 178.5f, new byte[]{4, 4, 3, 5, 4});
        String json = myGson.toJson(human);
        Human restored = gson.fromJson(json, Human.class);
        assertThat(human).isEqualTo(restored);
        System.out.println(json);
    }

    @Test
    @DisplayName("Проверка поля - объектов в массиве")
    void ArrayComplexObjectTest() {
        Human human = new Human("Roman", 38, List.of("Alex", "Rita", "Vlad"), 178.5f, new byte[]{1, 2, 3});
        Human human2 = new Human("Ivan", 38, List.of("Misha", "Nastya", "Gleb"), 178.5f, new byte[]{1, 2, 3});
        ComplexArrayHuman complexArrayHuman = new ComplexArrayHuman(new Human[]{human, human2});
        String json = myGson.toJson(complexArrayHuman);
        ComplexArrayHuman restored = gson.fromJson(json, ComplexArrayHuman.class);
        assertThat(complexArrayHuman).isEqualTo(restored);
    }

    @Test
    @DisplayName("Проверка поля - объектов в массиве")
    void CollectionComplexObjectTest() {
        Human human = new Human("Roman", 38, List.of("Alex", "Rita", "Vlad"), 178.5f, new byte[]{1, 2, 3});
        Human human2 = new Human("Ivan", 38, List.of("Misha", "Nastya", "Gleb"), 178.5f, new byte[]{1, 2, 3});
        ComplexCollectionHuman complexHuman = new ComplexCollectionHuman(List.of(human, human2));
        String json = myGson.toJson(complexHuman);
        ComplexCollectionHuman restored = gson.fromJson(json, ComplexCollectionHuman.class);
        assertThat(complexHuman).isEqualTo(restored);
    }

    @Test
    @DisplayName("Должна json = \"null\", если входной объект null")
    void nullTest() {
        assertThat(myGson.toJson(null)).isEqualTo("null");
        assertThat(myGson.toJson(null)).isEqualTo(gson.toJson(null));
    }

    @ParameterizedTest
    @MethodSource("generateDataForCustomTest")
    void customTest(Object o){
        assertThat(myGson.toJson(o)).isEqualTo(gson.toJson(o));
    }

    private static Stream<Arguments> generateDataForCustomTest() {
        return Stream.of(
                null,
                Arguments.of(true), Arguments.of(false),
                Arguments.of((byte)1), Arguments.of((short)2f),
                Arguments.of(3), Arguments.of(4L), Arguments.of(5f), Arguments.of(6d),
                Arguments.of("aaa"), Arguments.of('b'),
                Arguments.of(new byte[] {1, 2, 3}),
                Arguments.of(new short[] {4, 5, 6}),
                Arguments.of(new int[] {7, 8, 9}),
                Arguments.of(new float[] {10f, 11f, 12f}),
                Arguments.of(new double[] {13d, 14d, 15d}),
                Arguments.of(List.of(16, 17, 18)),
                Arguments.of(List.of(List.of(List.of(16, 17, 18), List.of(16, 17, List.of(16, 17, 18)), 18), List.of(16, 17, 18), List.of(16, 17, 18))),
                Arguments.of(Collections.singletonList(19))
        );
    }
}