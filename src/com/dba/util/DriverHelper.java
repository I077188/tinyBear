package com.dba.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dba.constants.CONSTAINTS;

public class DriverHelper {

	private static int TIMEOUT = 15;

	public static void waitForUrlPresent(final String urlFragment, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.urlContains(urlFragment));
	}

	public static void waitForElementPresent(By byLocator, WebDriver driver) {
		waitForElementPresent(byLocator, driver, TIMEOUT);
	}

	public static void waitForElementPresent(By locator, WebDriver driver, long timeout) {
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForElementPresent(final By byLocator, WebDriver driver, final String contain) {
		(new WebDriverWait(driver, TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(byLocator).getText().contains(contain);
			}
		});
	}

	public static void waitForElementVisibility(By byLocator, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public static void waitForElementVisibility(By byLocator, int timeout, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	}

	public static void waitForElementInvisibility(By byLocator, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	}

	public static void waitForElementClickable(By byLocator, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.elementToBeClickable(byLocator));
	}

	public static void waitForElementClickable(By byLocator, int timeout, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.elementToBeClickable(byLocator));
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	}

	public static void waitForElementClickable(WebElement we, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions.elementToBeClickable(we));
	}

	public static void waifForAjax(WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				return (Boolean) js.executeScript("return jQuery.active == 0");
			}
		});
	}

	public static void waitUntilElementsCount(By byLocator, int number, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return (driver.findElements(byLocator).size() == number ? true : false);
			}
		});
	}

	public static void waitForEnabledInCssClass(By byLocator, WebDriver driver) {
		(new WebDriverWait(driver, TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return !driver.findElement(byLocator).getAttribute("class").contains("disabled");
			}
		});
	}

	public static boolean isElementPresent(By byLocator, WebDriver driver) {
		// (new WebDriverWait(driver,
		// 5)).until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return (driver.findElements(byLocator).size() == 0 ? false : true);
	}

	public static boolean isElementPresent(By baseBy, By relativeBy, WebDriver driver) {
		try {
			driver.findElement(baseBy).findElement(relativeBy);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

		// return (driver.findElement(baseBy).findElements(relativeBy).size() ==
		// 0 ? false : true);
	}

	public static boolean isElementPresent(By byLocator, int timeout, WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			driver.findElement(byLocator);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}

	public static boolean isElementVisible(By byLocator, WebDriver driver) {
		return driver.findElement(byLocator).isDisplayed();
	}

	public static boolean isElementVisible(By byLocator, int timeout, WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			driver.findElement(byLocator);
			return driver.findElement(byLocator).isDisplayed();
		} catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}

	public static void waitForFileDownload(String filePath) throws Exception {
		File file = new File(filePath);
		long start_time = System.currentTimeMillis();
		long wait_time = 10000;
		long end_time = start_time + wait_time;
		while (!file.exists() || (file.length() < 20)) {
			Thread.sleep(3000);
			if (System.currentTimeMillis() < end_time) {
				break;
			}
		}
	}

	public static void captureScreenshot(String fileName, WebDriver driver) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void capturePageSource(String fileName, WebDriver driver) {
		try {
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
			writer.append(driver.getPageSource());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Popup window
	public static WebDriver switchToNewWindow(WebDriver currentDriver) {
		// Get current window
		String currentWindow = currentDriver.getWindowHandle();
		// Get all windows
		Set<String> handles = currentDriver.getWindowHandles();

		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			WebDriver newWindow = currentDriver.switchTo().window(handle);
			return newWindow;
		}

		return currentDriver;
	}

	// Close new window
	public static WebDriver closeNewWindow(WebDriver currentDriver) {
		// Get current window
		String currentWindow = currentDriver.getWindowHandle();
		// Get all windows
		Set<String> handles = currentDriver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			WebDriver newWindow = currentDriver.switchTo().window(handle);
			// Close popup window, then switch to original window
			newWindow.close();
			currentDriver.switchTo().window(currentWindow);
		}

		return currentDriver;
	}

	// Added for scolling
	public static void scollTo(WebDriver driver, WebElement focus) throws Exception {
		try {
			// System.out.println("aaa");
			JavascriptExecutor scroll = (JavascriptExecutor) driver;
			scroll.executeScript("arguments[0].scrollIntoView(false);", focus);
			Thread.sleep(100);

		} catch (Exception e) {
			throw e;
		}
	}

	public static void scollToBottom(WebDriver driver) throws Exception {
		try {
			String setscroll = "document.documentElement.scrollTop=100000";

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(setscroll);
			Thread.sleep(100);

		} catch (Exception e) {
			throw e;
		}
	}

	// Added for scoll and click
	public static void scollAndClick(WebDriver driver, WebElement focus) throws Exception {
		scollAndWaitAndClick(driver, focus, 50);
	}

	public static void scollAndClick(WebDriver driver, By by) throws Exception {
		scollAndWaitAndClick(driver, driver.findElement(by), 200);
	}

	// Added for scoll and click
	public static void scollAndWaitAndClick(WebDriver driver, WebElement focus, long ms) throws Exception {
		try {
			// System.out.println("aaa");
			JavascriptExecutor scroll = (JavascriptExecutor) driver;
			scroll.executeScript("arguments[0].scrollIntoView(false);", focus);
			Thread.sleep(ms);
			// System.out.println("bbb");

			focus.click();
			// System.out.println("ccc");

		} catch (Exception e) {
			throw e;
		}
	}

	// Added for scoll and click
	public static void scollAndDoubleClick(WebDriver driver, WebElement focus) throws Exception {
		scollAndWaitAndDoubleClick(driver, focus, 200);
	}

	// Added for scoll and click
	public static void scollAndWaitAndDoubleClick(WebDriver driver, WebElement focus, long ms) throws Exception {
		try {
			// System.out.println("aaa");
			JavascriptExecutor scroll = (JavascriptExecutor) driver;
			scroll.executeScript("arguments[0].scrollIntoView(false);", focus);
			Thread.sleep(ms);
			// System.out.println("bbb");
			Actions action = new Actions(driver).doubleClick(focus);
			action.build().perform();

			// System.out.println("ccc");

		} catch (Exception e) {
			throw e;
		}
	}

	public static void moveToElementAndSendKey(WebDriver driver, WebElement element, String key)
			throws InterruptedException {

		String node = null;
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();

		// group String
		int i = key.indexOf(";");

		if (i < 0) {
			actions.sendKeys(key);
			actions.build().perform();
		} else {
			if (i == 0) {
				String tempt = key.substring(1).trim();
				int j = tempt.indexOf(";");
				node = tempt.substring(0, j).trim();
			} else {
				String tempt = key.substring(0, i).trim();
				node = tempt.substring(0, tempt.indexOf(" "));
			}

			actions.click();
			actions.sendKeys(key);
			actions.build().perform();
			DriverHelper.waitForElementPresent(By.xpath("//span[contains(text(),'" + node + "')]"), driver);
		}

	}

	public static void moveToElementAndSendKeyDerictly(WebDriver driver, WebElement element, String key)
			throws InterruptedException {

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();

		actions.sendKeys(key);
		actions.build().perform();
		// DriverHelper.waitForElementPresent(By.xpath("//span[contains(text(),'"
		// + node
		// + "')]"), driver);
		Thread.sleep(3000);
	}

	public static void moveToElement(WebDriver driver, WebElement element) throws InterruptedException {

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.build().perform();

	}

	public static void focusOn(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.focus();");
	}

	public static void wait(WebDriver driver, By by) {
		long a = System.currentTimeMillis();
		while (isElementPresent(driver, by) == false) {
			long b = System.currentTimeMillis();
			boolean breakSign = ((b - a) > 70000);
			// System.out.println("Break Sign is "+breakSign);
			if (breakSign) {
				System.out.println("Wait " + by + " timeout for 1 minute 10 seconds, break waiting.");
				return;
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void waitAndClick(WebDriver driver, By by) {
		wait(driver, by);
		try {
			scollAndClick(driver, driver.findElement(by));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitAndClick(WebDriver driver, WebElement we) throws Exception {
		Thread.sleep(2000);
		scollAndClick(driver, we);
	}

	public static void waitAndNativeClick(WebDriver driver, By by) throws Exception {
		wait(driver, by);
		driver.findElement(by).click();
	}

	public static void waitClickableAndJsClick(WebDriver driver, By by) throws Exception {
		DriverHelper.waitForElementClickable(by, driver);
		DriverHelper.jsClick(by, driver);
	}

	public static void waitClickableAndJsClick(WebDriver driver, WebElement we) throws Exception {
		DriverHelper.waitForElementClickable(we, driver);
		DriverHelper.jsClick(we, driver);
	}

	public static void waitClickableAndClick(WebDriver driver, By by) throws Exception {
		DriverHelper.waitForElementClickable(by, driver);
		driver.findElement(by).click();
	}

	public static void waitAndDoubleClick(WebDriver driver, By by) throws Exception {
		wait(driver, by);
		scollAndDoubleClick(driver, driver.findElement(by));
	}

	public static void waitAndType(WebDriver driver, By by, String value) throws Exception {
		wait(driver, by);
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
		Thread.sleep(3000);
	}

	public static void clickLinkRandomly(WebDriver driver, By by) {
		try {
			wait(driver, by);
			List<WebElement> list = driver.findElements(by);
			int pos = (int) (list.size() * Math.random());
			pos = pos < list.size() ? pos : (pos - 1);
			if (pos < 0) {
				pos = 0;
			}
			scollAndClick(driver, list.get(pos));
		} catch (Exception e) {

		}
	}

	public static boolean isElementPresent(WebDriver driver, By by) {
		try {
			// System.out.println("Looking for element "+by.toString());
			driver.findElement(by);
			// System.out.println("FOUND!!!");
			return true;
		} catch (NoSuchElementException e) {
			// System.out.println("NOT FOUND!!!");
			return false;
		}
	}

	public static void doubleClick(By by, WebDriver driver) {
		Actions action = new Actions(driver).doubleClick(driver.findElement(by));
		action.build().perform();
	}

	public static void doubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().build().perform();
	}

	public static void rightClick(By by, WebDriver driver) {
		Actions action = new Actions(driver).contextClick(driver.findElement(by));
		action.build().perform();
	}

	public static void dragAndDrop(By bySource, By byTarget, WebDriver driver) {
		Actions action = new Actions(driver).dragAndDrop(driver.findElement(bySource), driver.findElement(byTarget));
		action.build().perform();
	}

	public static void sendEnter(By by, WebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(by), Keys.ENTER).perform();
	}

	public static void sendESC(By by, WebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(by), Keys.ESCAPE).perform();
	}

	public static void mouseOver(By by, WebDriver driver) {
		try {
			new Actions(driver).moveToElement(driver.findElement(by)).perform();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void switchToParentWindow(WebDriver driver) {
		// String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		if (iterator.hasNext()) {
			subWindowHandler = iterator.next();
			driver.switchTo().window(subWindowHandler);
		}
	}

	public static void switchToParentWindow(WebDriver driver, String parentWindowHandler) {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("Child window is closed already");
		}
		driver.switchTo().window(parentWindowHandler);
	}

	public static String switchToChildWindow(WebDriver driver) {
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);

		return parentWindowHandler;
	}

	public static void jsClick(By by, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
	}

	public static void jsClick(WebElement we, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
	}

	public static void jsScrollAndClick(By by, WebDriver driver) throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
		Thread.sleep(2000);
		je.executeScript("arguments[0].click();", driver.findElement(by));
		Thread.sleep(2000);
	}

	public static void scrollToBottom(WebDriver driver) throws Exception {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
	}

	public static void scrollToTop(WebDriver driver) throws Exception {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
	}

	public static void alert(WebDriver driver, String acceptOrDismiss) throws Exception {
		Alert alert = driver.switchTo().alert();
		if ("accept".equals(acceptOrDismiss)) {
			alert.accept();
		} else {
			alert.dismiss();
		}
		Thread.sleep(2000);
	}

	public static String closeAlertAndGetItsText(WebDriver driver, boolean accept) {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (accept) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} catch (Exception e) {
			System.out.println("Exception when closing alert");
		}
		return "";
	}

	public static void scrollToElement(By by, WebDriver driver) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
		Thread.sleep(2000);
	}

	public static void scrollToElementAndWait(WebElement element, WebDriver driver, int ms) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(ms);
	}

	public static void checkAlert(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// exception handling
		}
	}
	
	public static void waitPageFullyLoad(WebDriver driver) {

		JavascriptExecutor jsExe = (JavascriptExecutor) driver;

		// Initially bellow given if condition will check ready state of page.
		if (jsExe.executeScript("return document.readyState").toString().equals("complete")) {
			CONSTAINTS.LOG.info("Page Is loaded.");
			return;
		}

		for (int i = 0; i < CONSTAINTS.waitTime; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			// To check page ready state.
			if (jsExe.executeScript("return document.readyState").toString().equals("complete")) {
				return;
			}
		}
	}

	public static void type(WebDriver driver, By by, String value) throws Exception {
		// wait(driver, by);
		for (int i = 0; i < 3; i++) {
			try {
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys(value);
				Thread.sleep(200);
				if (driver.findElement(by).getAttribute("value").equals(value)) {
					System.out.println("Value " + value + " is typed successfully in Browser.");
					return;
				} else {
					System.out
							.println("Failed to type " + value + " in Browser with xpath " + by + "\nRetrying typing");
				}

			} catch (Exception e) {

			} finally {
				Thread.sleep(i * 800);
			}
		}

	}
}
