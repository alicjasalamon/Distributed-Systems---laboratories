package client;

import java.util.Scanner;

import client.messages.ChannelsManager;
import client.state.StateMessageReceiver;

public class Client {

	public static String nick;

	public static void main(String args[]) throws InterruptedException {

		
		ChannelsManager channelsManager = new ChannelsManager();
		
		System.out.println("enter nickname: ");
		Scanner scanner = new Scanner(System.in);
		nick = scanner.nextLine();

		String operation;
		do {

			printOptions();
			operation = scanner.nextLine();

			if (operation.equals("j")) {

				System.out.println("type the channel name");
				String channelName = scanner.nextLine();
				channelsManager.join(channelName);

			} else if (operation.equals("l")) {

				System.out.println("type the channel name");
				String channelName = scanner.nextLine();
				channelsManager.leave(channelName);

			} else if (operation.equals("c")) {
				
				System.out.println("type the channel name");
				String channelName = scanner.nextLine();
				channelsManager.create(channelName);

			} else if (operation.equals("s")) {

				System.out.println("type the channel name");
				String channelName = scanner.nextLine();
				System.out.println("type a message");
				String message = scanner.nextLine();
				channelsManager.send(channelName, message);

			} else if (operation.equals("h")) {

				StateMessageReceiver.printState();

			}



		} while (!operation.equals("x"));

		channelsManager.leaveAll();
		scanner.close();
	}

	static void printOptions() {
		System.out.println("------------------");
		System.out.println("h - list channels and users");
		System.out.println("c - create a channel");
		System.out.println("j - join a channel");
		System.out.println("l - leave a channel");
		System.out.println("s - send message");
		System.out.println("x - exit");
		System.out.println("------------------");
	}

}
