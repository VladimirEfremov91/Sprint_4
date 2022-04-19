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
public class NameSpaceValidationTest {
    private final String name;
    private final boolean expected;

    public NameSpaceValidationTest(String name, boolean expected) {
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "  Проверяемое имя - /{0}/ | Ожидаемый результат - {1}")
    public static Object[][] getNamesToCheck() {
        return new Object[][] {
                {"ЯнКим", false},
                {" ЯнКим", false},
                {"ЯнКим ", false},
                {" ЯнКим ", false},
                {"Ян Ким", true},
                {"Ян  Ким", false},
                {" Ян Ким", false},
                {"Ян Ким ", false},
                {" Ян Ким ", false},
        };
    }
    @Story("Валидация имени по наличию и расположению пробелов")
    @Test
    @Description("В данном тесте будет проверено соблюдение условий:\n  - в строке есть только один пробел;\n  - пробел стоит не в начале и не в конце строки.")
    public void nameSpaceValidationTest() {
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        assertEquals(expected, actual);
    }
}
