package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {
    private WebDriver webDriver;
    private By firstNameInput =
            By.xpath("//input[@name='firstName']");
    private By middleNameInput =
            By.xpath("//input[@name='middleName']");
    private By lastNameInput =
            By.xpath("//input[@name='lastName']");
    private By employeeIdInput =
            By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input");
    private By createLoginDetailsSwitch =
            By.xpath("//p[normalize-space()='Create Login Details']/following-sibling::div//span[contains(@class,'oxd-switch-input')]");
    private By formLoader =
            By.cssSelector("div.oxd-form-loader");
    private By usernameInput =
            By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div/input");
    private By passwordInput =
            By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::div/input");
    private By confirmPasswordInput =
            By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div/input");
    private By enabledStatus =
            By.xpath("//label[normalize-space()='Enabled']//span[contains(@class,'oxd-radio-input')]");
    private By disabledStatus =
            By.xpath("//label[normalize-space()='Disabled']//span[contains(@class,'oxd-radio-input')]");
    private By saveButton =
            By.xpath("//button[@type='submit' and normalize-space()='Save']");
    private By personalDetailsTitle =
            By.xpath("//h6[normalize-space()='Personal Details']");
    private By employeeListTab =
            By.xpath("//a[normalize-space()='Employee List']");
    private By employeeInformationTitle =
            By.xpath("//h5[normalize-space()='Employee Information']");

    public AddEmployeePage(WebDriver webDriver){
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

    public void typeFirstName(String firstName){
        WebElement element = waitForElement(firstNameInput);
        element.clear();
        element.sendKeys(firstName);
    }

    public void typeMiddleName(String middleName){
        WebElement element = waitForElement(middleNameInput);
        element.clear();
        element.sendKeys(middleName);
    }

    public void typeLastName(String lastName){
        WebElement element = waitForElement(lastNameInput);
        element.clear();
        element.sendKeys(lastName);
    }

    public void typeEmployeeId(String employeeId){
        WebElement element = waitForElement(employeeIdInput);
        element.clear();
        element.sendKeys(employeeId);
    }

    public void enableLoginDetails(){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        formLoader
                )
        );

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                createLoginDetailsSwitch
                        )
                );

        element.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameInput
                )
        );
    }

    public void typeUsername(String username){
        WebElement element = waitForElement(usernameInput);
        element.clear();
        element.sendKeys(username);
    }

    public void typePassword(String password){
        WebElement element = waitForElement(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void typeConfirmPassword(String password){
        WebElement element = waitForElement(confirmPasswordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void selectStatus(String status){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        By statusLocator;

        if(status.equalsIgnoreCase("Enabled")){
            statusLocator = enabledStatus;
        }else if(status.equalsIgnoreCase("Disabled")){
            statusLocator = disabledStatus;
        }else {
            throw new IllegalArgumentException(
                    "Status no reconocido: " + status
            );
        }

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                statusLocator
                        )
                );

        element.click();
    }

    public void clickSave(){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                saveButton
                        )
                );

        element.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        personalDetailsTitle
                )
        );
    }

    public EmployeeListPage goToEmployeeList(){
        WebDriverWait wait =
                new WebDriverWait(
                        webDriver,
                        Duration.ofSeconds(20)
                );

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                employeeListTab
                        )
                );

        element.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        employeeInformationTitle
                )
        );

        EmployeeListPage employeeListPage =
                new EmployeeListPage(webDriver);

        employeeListPage.scrollDownALittle();

        return employeeListPage;
    }

    public void createEmployee(
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String username,
            String password,
            String status
    ){
        typeFirstName(firstName);
        typeMiddleName(middleName);
        typeLastName(lastName);
        typeEmployeeId(employeeId);

        enableLoginDetails();

        typeUsername(username);
        typePassword(password);
        typeConfirmPassword(password);

        selectStatus(status);

        clickSave();
    }
}
