//import selenium
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        //ChromeDriver Path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\THIS PC\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Test Case: Login
        loginTest(driver, "palindifernando36@gmail.com", "SQA@1234");

        // Delay before closing the browser (8 seconds)
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }

    public static void loginTest(WebDriver driver, String username, String password) {
        // Navigate to the Threads login page
        driver.get("https://www.threads.net/login/?hl=en");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the login elements
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username, phone or email']")));
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        WebElement loginButton = driver.findElement(By.xpath("//div[@role='button' and contains(., 'Log in')]"));

        // Enter the username and password
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Click the login button
        loginButton.click();

        // Wait for the URL to change to the post-login URL
        boolean loginSuccess;
        try {
            wait.until(ExpectedConditions.urlToBe("https://www.threads.net/"));
            loginSuccess = true;
        } catch (Exception e) {
            loginSuccess = false;
        }

        // Output the result of the test
        if (loginSuccess) {
            System.out.println("Login Test Passed for username: " + username);
        } else {
            System.out.println("Login Test Failed for password: " + password);
        }
    }
}
