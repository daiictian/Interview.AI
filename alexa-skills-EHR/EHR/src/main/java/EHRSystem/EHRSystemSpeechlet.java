
package EHRSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.*;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import java.util.Map;
import java.util.HashMap;


public class EHRSystemSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(EHRSystemSpeechlet.class);
	HashMap<String, Integer> data = new HashMap<String, Integer>();
	boolean fetchingData = false;
	String fetchingTid;
	
	
	
    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("NameIntent".equals(intentName)) {
            return getNameIntentResponse(intent);
            }
            else if("YearsIntent".equals(intentName)){
            return getYearsIntentResponse(intent);
        } else if ("ProjectIntent".equals(intentName)){
            return getProjectIntentResponse(intent); 
            }
            else if ("MonthlyIntent".equals(intentName)){
            return getMonthlyIntentResponse(intent);
            }
            else if ("SalaryIntent".equals(intentName)){
            return getSalaryIntentResponse(intent);
            }
            else if("PromotionIntent".equals(intentName)){
            return getPromotionIntentResponse(intent);
            }
            else if("EvaluationIntent".equals(intentName)){
            return getEvaluationIntentResponse(intent);
            }
            else if("LeftIntent".equals(intentName)){
            return getLeftIntentResponse(intent);
            }
            else if("WorkAccIntent".equals(intentName)){
            return getWorkAccIntentResponse(intent);
            }
            else if("AMAZON.YesIntent".equals(intentName)){
            return getYesResponse();
            }
            else if("AMAZON.StopIntent".equals(intentName)){
            return getStopResponse();
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        
    }

   
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to your virtual interview. Tell me your name";

	//data.put(x,y);
	
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    
    private SpeechletResponse getNameIntentResponse(Intent intent) {
    	Map<String, Slot> slots = intent.getSlots();
    
    	String speechText;
    
    	Slot tid = slots.get("NAME");
    
    	if(tid!=null){
    	fetchingTid = tid.getValue();
        speechText = "Okay "+fetchingTid+". How many years have you worked with your last company";
       // fetchingData = false;
	}
	else
	{
	speechText = "I didn't understood what you said. Please say again";
	}
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	// Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    
    private SpeechletResponse getYearsIntentResponse(Intent intent){
    Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("YEARS");
    
    if(tid!=null){
    fetchingTid = tid.getValue();
    data.put("P1",Integer.parseInt(fetchingTid));
    speechText = "Okay. How many projects your were involved in";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
    
    // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	// Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    
    private SpeechletResponse getProjectIntentResponse(Intent intent){
    Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("PROJECTCOUNT");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
        data.put("P2",Integer.parseInt(fetchingTid));
    speechText = "Okay. How many hours you work weekly";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    
    }
    
     private SpeechletResponse getMonthlyIntentResponse(Intent intent) {
        Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("HOURCOUNT");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
    Integer i = Integer.parseInt(fetchingTid);
    i = i*4;
        data.put("P3",i);
    speechText = "Okay. How would you classify your salary as low, medium or high";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
     }

	
	private SpeechletResponse getSalaryIntentResponse(Intent intent){
	Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("SALARY");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
    Integer i = 0;
    if(fetchingTid.equals("low"))
    i=1;
    else if(fetchingTid.equals("medium"))
    i=2;
    else
    i=3;
        data.put("P4",i);
    speechText = "Okay. Did you get any promotion in last five years";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
	
	private SpeechletResponse getYesResponse() {
        String speechText = "Okay, go ahead and tell me what do you want to do";
	
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	// Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
     
     
     private SpeechletResponse getPromotionIntentResponse(Intent intent) {
    
   
   	Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("PROMOTION");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
    Integer i=0;
    if(fetchingTid.equals("yes"))
    i=1;
        data.put("P5",i);
    speechText = "Okay. How will you classify your last company evaluation on a scale of one to ten";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
  
      private SpeechletResponse getEvaluationIntentResponse(Intent intent) {
   
   	Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("EVALUATION");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
        data.put("P6",Integer.parseInt(fetchingTid)/10);
    speechText = "Okay. Are you still working or not";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
      private SpeechletResponse getLeftIntentResponse(Intent intent) {
   
   	Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("LEFT");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
    Integer i=0;
    if(fetchingTid.equals("yes"))
    i=1;
        data.put("P7",i);
    speechText = "Okay. Have you ever had any accident or mishappening at your workplace";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }   
    private SpeechletResponse getWorkAccIntentResponse(Intent intent) {
   
   	Map<String, Slot> slots = intent.getSlots();
    
    String speechText;
    
    Slot tid = slots.get("WORKACC");
    
    if(tid!=null ){
    fetchingTid = tid.getValue();
    Integer i=0;
    if(fetchingTid.equals("yes"))
    i=1;
        data.put("P8",i);
        
        double y = ((data.get("P1"))*0.0021)+((data.get("P2"))*0.0022)+((data.get("P3"))*0.0023)+((data.get("P4"))*0.0024)+((data.get("P5"))*0.0025)+((data.get("P6"))*0.0026)+((data.get("P7"))*0.0027)+((data.get("P8"))*0.0028);
    if(y>0.5)
    speechText = "Okay. Thank you for your time , you will hear from hiring managers soon , Good Luck!";
    else
    speechText = "Okay. Thank you for your time and interest in our company. We don't have a perfect match for you at this time. Good Luck!";
    }
    else{
    speechText = "I didn't understood what you said. Please say again";
    }
        
    SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        
        return SpeechletResponse.newTellResponse(speech, card);
    }   

    private SpeechletResponse getStopResponse() {
        String speechText = "Okay GoodBye";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

	
        
        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    private SpeechletResponse getHelpResponse() {
        String speechText = "You can say hello to me!";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}
