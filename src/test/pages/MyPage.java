package test.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyPage {
    
    private ChromeDriver driver;

    public MyPage(ChromeDriver driver) {
        this.driver = driver;
    }

    private By headerBy = By.cssSelector("h2");
    private By emailBy = By.id("email");

    public String getHeader() {
        return driver.findElement(headerBy).getText();
    }

    public String getEmail() {
        return driver.findElement(emailBy).getText();
    }
}
