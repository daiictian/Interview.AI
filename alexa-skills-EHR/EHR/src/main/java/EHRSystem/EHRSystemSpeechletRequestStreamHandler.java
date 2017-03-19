
package EHRSystem;

import java.util.HashSet;
import java.util.Set;
import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;


public final class EHRSystemSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
       
     System.setProperty(Sdk.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true"); System.setProperty(Sdk.SUPPORTED_APPLICATION_IDS_SYSTEM_PROPERTY, ""); System.setProperty(Sdk.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, "");
        supportedApplicationIds.add("amzn1.ask.skill.9af4481c-bdad-483c-b147-a6999c08f6b6");
    }

    public EHRSystemSpeechletRequestStreamHandler() {
        super(new EHRSystemSpeechlet(), supportedApplicationIds);
    }
}
