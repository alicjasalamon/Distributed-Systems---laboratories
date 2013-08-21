package client.state;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.jgroups.Address;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatAction;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatState;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatAction.ActionType;

import com.google.protobuf.InvalidProtocolBufferException;

public class StateMessageReceiver extends ReceiverAdapter {

	public static Map<String, Set<String>> state = new ConcurrentHashMap<String, Set<String>>();
	View oldView = null;

	public static void printState() {

		System.out.println();
		for (String groupName : state.keySet()) {

			System.out.println("GROUP: " + groupName + " ");

			for (String user : state.get(groupName)) {
				System.out.println("\tUSER: " + user);
			}
		}
		System.out.println();
	}

	@Override
	public void receive(Message msg) {

		byte[] byteArrayMsg = msg.getRawBuffer();
		ChatAction chatAction = null;
		
		try {
			chatAction = ChatAction.parseFrom(byteArrayMsg);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		String channelName = chatAction.getChannel();
		String user = chatAction.getNickname();

		//JOIN
		if (chatAction.getAction() == ActionType.JOIN) {
			if (state.containsKey(channelName)) {
				state.get(channelName).add(user);
			} else {
				Set<String> users = new ConcurrentSkipListSet<>();
				users.add(user);
				state.put(channelName, users);
			}
			
		//LEAVE
		} else if (chatAction.getAction() == ActionType.LEAVE){
			if (state.containsKey(channelName)) {
				state.get(channelName).remove(user);
				
				if (state.get(channelName).isEmpty()) {
					state.remove(channelName);
				}
			}
		}
	}
	
	@Override
	public void getState(OutputStream output) throws Exception {
		
		List<ChatAction> actionList = new LinkedList<>();
		
		for(String channelName : state.keySet()){
			for(String user : state.get(channelName)){
				ChatAction action = ChatAction.newBuilder().
						setAction(ActionType.JOIN)
						.setChannel(channelName)
						.setNickname(user)
						.build();
				actionList.add(action);
			}
		}
		
		ChatState chatState = ChatState.newBuilder().addAllState(actionList).build();
		byte[] outputByteArray = chatState.toByteArray();
		output.write(outputByteArray);
	}

	@Override
	public void setState(InputStream input) throws Exception {
		
		try{
			ChatState chatState = ChatState.parseFrom(input);
			
			state.clear();
			for(ChatAction chatAction : chatState.getStateList()){
				
				String channelName = chatAction.getChannel();
				String user = chatAction.getNickname();
				
				//JOIN
				if (chatAction.getAction() == ActionType.JOIN) {
					if (state.containsKey(channelName)) {
						state.get(channelName).add(user);
					} else {
						Set<String> users = new ConcurrentSkipListSet<>();
						users.add(user);
						state.put(channelName, users);
					}
					
				//LEAVE
				} else if (chatAction.getAction() == ActionType.LEAVE){
					if (state.containsKey(channelName)) {
						state.get(channelName).remove(user);
						
						if (state.get(channelName).isEmpty()) {
							state.remove(channelName);
						}
					}
				}
			}
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace(System.err);
		}
	}
	
	@Override
	public synchronized void viewAccepted(View newView) {
		
		if(oldView == null){
			oldView = newView;
		}
		
		else{
			List<Address> memberList = oldView.getMembers();
			
			for(Address member : memberList){
	
				if(! newView.containsMember(member)){
					
					for(String channelName : state.keySet()){
						state.get(channelName).remove(member.toString());
					
						if(state.get(channelName).isEmpty()){
							state.remove(channelName);
						}
					}
				}
			}
		oldView = newView;
		}
	}
	

}
