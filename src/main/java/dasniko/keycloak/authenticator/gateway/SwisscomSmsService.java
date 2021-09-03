// vim: set shiftwidth=2 tabstop=2 :
package dasniko.keycloak.authenticator.gateway;

import be.rufer.swisscom.sms.api.client.SwisscomSmsSender;
import be.rufer.swisscom.sms.api.domain.CommunicationWrapper;

import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class SwisscomSmsService implements SmsService {

	// private static final SnsClient sns = SnsClient.create();

	private static final Logger LOG = Logger.getLogger(SmsServiceFactory.class);
	private static final String messageFormat = "***** SWISSCOM SIMULATION ***** Would send SMS to %s with text: %s";

	private final String senderId;

	SwisscomSmsService(Map<String, String> config) {
		senderId = config.get("senderId");
	}

	@Override
	public void send(String phoneNumber, String message) {
		LOG.warn(String.format(messageFormat, phoneNumber, message));
    SwisscomSmsSender smsSender = new SwisscomSmsSender("Foobar-Key", "+41792345678");
    CommunicationWrapper response = smsSender.sendSms(message, phoneNumber);
	}
}
