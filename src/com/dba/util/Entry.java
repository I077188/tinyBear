package com.dba.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dba.constants.CONSTAINTS;
import com.dba.data.Customer;
import com.dba.search.impl.SearchImpl;

public class Entry {
	public Entry() {
		ExecuteLOG.info("Loading program...");
	}

	public static void main(String[] args) {

		CONSTAINTS.inital();

		Result.info("Progress started...");

		/**
		 * search part result should be the customer set for each customer it
		 * will include the customer name and mail-box
		 */
		SearchImpl sImpl = new SearchImpl();
		List<String> customerURLs = sImpl.getCustomersURLs();
		Set<String> mailBoxes = new HashSet<String>();
		List<Customer> customers = sImpl.getCustomers(customerURLs);

		/*
		 * for (int i = 0; i < customers.size(); i++) { String customerName =
		 * customers.get(i).getCustomerName(); String customerMail =
		 * customers.get(i).getMailbox(); }
		 */

		/*
		 * 
		 * // // for testing
		 * 
		 * 
		 * 
		 * // avoid duplicate mailBoxes.add("Customer:" + customerName + "->" +
		 * "MailBox:" + customerMail); }
		 * 
		 * for (String mailBox : mailBoxes) {
		 * Result.info("Following is the mail collect from the search!");
		 * Result.info(mailBox); }
		 */

		/**
		 * Loop customers list to sending a mail
		 */

		Close.closeApp();
	}

}
