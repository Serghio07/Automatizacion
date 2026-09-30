package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage {
    private WebDriver webDriver;
    private By employeeNameInput =
            By.xpath("//label[normalize-space()='Employee Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']");
    private By searchButton =
            By.xpath("//button[@type='submit' and normalize-space()='Search']");
    private By employeeInformationTitle =
            By.xpath("//h5[normalize-space()='Employee Information']");
    private By autocompleteOptions =
            By.xpath("//div[@role='listbox']//div[@role='option']");
    private By resultRows =
            By.xpath("//div[contains(@class,'oxd-table-body')]//div[contains(@class,'oxd-table-card')]");
    private By noRecordsFound =
            By.xpath("//span[normalize-space()='No Records Found']");

    public EmployeeListPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void scrollDownALittle(){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        employeeInformationTitle
                )
        );

        JavascriptExecutor javascriptExecutor =
                (JavascriptExecutor) webDriver;

        javascriptExecutor.executeScript(
                "window.scrollBy(0, 350);"
        );
    }

    public void searchEmployeeByName(String employeeName){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        employeeInformationTitle
                )
        );

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeNameInput
                        )
                );

        input.clear();
        input.sendKeys(employeeName);

        WebElement option =
                wait.until(driver -> {
                    List<WebElement> options =
                            driver.findElements(autocompleteOptions);

                    for(WebElement currentOption : options){
                        if(currentOption.isDisplayed()
                                && currentOption.getText().contains(employeeName)){
                            return currentOption;
                        }
                    }

                    return null;
                });

        option.click();

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                searchButton
                        )
                );

        button.click();

        wait.until(driver -> {
            List<WebElement> rows =
                    driver.findElements(resultRows);

            for(WebElement row : rows){
                if(row.getText().contains(employeeName)){
                    return true;
                }
            }

            return !driver.findElements(noRecordsFound).isEmpty();
        });
    }

    public boolean isEmployeeDisplayed(String employeeName){
        List<WebElement> rows =
                webDriver.findElements(resultRows);

        for(WebElement row : rows){
            if(row.getText().contains(employeeName)){
                return true;
            }
        }

        return false;
    }
}
