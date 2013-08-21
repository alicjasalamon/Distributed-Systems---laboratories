package client.messages;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatMessage;
import client.Client;
import client.state.StateManager;
import client.state.StateMessageReceiver;

public class ChannelsManager {

	Map<String, JChannel> joinedChannels = new ConcurrentHashMap<>();
	StateManager stateManager = new StateManager();
	MessageReceiver messageReceiver = new MessageReceiver();


	public void create(String channelName) {

		JChannel channel = new JChannel(false);
		ProtocolStack stack = new ProtocolStack();
		channel.setProtocolStack(stack);
	
		try {
			stack.addProtocol(new UDP().setValue("mcast_group_addr",InetAddress.getByName(channelName)))
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
		
			stack.init();
			channel.connect(channelName);	
			channel.setReceiver(messageReceiver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		joinedChannels.put(channelName, channel);
		stateManager.newUserInChannel(channelName);
		System.out.println("u created channel " + channelName);

	}
	
	public void join(String channelName) {
		
		if (joinedChannels.containsKey(channelName)) {
			System.out.println("you already are member of this group");
		} else if (!StateMessageReceiver.state.containsKey(channelName)) {
			System.out.println("there is no such group");
		}else{
		
			ProtocolStack stack = new ProtocolStack();
			JChannel channel = new JChannel(false);
			channel.setProtocolStack(stack);
			try {
				
				stack.addProtocol(new UDP().setValue("mcast_group_addr",InetAddress.getByName(channelName)))
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
			
				stack.init();
				channel.connect(channelName);
				channel.setReceiver(messageReceiver);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			joinedChannels.put(channelName, channel );
			stateManager.newUserInChannel(channelName);
			System.out.println("u joined channel " + channelName);
		}
	}

	public void leave(String channelName) {

		if (!joinedChannels.containsKey(channelName)) {
			System.out.println("you're not member of this group");
		}else if (!StateMessageReceiver.state.containsKey(channelName)) {
			System.out.println("there is no such group");
		}else{
			
			JChannel channel = joinedChannels.remove(channelName);
			channel.disconnect();
	
			stateManager.userLeft(channelName);
			
			System.out.println("u left channel " + channelName);
		}
	}
	
	public void leaveAll() {

		Iterator<String> i = joinedChannels.keySet().iterator();
		while (i.hasNext()) {
			String channelName = i.next();
			JChannel channel = joinedChannels.get(channelName);
			channel.disconnect();
			i.remove();
			stateManager.userLeft(channelName);
		}
		
		/*for(String channelName : joinedChannels.keySet()){
			
			JChannel channel = joinedChannels.remove(channelName);
			channel.close();
			stateManager.userLeft(channelName);
		}*/
		
		stateManager.channelManagement.close();

	}



	public void send(String channelName, String message) {
		
		for (String s : joinedChannels.keySet()) {
			System.out.println(s);
		}
		JChannel channel = joinedChannels.get(channelName);
		ChatMessage chatMessage = ChatMessage.newBuilder().setMessage(message).build();
		byte[] output = chatMessage.toByteArray();
		Message msg = new Message(null, null, output);

		try {
			channel.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
