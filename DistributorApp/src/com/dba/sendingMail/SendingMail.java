package com.dba.sendingMail;

import java.util.List;
import com.dba.data.Customer;

public interface SendingMail {
	
	public void sendingMailtoCustomers(List<Customer> customers);
}
