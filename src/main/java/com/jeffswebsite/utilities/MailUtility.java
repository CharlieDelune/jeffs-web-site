package com.jeffswebsite.utilities;

import static com.jeffswebsite.utilities.Functions.verifyContactSubmission;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import com.jeffswebsite.models.ContactSubmission;

public class MailUtility {

	private static final String HOST = System.getenv().get("jeffswebsite.mail.host");
	private static final int PORT = Integer.parseInt(System.getenv().get("jeffswebsite.mail.port"));
	private static final boolean SSL_ENABLED = true;
	private static final boolean STARTTLS_ENABLED = true;
	private static final String USERNAME = System.getenv().get("jeffswebsite.mail.username");
	private static final String PASSWORD = System.getenv().get("jeffswebsite.mail.password");
	private static final String TO_ADDRESS = System.getenv().get("jeffswebsite.mail.toAddress");
	private static final String CC_ADDRESS = System.getenv().get("jeffswebsite.mail.ccAddress");

	public static boolean SendContactSubmissionEmail(final ContactSubmission contactSubmission) {
		try {
			if (!verifyContactSubmission(contactSubmission)) {
				throw new IllegalArgumentException();
			}
			final Email email = new SimpleEmail();
			email.setHostName(HOST);
			email.setSmtpPort(PORT);
			email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
			email.setAuthentication(USERNAME, PASSWORD);
			email.setSSLOnConnect(SSL_ENABLED);
			email.setStartTLSEnabled(STARTTLS_ENABLED);
			email.setFrom(contactSubmission.getEmail(), contactSubmission.getName());
			email.setSubject(contactSubmission.getSubject());
			email.setMsg(contactSubmission.getContent());
			email.addTo(TO_ADDRESS);
			email.addCc(CC_ADDRESS);
			email.send();
		} catch (final Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

}
