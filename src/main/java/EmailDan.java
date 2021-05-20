import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class EmailDan {
    private final static By EMAIL = By.xpath("//*[@id=\"identifierId\"]");
    private final static By EMAIL_NEXT = By.id("identifierNext");
    private final static By PASSWORD = By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input");
    private final static By NEXT = By.id("passwordNext");
    private final static By COMPOSE = By.xpath("//*[@id=\":k1\"]/div/div");
    private final static By ENLARGE = By.id(":mb");
    private final static By TO = By.xpath("//textarea[@name='to']");
    private final static By SUBJECT = By.xpath("//input[@name='subjectbox']");
    private final static By EMAIL_BODY = By.id("//*[@id=\":qb\"]");

     WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public EmailDan loadPage() {
        driver.manage().window().maximize();
       driver.get("https://gmail.com");
        return this;
    }

    public EmailDan enterEmail() {
        waitFor(EMAIL);
        driver.findElement(EMAIL).sendKeys("essam@ggfoods.co.nz");
        return this;
    }

    public EmailDan clickEmailNext() {
        waitFor(EMAIL_NEXT);
        driver.findElement(EMAIL_NEXT).click();
        return this;
    }

    public EmailDan enterPassword() {
        waitFor(PASSWORD);
        driver.findElement(PASSWORD).sendKeys("test");
        return this;
    }

    public EmailDan clickNext() {
        waitFor(NEXT);
        driver.findElement(NEXT).click();
        return this;
    }

    public EmailDan clickCompose() {
        waitFor(COMPOSE);
        driver.findElement(COMPOSE).click();
        return this;
    }


    public EmailDan typeEmailAddress() throws InterruptedException {
        Thread.sleep(4000);
        waitFor(TO);
        driver.findElement(TO).sendKeys("dan@malaysiarice.com.sg");

        return this;
    }

    public EmailDan typeSubject (String cdOrderNumber, String ggOrderNumber, String riceType) {
        waitFor(SUBJECT);
        driver.findElement(SUBJECT).sendKeys("Countdown Order " + cdOrderNumber + " GG " + ggOrderNumber + Keys.TAB
        + "Hi Dan," + Keys.ENTER + Keys.ENTER + "Please see the attached order for " + riceType + "." + Keys.ENTER + Keys.ENTER + "Thanks,");
        return this;
    }

//    public EmailDan typeEmail (String riceType) {
//        waitFor(EMAIL_BODY);
//        driver.findElement(EMAIL_BODY).sendKeys("Hi Dan," + Keys.ENTER + Keys.ENTER + "Please see the attached order for " + riceType + "." + Keys.ENTER + Keys.ENTER + "Thanks,");
//        return this;
//    }

    public void attachFile (String ggOrderNumber, String cdOrderNumber) {
        driver.findElement(By.xpath("//input[@name='Filedata']")).sendKeys("C:\\Users\\Essam\\Documents\\Countdown Real Rice Orders\\" + ggOrderNumber + cdOrderNumber + "\\WW Order Number " + ggOrderNumber + "" + cdOrderNumber + ".docx");
    }

    public void waitFor(By by) {
        wait.until(visibilityOfElementLocated(by));
        wait.until(elementToBeClickable(by));
    }






}
