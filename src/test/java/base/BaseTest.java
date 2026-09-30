package base;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BaseTest {
    private static final Logger log =
            Logger.getLogger(BaseTest.class.getName());
    protected WebDriver webDriver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws Exception {
        log.info("Navegador seleccionado: " + browser);

        switch (browser) {
            case "chrome":
                webDriver = new ChromeDriver(chromeSinGestorDeContrasenas());
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new Exception(browser + " no soportado");
        }

        log.info("Navegador iniciado correctamente");

        webDriver.manage().window().maximize();
        webDriver.get("https://opensource-demo.orangehrmlive.com/");

        log.info("Aplicacion abierta correctamente");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        log.info("Cerrando navegador");

        if(webDriver != null)
            webDriver.quit();
    }

    protected void pausaVisual(){
        pausaVisual(1);
    }

    protected void pausaVisual(int segundos){
        new Actions(webDriver)
                .pause(Duration.ofSeconds(segundos))
                .perform();
    }

    private ChromeOptions chromeSinGestorDeContrasenas(){
        Map<String, Object> preferencias = new HashMap<>();

        preferencias.put("credentials_enable_service", false);
        preferencias.put("profile.password_manager_enabled", false);
        preferencias.put("profile.password_manager_leak_detection", false);

        ChromeOptions opciones = new ChromeOptions();

        opciones.setExperimentalOption("prefs", preferencias);
        opciones.setPageLoadStrategy(PageLoadStrategy.EAGER);

        opciones.addArguments(
                "--disable-features=PasswordLeakDetection,AutofillServerCommunication"
        );

        return opciones;
    }
}
