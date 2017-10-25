package net.jju.chat.dev;

import java.util.Scanner;

import org.junit.Test;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationTest {

	@Test
	public void testConversation() {

		Scanner sc = new Scanner(System.in);

		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword("<user id>", "<password>");
		
		MessageResponse response = null;
		Context context = null; //대화의 흐름을 유지하는데 사용
		MessageOptions options = null;
		
		String msg = "";
		StringBuffer watsonSay = null;
		
		while(true) {
			options = new MessageOptions.Builder()
				    					.workspaceId("27119b3b-de6f-4212-8df2-3b3867b5004a")
				    					.input(new InputData.Builder(msg).build())
				    					.context(context)
				    					.build();
			
			response = service.message(options).execute();
			
			System.out.print("Watson Say : " );
			
			watsonSay = new StringBuffer();
			
			for(String text : response.getOutput().getText()) {
				watsonSay.append(text);
				watsonSay.append(" ");
			} //end for
			
			System.out.println(watsonSay);
			
			System.out.print("I : ");
			msg = sc.nextLine();
			
			context = response.getContext();
			
		} //end while
		
	}

} //end class
