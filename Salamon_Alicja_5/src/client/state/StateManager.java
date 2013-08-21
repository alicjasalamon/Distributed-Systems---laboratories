package client.state;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.protocols.BARRIER;
import org.jgroups.protocols.FD_ALL;
import org.jgroups.protocols.FD_SOCK;
import org.jgroups.protocols.FRAG2;
import org.jgroups.protocols.MERGE2;
import org.jgroups.protocols.MFC;
import org.jgroups.protocols.PING;
import org.jgroups.protocols.UDP;
import org.jgroups.protocols.UFC;
import org.jgroups.protocols.UNICAST2;
import org.jgroups.protocols.VERIFY_SUSPECT;
import org.jgroups.protocols.pbcast.FLUSH;
import org.jgroups.protocols.pbcast.GMS;
import org.jgroups.protocols.pbcast.NAKACK;
import org.jgroups.protocols.pbcast.STABLE;
import org.jgroups.protocols.pbcast.STATE_TRANSFER;
import org.jgroups.stack.ProtocolStack;

import client.Client;

import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatAction;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatAction.ActionType;

public class StateManager {
	
	public JChannel channelManagement = new JChannel(false);
	
	
	public StateManager() {

		ProtocolStack stack = new ProtocolStack();
		channelManagement.setProtocolStack(stack);
		channelManagement.setReceiver(new StateMessageReceiver());
		
		stack.addProtocol(new UDP())
		.addProtocol(new PING())
		.addProtocol(new MERGE2())
		.addProtocol(new FD_SOCK())
		.addProtocol(new FD_ALL().setValue("timeout", 12000).setValue("interval", 3000))
		.addProtocol(new VERIFY_SUSPECT())
		.addProtocol(new BARRIER())
		.addProtocol(new NAKACK())
		.addProtocol(new UNICAST2())
		.addProtocol(new STABLE())
		.addProtocol(new GMS())
		.addProtocol(new UFC())
		.addProtocol(new MFC())
		.addProtocol(new FRAG2())
		.addProtocol(new STATE_TRANSFER())
		.addProtocol(new FLUSH());
	
		
		try {
			stack.init();
			channelManagement.connect("ChatManagement768264");
			channelManagement.getState(null, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	void newChannel(String channelName)
	{
		try {
			ChatAction chatAction = ChatAction.newBuilder()
					.setAction(ActionType.JOIN)
					.setChannel(channelName)
					.setNickname(Client.nick)
					.build();
			
			Message message = new Message(null, null, chatAction.toByteArray());
			channelManagement.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newUserInChannel(String channelName){
		try {
			ChatAction chatAction = ChatAction.newBuilder()
					.setAction(ActionType.JOIN)
					.setChannel(channelName)
					.setNickname(Client.nick)
					.build();
			
			Message message = new Message(null, null, chatAction.toByteArray());
			channelManagement.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void userLeft(String channelName) {
		try {
		ChatAction chatAction = ChatAction.newBuilder()
				.setAction(ActionType.LEAVE)
				.setChannel(channelName)
				.setNickname(Client.nick)
				.build();

		Message message = new Message(null, null, chatAction.toByteArray());
		channelManagement.send(message);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
