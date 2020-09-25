import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TutBySearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void searchByKeyWord() {
        Random random = new Random();
        driver.get("https://jobs.tut.by/");
        driver.findElement(By.cssSelector("[data-qa=search-input]")).sendKeys("QA");
        driver.findElement(By.cssSelector("[data-qa=search-button]")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("[data-qa='vacancy-serp__vacancy-employer']"));
        int searchSize = elements.size();
        for (int i = 0; i < 3; i++) {
            String companyName = elements.get(random.nextInt(searchSize)).getText();
            System.out.println(companyName);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}