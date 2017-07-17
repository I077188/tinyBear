package com.dba.util;

import java.util.List;
import java.util.ArrayList;

import com.dba.Mail.impl.SendingMailImpl;
import com.dba.constants.CONSTAINTS;
import com.dba.data.Customer;

public class test {

	public static void main(String[] args) {
		CONSTAINTS.inital();
		
		List<Customer> customers = new ArrayList<>();
		
		Customer customer = new Customer();
		customer.setCustomerName("Tester");
		customer.setMailbox("flowerencedew@sina.com");
		customers.add(customer);
		
		SendingMailImpl senMail = new SendingMailImpl();
		senMail.sendingMailtoCustomers(customers);
	}
}
