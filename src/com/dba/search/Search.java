package com.dba.search;

import java.util.List;

import com.dba.data.Customer;

/**
 * Search interface
 * @author LEO
 * Step1 found all the related customer URLs with search engine
 * Step2 for each URL of customer, enter the URL and found all the mail list
 */
public interface Search {
	/**
	 * get customer URL - web site
	 * @return
	 */
	public List<String> getCustomersURLs();

	/**
	 * get customer object based on the URL
	 * @param urls
	 * @return
	 */
	public List<Customer> getCustomers(List<String> urls);
}
