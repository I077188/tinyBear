package com.dba.Mail.impl;

import java.util.List;

import com.dba.Mail.SendingMail;
import com.dba.data.Customer;

public class SendingMailImpl implements SendingMail {

	@Override
	public void sendingMailtoCustomers(List<Customer> customers) {
		// TODO Auto-generated method stub
		
	}

	/*public void sendingMailtoCustomers(List<Customer> customers) {
		for (Customer customer : customers) {

			// connect email
			MailSenderInfo mailInfo = new MailSenderInfo();

			// sender information
			mailInfo.setMailServerHost(CONSTAINTS.senderUserSmtpHost);
			mailInfo.setMailServerPort(CONSTAINTS.senderUserSmtpPort);
			mailInfo.setValidate(true);
			mailInfo.setUserName(CONSTAINTS.senderUserName);
			mailInfo.setPassword(CONSTAINTS.senderUserPass);
			mailInfo.setFromAddress(CONSTAINTS.senderUserName);
			mailInfo.setToAddress(customer.getMailbox());

			// mail information
			mailInfo.setSubject(CONSTAINTS.mailTitle);
			mailInfo.setContent("test...");

			// send mails to customer
			this.sendTextMail(mailInfo);
		}
	}

	private void sendTextMail(MailSenderInfo mailInfo) {

		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}

		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {

			Message mailMessage = new MimeMessage(sendMailSession);

			Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			
			Transport transport = sendMailSession.getTransport("smtp");
			transport.connect(mailInfo.getMailServerHost(), Integer.parseInt(mailInfo.getMailServerPort()), mailInfo.getUserName(), mailInfo.getPassword());
			
			List<Address> addressesList = new ArrayList<Address>();
			addressesList.add(to);
			Address[] addresses = (Address[]) addressesList.toArray();
			
			Transport.send(mailMessage, addresses);
			System.out.println("Sending correct...");
			
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}*/
}
