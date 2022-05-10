package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private ChromeDriver driver;

    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
    }

    private By headerBy = By.cssSelector("h2");
    private By emailBy = By.name("email");
    private By emailErrorBy = By.id("email-message");
    private By passwordBy = By.name("password");
    private By passwordErrorBy = By.id("password-message");
    private By loginButtonBy = By.id("login-button");


    public String getHeader() {
        return driver.findElement(headerBy).getText();
    }
    
    public String getEmailError() {
        return driver.findElement(emailErrorBy).getText();
    }

    public void setEmail(String email) {
        var emailTextbox = driver.findElement(emailBy);
        emailTextbox.sendKeys(email);
    }

    public String getPasswordError() {
        return driver.findElement(passwordErrorBy).getText();
    }

    public void setPassword(String password) {
        var passwordTextbox = driver.findElement(passwordBy);
        passwordTextbox.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonBy).click();
    }
}
