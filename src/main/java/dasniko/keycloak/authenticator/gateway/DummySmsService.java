// vim: set shiftwidth=2 tabstop=2 :
package dasniko.keycloak.authenticator.gateway;

import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Urs Roesch, https://bun.ch
 */
public class DummySmsService implements SmsService {

	private static final Logger LOG = Logger.getLogger(DummySmsService.class);

	private final String senderId;

	DummySmsService(Map<String, String> config) {
		senderId = config.get("senderId");
	}

	@Override
	public void send(String phoneNumber, String message) {
		LOG.info(
			String.format("***** Dummy SMS Message ***** from: %s, to: %s, text: %s",
				senderId,
				phoneNumber,
				message
			)
		);
	}
}
