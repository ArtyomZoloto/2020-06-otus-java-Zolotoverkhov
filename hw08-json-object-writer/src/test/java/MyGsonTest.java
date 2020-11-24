import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.AnyObject;
import ru.otus.JsonParseException;
import ru.otus.MyGson;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MyGsonTest {

    private MyGson myGson;
    private Gson gson;

    @BeforeEach
    void setUp() {
        myGson = new MyGson();
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
        List<List<List<String>>> names_names_names = List.of(names_names);
        NestedCollection nestedCollection = new NestedCollection(names_names_names);
        String json = myGson.toJson(nestedCollection);
        NestedCollection fromJson = gson.fromJson(json, NestedCollection.class);
        assertThat(nestedCollection).isEqualTo(fromJson);
    }

    @Test
    @Disabled
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
    @DisplayName("Тест выброса ошибки")
    void toJsonExceptionTest() {
        Human human = new Human("Roman", 38, List.of("Alex", "Rita", "Vlad"), 178.5f, new byte[]{1, 2, 3});
        Human human2 = new Human("Ivan", 38, List.of("Misha", "Nastya", "Gleb"), 178.5f, new byte[]{1, 2, 3});
        WrongHuman wrongHuman = new WrongHuman(new Human[]{human, human2});
        assertThatThrownBy(() -> {
            myGson.toJson(wrongHuman);
        }).isInstanceOf(JsonParseException.class);
    }
}