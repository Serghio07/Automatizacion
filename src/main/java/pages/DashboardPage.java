package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriver webDriver;

    private By dashboardElement =
            By.cssSelector(".orangehrm-dashboard-grid");

    private By pimMenu =
            By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");

    public DashboardPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean isDashboardPageDisplayed(){
        try {
            WebDriverWait wait =
                    new WebDriverWait(
                            webDriver,
                            Duration.ofSeconds(10)
                    );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            dashboardElement
                    )
            );

            return true;

        }catch (Exception e){
            return false;
        }
    }

    public PimPage goToPim(){

        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                pimMenu
                        )
                );

        element.click();

        return new PimPage(webDriver);
    }
}