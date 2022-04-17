import com.ya.Account;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@Epic("Валидация данных банковской карты")
@Feature("Валидация имени для банковской карты")

@RunWith(Parameterized.class)
public class NameSizeValidationTest {

    private final String name;
    private final boolean expected;

    public NameSizeValidationTest(String name, boolean expected) {
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "  Проверяемое имя - {0} | Ожидаемый результат - {1}")
    public static Object[][] getNamesToCheck() {
        return new Object[][] {
                {null, false},                          // пришел null
                {"", false},                            // пустая строка
                {"Р", false},                           //1 символ
                {"А ", false},                          //2 символа
                {"а е", true},                          //3 символа
                {"Я Он", true},                         //4 символа
                {"Фёдор Иванов", true},                 //12 символов
                {"Владимир Соколович", true},           //18 символов
                {"Владимир Сокколович", true},          //19 символов
                {"Владимир Соколовичус", false},        //20 символов
                {"Вальдемариус Соколовичус", false},    //24 символа
        };
    }
    @Story("Валидация имени по количеству символов")
    @Test
    @Description("В данном тесте будет проверено соблюдение условия:\n в строке не меньше 3 и не больше 19 символов")
    public void nameSizeValidationTest() {
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        assertEquals(expected, actual);
    }
}
