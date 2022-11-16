package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectMenu {
    WebDriver driver;

    @And("^User go to https://demoqa\\.com/select-menu$")
    public void userGoToHttpsDemoqaComSelectMenu() throws InterruptedException {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.setProperty("webdriver.chrome.driver", dir+"/driver/chromedriver.exe");
        driver = new ChromeDriver();

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
        driver.findElement(By.xpath("//*[text() = 'Another root option']")).click();
    }

    @And("User choose select one {string}")
    public void userChooseSelectOne(String arg0) {
        driver.findElement(By.id("selectOne")).click();
        driver.findElement(By.xpath("//*[text() = 'Other']")).click();
    }

    @And("User choose old style select menu {string}")
    public void userChooseOldStyleSelectMenu(String arg0) {
        Select val = new Select(driver.findElement(By.id("oldSelectMenu")));
        val.selectByVisibleText("Aqua");
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
                Thread.sleep(3000);
            }

        } else {
            medium.findElement(By.xpath("//div[text() = \""+arg0+"\"]")).click();
            driver.findElement(By.className("css-1wa3eu0-placeholder")).click();
        }
//        driver.findElement(By.xpath("//*[text() = \""+arg0+"\"]")).click();
    }

    @Given("User go to https:\\/\\/demoqa.com\\/books")
    public void userGoToHttpsDemoqaComBooks() throws InterruptedException {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.setProperty("webdriver.chrome.driver", dir+"/driver/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://demoqa.com/books");
        Thread.sleep(1000);
    }

    @When("User in {string} page")
    public void userInPage(String arg0) {
//        driver.findElement(By.xpath("//*[text() = \""+arg0+"\"]")).isDisplayed();
        driver.findElement(By.xpath("//div[text() = 'Book Store']")).isDisplayed();
    }

    @And("User search book {string}")
    public void userSearchBook(String arg0) {
        driver.findElement(By.id("searchBox")).isDisplayed();
        driver.findElement(By.id("searchBox")).sendKeys("qa engineer");
    }

    @Then("User see {string}")
    public void userSee(String arg0) {
        driver.findElement(By.xpath("//div[text()='No rows found']")).isDisplayed();
    }
}
