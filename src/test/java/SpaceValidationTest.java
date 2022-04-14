import com.ya.Account;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@Epic("Валидация данных банковской карты")
@Feature("Валидация имени для банковской карты")

@RunWith(Parameterized.class)
public class SpaceValidationTest {
    private final String name;
    private final boolean expected;

    public SpaceValidationTest(String name, boolean expected) {
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getNamesToCheck() {
        return new Object[][] {
                {"PRed", false},
                {" PRed", false},
                {"PRed ", false},
                {" PRed ", false},
                {"PR ed", true},
                {"PR  ed", false},
                {" PR ed", false},
                {"PR ed ", false},
                {" PR ed ", false},
        };
    }

    @Test
    @DisplayName("Валидация пробелов в имени")
    @Description("В данном тесте будет проверено соблюдение условий:\n  - в строке есть только один пробел;\n  - пробел стоит не в начале и не в конце строки.")
    public void SpaceValidationTest() {
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        assertEquals(expected, actual);
    }
}
