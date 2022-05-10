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

    @BeforeEach
    void setUp() {
        driver.get("https://hotel.testplanisphere.dev/ja/login.html");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulLogin() {

        LoginPage loginPage = new LoginPage(driver);
        String successfulEmail = "ichiro@example.com";
        loginPage.setEmail(successfulEmail);
        loginPage.setPassword("password");
        loginPage.clickLoginButton();
        MyPage myPage = new MyPage(driver);
        assertEquals("マイページ", myPage.getHeader());
        assertEquals(successfulEmail, myPage.getEmail());
    }

    @Test
    public void emptyFields() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("");
        loginPage.setPassword("");
        loginPage.clickLoginButton();
        assertEquals("このフィールドを入力してください。", loginPage.getEmailError());
        assertEquals("このフィールドを入力してください。", loginPage.getPasswordError());
    }

    @Test
    public void invalidEmailFormat() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("incorrectFormat");
        loginPage.clickLoginButton();
        assertEquals("メールアドレスを入力してください。", loginPage.getEmailError());
    }

    @Test
    public void incorrectPassword() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("ichiro@example.com");
        loginPage.setPassword("incorrectPassword");
        loginPage.clickLoginButton();
        assertEquals("メールアドレスまたはパスワードが違います。", loginPage.getEmailError());
        assertEquals("メールアドレスまたはパスワードが違います。", loginPage.getPasswordError());
    }
}
