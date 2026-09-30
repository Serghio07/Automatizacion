package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    // El login ya está incluido en EmployeeTests y queremos una sola prueba activa.
    @Test(enabled = false)
    public void testSuccessfulLogin(){
        LoginPage loginPage =   
                new LoginPage(webDriver);

        DashboardPage dashboardPage =
                loginPage.loginAs("Admin","admin123");

        Assert.assertTrue(
                dashboardPage.isDashboardPageDisplayed()
        );
    }
}
