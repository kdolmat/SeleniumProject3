import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selenium3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Inna\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://carfax.com");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[.='Find a Used Car']")).click();
        String title = driver.getTitle();
        if (title.contains("Used Cars")){
            System.out.println("contains");
        }
        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//select[@name='make']"))).selectByValue("Tesla");
        List<String> expected = Arrays.asList(" Model 3 ");
        List<WebElement> list = new Select(driver.findElement(By.xpath("//select[@class='form-control search-model  search-model--lp']"))).getOptions();
        List<String> models = new ArrayList<>();


        for (WebElement l:list) {
            models.add(l.getText());
        }
        if(models.contains(expected)){
            System.out.println("contains1");
        }


        new Select(driver.findElement(By.xpath("//select[@class='form-control search-model  search-model--lp']"))).selectByValue("Model S");

        driver.findElement(By.xpath("//input[@name='zip']")).clear();
        driver.findElement(By.xpath("//input[@name='zip']")).sendKeys("22182");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

//        List<WebElement> options = new Select(driver.findElements(By.xpath("//ul[@class='checkbox-list checkbox-list--left checkbox-list--large list-unstyled']"))).getOptions();
//        System.out.println(options.size());
        //to make options dynamic

        for (int i=0;i<4;i++){
            driver.findElement(By.xpath("//span[@class='checkbox-list-item--fancyCbx']")).click();
        }

        String count = driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText();
        driver.findElement(By.xpath("//span[@class='totalRecordsText']")).click();



        List<WebElement> options = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));






    }
}
