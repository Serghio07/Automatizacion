package pim;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PimPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class EmployeeTests extends BaseTest {
    private static final Logger log =
            Logger.getLogger(EmployeeTests.class.getName());

    @DataProvider(name = "employees")
    public Object[][] employees() throws IOException {
        InputStream inputStream =
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("employees.csv");

        if(inputStream == null){
            throw new IllegalStateException(
                    "No se encontro el archivo employees.csv"
            );
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     inputStream,
                                     StandardCharsets.UTF_8
                             )
                     )) {
            return reader.lines()
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(line -> line.split(",", -1))
                    .toArray(String[][]::new);
        }
    }

    @Test(dataProvider = "employees")
    public void testCreateEmployee(
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String username,
            String password,
            String status
    ) throws Exception {
        String unique =
                Long.toString(
                        System.currentTimeMillis(),
                        36
                ).toUpperCase();

        String uniqueFirstName =
                firstName + "_" + unique;

        String uniqueUsername =
                username + unique;

        try {
            log.info("Iniciando prueba para: " + uniqueFirstName);

            System.out.println(
                    "INFO: Creando " + uniqueFirstName
                            + " con username " + uniqueUsername
                            + " y status " + status
            );

            log.info("Realizando login");

            LoginPage loginPage =
                    new LoginPage(webDriver);

            DashboardPage dashboardPage =
                    loginPage.loginAs("Admin","admin123");

            pausaVisual();

            log.info("Ingresando a PIM");

            PimPage pimPage =
                    dashboardPage.goToPim();

            pausaVisual();

            log.info("Creando empleado: " + uniqueFirstName);

            AddEmployeePage addEmployeePage =
                    pimPage.goToAddEmployee();

            pausaVisual();

            addEmployeePage.createEmployee(
                    uniqueFirstName,
                    middleName,
                    lastName,
                    employeeId,
                    uniqueUsername,
                    password,
                    status
            );

            pausaVisual();

            log.info("Buscando empleado: " + uniqueFirstName);

            EmployeeListPage employeeListPage =
                    addEmployeePage.goToEmployeeList();

            pausaVisual();

            System.out.println(
                    "INFO: Buscando " + uniqueFirstName
            );

            employeeListPage.searchEmployeeByName(
                    uniqueFirstName
            );

            pausaVisual(2);

            log.info("Validando empleado en la grilla");

            Assert.assertTrue(
                    employeeListPage.isEmployeeDisplayed(
                            uniqueFirstName
                    )
            );

            log.info("OK");
        } catch (Exception | AssertionError e) {
            log.severe("ERROR");
            throw e;
        }
    }
}
