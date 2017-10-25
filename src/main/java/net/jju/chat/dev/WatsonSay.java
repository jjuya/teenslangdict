package net.jju.chat.dev;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@RestController
public class WatsonSay {

	private static Logger logger = LoggerFactory.getLogger(WatsonSay.class);

	@RequestMapping(value="watsonsay")
	public MessageResponse  watsonsay(String isay, HttpSession session) {
		logger.info("user input : " + isay);

		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword("<user id>", "<password>");

		MessageResponse response = null;
		Context context = null; //대화의 흐름을 유지하는데 사용
		MessageOptions options = null;

		String msg = isay;
		StringBuffer watsonSay = null;

		options = new MessageOptions.Builder()
									.workspaceId("27119b3b-de6f-4212-8df2-3b3867b5004a")
									.input(new InputData.Builder(msg).build())
									.context(context)
									.build();

		response = service.message(options).execute();

		watsonSay = new StringBuffer();

		for(String text : response.getOutput().getText()) {
			watsonSay.append(text);
			watsonSay.append(" ");
		} //end for

		logger.info("Watson Say : " + watsonSay);
		

		context = response.getContext();
		session.setAttribute("context", context);
		
		return response;
	}

} //end class
