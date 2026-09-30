package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PimPage {
    private WebDriver webDriver;
    private By pimElement =
            By.xpath("//h5[normalize-space()='Employee Information']");
    private By addButton =
            By.xpath("//button[normalize-space()='Add']");

    public PimPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public boolean isPimPageDisplayed(){
        try {
            WebDriverWait wait =
                    new WebDriverWait(
                            webDriver,
                            Duration.ofSeconds(10)
                    );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            pimElement
                    )
            );

            return true;

        }catch (Exception e){
            return false;
        }
    }

    public AddEmployeePage goToAddEmployee(){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                addButton
                        )
                );

        element.click();

        return new AddEmployeePage(webDriver);
    }
}
