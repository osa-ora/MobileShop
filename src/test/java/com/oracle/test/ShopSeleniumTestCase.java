package test.java.com.oracle.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * Selenium test case for TestSession Project
 * @author ooransa
 *
 */
public class ShopSeleniumTestCase {
	public static final String BASE_URL = "base_url";
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities cap = ensureProxyFromEnv(System.getenv("HTTP_PROXY"));		
		// Open firefox browser
		driver = new FirefoxDriver(cap);
		baseUrl = System.getProperty(BASE_URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Initialized ...");
	}
	private DesiredCapabilities ensureProxyFromEnv(String proxyVal) {
		DesiredCapabilities cap = new DesiredCapabilities();
		if(proxyVal==null) return cap;
        // we should get http://ch3-opc-proxy.usdc2.oraclecloud.com:80 for Chicago DC
		URL proxyURL = null;
		try {
			proxyURL = new URL(proxyVal);
			// create Proxy 
			Proxy p = new Proxy();
	    	// Set the proxy ...
			p.setHttpProxy(proxyURL.getHost()+":"+proxyURL.getPort());
			p.setSslProxy(proxyURL.getHost()+":"+proxyURL.getPort());
			p.setFtpProxy(proxyURL.getHost()+":"+proxyURL.getPort());
			//p.setSocksUsername(null);
			//p.setSocksPassword(null);
			// Create desired Capability object
			// Pass proxy object p
			cap.setCapability(CapabilityType.PROXY, p);
		} catch (MalformedURLException mue) {
			System.out.println(mue.getMessage());
		}
    	return cap;
    }
	@Test
	public void testCart() throws Exception {
		driver.get(baseUrl + "index.jsp");
		driver.findElement(By.id("cart")).click();
		driver.findElement(By.id("back")).click();
	}
	@Test
	public void testJourney() throws Exception {
		driver.get(baseUrl + "index.jsp");
		driver.findElement(By.id("2")).click();
		driver.findElement(By.id("3")).click();
		driver.findElement(By.id("cart")).click();
		driver.findElement(By.id("back")).click();
		driver.findElement(By.id("1")).click();
		driver.findElement(By.id("cart")).click();
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Osama Oransa");
		driver.findElement(By.id("address")).clear();
		driver.findElement(By.id("address")).sendKeys("Cairo, Egypt");
		driver.findElement(By.id("insert")).click();
		driver.findElement(By.id("back")).click();
	}
	@Test
	public void testCheckoutValidation() throws Exception {
		driver.get(baseUrl + "index.jsp");
		driver.findElement(By.id("2")).click();
		driver.findElement(By.id("3")).click();
		driver.findElement(By.id("cart")).click();
		driver.findElement(By.id("back")).click();
		driver.findElement(By.id("1")).click();
		driver.findElement(By.id("cart")).click();
		driver.findElement(By.id("checkout")).click();
	    driver.findElement(By.id("insert")).click();
	    assertEquals("Please enter valid name!", closeAlertAndGetItsText());
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("Osama Oransa");
	    driver.findElement(By.id("insert")).click();
	    assertEquals("Please enter valid address!", closeAlertAndGetItsText());
	    driver.findElement(By.id("address")).clear();
	    driver.findElement(By.id("address")).sendKeys("Cairo, Egypt");
	    driver.findElement(By.id("insert")).click();
	    // Take snapshot of browser
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File targetFile= new File("target/surefire-reports/screenshot.png");
        targetFile.getParentFile().mkdirs();
        targetFile.delete();
        Files.copy(srcFile.toPath(),targetFile.toPath());
        System.out.println("File :"+ targetFile.getAbsolutePath());
		driver.findElement(By.id("back")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
