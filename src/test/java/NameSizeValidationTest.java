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
public class NameSizeValidationTest {

    private final String name;
    private final boolean expected;

    public NameSizeValidationTest(String name, boolean expected) {
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getNamesToCheck() {
        return new Object[][] {
                {"Р", false},                           //1 символ
                {"PR", false},                          //2 символа
                {"P e", true},                          //3 символа
                {"P ed", true},                         //4 символа
                {"PReddotra ff", true},                 //12 символов
                {"lififligl hlgfglug", true},           //18 символов
                {"lififligl hlgfglugl", true},          //19 символов
                {"lififligl hlgfglugla", false},        //20 символов
                {"lififligl hlgfgluglasdvbn", false},   //25 символов
        };
    }

    @Test
    @DisplayName("Валидация количества символов в имени")
    @Description("В данном тесте будет проверено соблюдение условия:\n в строке не меньше 3 и не больше 19 символов")
    public void nameSizeValidationTest() {
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        assertEquals(expected, actual);
    }
}
