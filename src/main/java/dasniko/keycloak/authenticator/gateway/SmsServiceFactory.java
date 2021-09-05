// vim: set shiftwidth=2 tabstop=2 : 
package dasniko.keycloak.authenticator.gateway;

import org.jboss.logging.Logger;

import java.util.Map;
import java.lang.Class;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class SmsServiceFactory {

	private static final Logger LOG = Logger.getLogger(SmsServiceFactory.class);

	public static SmsService get(Map<String, String> config) {

  	String backend = config.get("backend");

		if (Boolean.parseBoolean(config.getOrDefault("simulation", "false"))) {
			return (phoneNumber, message) ->
				LOG.warn(
					String.format("***** SIMULATION MODE BACKEND %s ***** Would send SMS to %s with text: %s", 
						backend,
						phoneNumber,
						message
					)
				);
		} else {
			LOG.info(String.format("Sending SMS with backend %s", backend));
			/* 
			 * I know switch statements are bad for this case but my Java-Fu is not
			 * yet strong enough to wing it. Hence the switch statment.
			*/
			switch (backend) {
				case "AWS":
					return new AwsSmsService(config);
				case "Swisscom": 
					return new SwisscomSmsService(config);
				default:
					LOG.info(String.format("Failed to initiate SMS bakend %s", backend));
					return new DummySmsService(config);
			}
			/*
		  try {
				Class<?> smsService = Class.fromName(backend.concat("SmsService"));
			  // return new smsService(config);
			} finally {
				LOG.info(String.format("Failed to initiate SMS bakend %s", backend));
			}
			*/
		}
	}

}
