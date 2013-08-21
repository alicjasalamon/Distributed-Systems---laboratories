package client.messages;

import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import com.google.protobuf.InvalidProtocolBufferException;

import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos.ChatMessage;

public class MessageReceiver extends ReceiverAdapter {

	@Override
	public synchronized void receive(Message msg) {
		
			try {
			
				ChatMessage message = ChatMessage.parseFrom(msg.getRawBuffer());
				System.out.println(">>" + msg.getSrc() + " >> " + message.getMessage());
			
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}

	}

}
