package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running app during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isWorking(ChromeDriver driver) throws Exception {
        driver.get(baseUrl+"/product/list");
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        for (WebElement e : elements) {
            if (e.getText().equals("Create Product")) {
                e.click();
                break;
            }
        }
        driver.findElement(By.id("nameInput")).sendKeys("puyed");
        driver.findElement(By.id("quantityInput")).sendKeys("1");
        driver.findElement(By.tagName("button")).click();
        List<WebElement> tableContent = driver.findElements(By.xpath("/html/body/div/table/tbody/tr/td"));
        assertEquals("puyed", tableContent.getFirst().getText());
        assertEquals("1", tableContent.get(1).getText());
    }
}
