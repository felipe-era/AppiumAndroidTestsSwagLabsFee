package stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {

    public static AppiumDriver driver;
    public static Scenario scenarioGlobal;
    private static boolean driverInicializado = false;

    @Before
    public void before(Scenario scenario) {
        scenarioGlobal = scenario;
    }


    @BeforeAll
    public static void setUp() throws MalformedURLException, InterruptedException {
        if (!driverInicializado) {
            System.out.println("🔵 Inicializando driver Appium! 🔵");

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("autoGrantPermissions", true);
            cap.setCapability("deviceName", "f4c3623d0305");
            cap.setCapability("udid", "f4c3623d0305");
            cap.setCapability("platformName", "Android");
            cap.setCapability("platformVersion", "9");
            cap.setCapability("automationName", "UiAutomator2");
            cap.setCapability("appPackage", "com.swaglabsmobileapp");
            cap.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

            URL url = new URL("http://127.0.0.1:4723");
            driver = new AppiumDriver(url, cap);

            driverInicializado = true;
            System.out.println("✅ Driver Appium inicializado!");
            Thread.sleep(5000);
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driverInicializado && driver != null) {
            driver.quit();
            System.out.println("| Driver Appium FINALIZADO! | ");
        }
    }
}
