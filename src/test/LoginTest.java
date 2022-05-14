package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import test.pages.LoginPage;
import test.pages.MyPage;

public class LoginTest {
    private static ChromeDriver driver = new ChromeDriver();
    private String landingPage = "https://hotel.testplanisphere.dev/ja/login.html";
    private String successfulEmail = "ichiro@example.com";

    @BeforeEach
    void setUp() {
        driver.get(landingPage);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulLogin() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginDetails(successfulEmail, "password");
        MyPage myPage = new MyPage(driver);
        assertEquals("マイページ", myPage.getHeader());
        assertEquals(successfulEmail, myPage.getEmail());
    }

    @Test
    public void emptyFields() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginDetails("", "");
        assertEquals("このフィールドを入力してください。", loginPage.getEmailError());
        assertEquals("このフィールドを入力してください。", loginPage.getPasswordError());
    }

    @Test
    public void invalidEmailFormat() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginDetails("incorrectFormat", "");
        assertEquals("メールアドレスを入力してください。", loginPage.getEmailError());
    }

    @Test
    public void incorrectPassword() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginDetails(successfulEmail, "incorrectPassword");
        assertEquals("メールアドレスまたはパスワードが違います。", loginPage.getEmailError());
        assertEquals("メールアドレスまたはパスワードが違います。", loginPage.getPasswordError());
    }
    
    @Test
    public void restrictedCharactersInEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginDetails("invalid@432$/.com", "");
        assertEquals("メールアドレスを入力してください。", loginPage.getEmailError());
    }
}
