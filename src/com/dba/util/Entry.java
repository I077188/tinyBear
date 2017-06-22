package com.dba.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dba.constants.CONSTAINTS;
import com.dba.data.Customer;
import com.dba.search.impl.SearchImpl;

public class Entry {
	public Entry() {
		LOG.info("Loading program...");
	}

	public static void main(String[] args) {
		
		CONSTAINTS.inital();
		
		Result.info("Progress started...");
		
		LOG.info("program started...");
		/**
		 * search part result should be the customer set for each customer it
		 * will include the customer name and mail-box
		 */

		SearchImpl sImpl = new SearchImpl();

//		List<String> customerURLs = sImpl.getCustomersURLs();
		// for testing
		 List<String> customerURLs = new ArrayList<String>();
		 customerURLs.add("http://www.designeryarns.uk.com/");
		 customerURLs.add("http://www.marrineryarns.com/");

		List<Customer> customers = sImpl.getCustomers(customerURLs);
		
		Set<String> mailBoxes = new HashSet<String>();

		for (int i = 0; i < customers.size(); i++) {
			String customerName = customers.get(i).getCustomerName();
			String customerMail = customers.get(i).getMailbox(); 
			
			// avoid duplicate
			mailBoxes.add("Customer:" + customerName + "->" + "MailBox:" + customerMail);
		}
		
		for(String mailBox : mailBoxes){
			LOG.debug(mailBox);
		}

		/**
		 * Loop customers list to sending a mail
		 */
		
		
		Close.closeApp();
	}

}
