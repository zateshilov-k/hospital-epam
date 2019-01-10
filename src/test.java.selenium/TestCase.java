import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class TestCase {
    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8888/");

        System.out.println("Page title is: " + driver.getTitle());

        WebElement emailInput = driver.findElement(By.id("email_address"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        emailInput.sendKeys("login1@epam.com");
        passwordInput.sendKeys("password1");

        driver.findElement(By.id("loginSubmitButton")).submit();
        System.out.println("Page title is: " + driver.getTitle());

        WebElement patientTable = driver.findElement(By.id("example"));
        List<WebElement> rows = patientTable.findElements(By.tagName("tr"));
        rows.get(1).click();

        WebElement buttonToPatientPage = driver.findElement(By.id("buttonToPatientPage"));
        buttonToPatientPage.click();

        WebElement diagnosisDescription = driver.findElement(By.id("diagnosisDescription"));
        diagnosisDescription.sendKeys("Test diagnosis description");
        driver.findElement(By.id("diagnosisSubmit")).click();
    }
}
