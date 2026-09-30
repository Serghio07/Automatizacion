package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver webDriver;
    private By userInput = By.cssSelector("input[name='username']");
    private By passWordInput = By.cssSelector("input[name='password']");
    private By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    private WebElement waitForElement(By locator){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public void typeUserName(String user){
        WebElement element = waitForElement(userInput);
        element.sendKeys(user);
    }

    public void typePassWord(String passWord){
        WebElement element = waitForElement(passWordInput);
        element.sendKeys(passWord);
    }

    public DashboardPage clickOnLoginButton(){
        WebElement element = waitForElement(loginButton);
        element.click();
        return new DashboardPage(webDriver);
    }

    public DashboardPage loginAs(String user, String passWord){
        typeUserName(user);
        typePassWord(passWord);
        return clickOnLoginButton();
    }
}
