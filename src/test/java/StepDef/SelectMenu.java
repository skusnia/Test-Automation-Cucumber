package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectMenu {
    WebDriver driver;

    @Given("User Open Browser")
    public void userOpenBrowser() {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.setProperty("webdriver.chrome.driver", dir+"/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @And("^User go to https://demoqa\\.com/select-menu$")
    public void userGoToHttpsDemoqaComSelectMenu() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        Thread.sleep(1000);
    }

    @When("User in select menu page")
    public void userInSelectMenuPage() {//withOptGroup
        driver.findElement(By.id("selectMenuContainer")).isDisplayed();
    }

    @And("User choose select value {string}")
    public void userChooseSelectValue(String arg0) {
        driver.findElement(By.id("withOptGroup")).click();
        driver.findElement(By.xpath("//*[text() = \""+arg0+"\"]")).click();
    }

    @And("User choose select one {string}")
    public void userChooseSelectOne(String arg0) {
        driver.findElement(By.id("selectOne")).click();
        driver.findElement(By.xpath("//*[text() = \""+arg0+"\"]")).click();
    }

    @And("User choose old style select menu {string}")
    public void userChooseOldStyleSelectMenu(String arg0) {
        Select val = new Select(driver.findElement(By.id("oldSelectMenu")));
        val.selectByVisibleText(arg0);
    }

    @And("User choose multi select drop down {string}")
    public void userChooseMultiSelectDropDown(String arg0) throws InterruptedException {
        driver.findElement(By.className("css-1wa3eu0-placeholder")).click();
        WebElement big = driver.findElement(By.xpath("//div[contains(@class,'css-26l3qy-menu')]"));
        WebElement medium = big.findElement(By.xpath(".//div[contains(@class,'css-11unzgr')]"));
        Thread.sleep(1000);
        if (arg0.equals("all color")) {
            List<WebElement> allChildElements = medium.findElements(By.xpath(".//div[contains(@class, 'option')]"));

            for (int i = 0; i < allChildElements.size(); i++) {
                allChildElements.get(i).click();
                Thread.sleep(1000);
            }

        } else {
            medium.findElement(By.xpath("//div[text() = \""+arg0+"\"]")).click();
            driver.findElement(By.className("css-1wa3eu0-placeholder")).click();
        }
//        driver.findElement(By.xpath("//*[text() = \""+arg0+"\"]")).click();
    }
}
