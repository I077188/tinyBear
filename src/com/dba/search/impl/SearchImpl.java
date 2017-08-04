package com.dba.search.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dba.constants.CONSTAINTS;
import com.dba.data.Customer;
import com.dba.search.Search;
import com.dba.util.ExecuteLOG;
import com.dba.util.Result;

/**
 * Search function
 * 
 * @author LEO Step1 found all the related customer URLs with search engine
 *         Step2 for each URL of customer, enter the URL and found all the mail
 *         list
 */
public class SearchImpl implements Search {

	private String searchContent;
	private String[] countries;
	private String searchEngine;
	private int page;
	private WebDriver driver;

	public SearchImpl() {
		this.searchContent = CONSTAINTS.searchContent;
		this.countries = CONSTAINTS.countries;
		this.searchEngine = CONSTAINTS.searchEngine;
		this.page = CONSTAINTS.page;
		this.driver = CONSTAINTS.driver;
	}

	@Override
	public List<String> getCustomersURLs() {
		ExecuteLOG.info("Get URLs ...");

		List<String> urlList = new ArrayList<String>();

		for (String country : countries) {
			try {
				// searching
				driver.get(searchEngine);
				Thread.sleep(3000);

				WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
				element.sendKeys(searchContent + " " + country);
				Thread.sleep(1000);

				driver.findElement(By.xpath(".//button[@name='btnG']")).click();
				Thread.sleep(3000);

				// scan searching results per page
				String currentUrl_org = driver.getCurrentUrl();

				for (int i = 0; i < page; i++) {
					String currentUrl = currentUrl_org;

					if (i > 0) {
						currentUrl = currentUrl_org + "&start=" + (i * 10);
						// LOG.info("Current URL: " + currentUrl);
					} else {
						// LOG.info("Current URL: " + currentUrl);
					}

					driver.get(currentUrl);
					Thread.sleep(3000);
					// get LINKs
					List<WebElement> list = driver
							.findElements(By.xpath("//*[@id='vs0p1c0']|//*[@id='rso']//*/h3[@class='r']/a"));

					ExecuteLOG.info("Page " + (i + 1) + ":\t has found " + list.size() + " records");

					for (WebElement welement : list) {
						String site = welement.getAttribute("href");
						// LOG.debug(welement.getText() + "-->" + site);

						urlList.add(site);
					}
					Thread.sleep(3000);

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				ExecuteLOG.error(e.getClass().toString(), e.getMessage());
			}

		}

		ExecuteLOG.info("Number of URLs is " + urlList.size());

		return urlList;

	}

	@Override
	public List<Customer> getCustomers(List<String> urls) {
		ExecuteLOG.info("Get customers ... ");
		List<Customer> customers = new ArrayList<Customer>();

		for (int i = 0; i < urls.size(); i++) {
			ExecuteLOG.info("------------------" + i + "--------------------");
			String url = urls.get(i);

			ExecuteLOG.info(url);
			driver.get(url);
			// driver.get("http://www.marrineryarns.com/");
			// contact -> click
			try {
				// check in the home page whether there is some Email
				// information
				List<WebElement> welist = driver.findElements(By.xpath(
						"//*[contains(text(),'Email')]/*|//*[contains(text(),'email')]/*|//*[contains(text(),'E-mail')]/*|//*[contains(text(),'E-Mail')]/*|//*[contains(text(),'e-mail')]/*|"
								// Static web page
								+ "//*[contains(.,'Email')]/a|//*[contains(.,'email')]/a|//*[contains(.,'E-mail')]/a|//*[contains(.,'E-Mail')]/a|//*[contains(.,'e-mail')]/a"));

				// if not find whether have contact
				if ((welist == null) || (welist.size() == 0)) {
					List<WebElement> temptList = new ArrayList<WebElement>();

					List<WebElement> welistContacts = driver.findElements(By.xpath(
							"//*[contains(text(),'contact')]|//*[contains(text(),'Contact')]|//*[contains(text(),'About')]"));

					if (welistContacts.size() > 0) {
						for (int j = 0; j < welistContacts.size(); j++) {
							try {
								welistContacts.get(j).click();
								Thread.sleep(3000);

								// in the content page check the Email
								// information
								List<WebElement> welistContact = driver.findElements(By.xpath(
										"//*[contains(text(),'Email')]/*|//*[contains(text(),'email')]/*|//*[contains(text(),'E-mail')]/*|//*[contains(text(),'E-Mail')]/*|//*[contains(text(),'e-mail')]/*"));
								temptList.addAll(welistContact);
							} catch (Exception e) {
								continue;
							}
						}
						welist = temptList;
					}
				}

				if (welist.size() == 0)
					Result.info(urls.get(i));

				for (int j = 0; j < welist.size(); j++) {
					String mailbox = welist.get(j).getAttribute("href");
					ExecuteLOG.info(mailbox);
					Customer c = new Customer();
					c.setCustomerName("j" + j);
					c.setMailbox(mailbox);
					customers.add(c);
				}

				Thread.sleep(3000);

			} catch (InterruptedException e) {
				ExecuteLOG.warn("Thread sleeping error.");
				continue;
			}

		}
		return customers;
	}

}
