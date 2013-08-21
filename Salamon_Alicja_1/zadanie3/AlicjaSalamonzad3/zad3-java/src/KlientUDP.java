import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class KlientUDP {

	MulticastSocket socket;
	String nick;
	String host;
	int port;
	
	public static void main(String args[]) {
		
		if (args.length != 3) {
			  
			System.out.println("Input parameters: <IP> <port> <nick>");
			System.exit(-1);
		}
		
		final KlientUDP k = new KlientUDP();
		
		k.host = args[0];
		k.port = Integer.parseInt(args[1]);
		k.nick = args[2];
		
		if(k.nick.length()>6)
		{	
			System.out.println("zartujesz? taki dlugi nick?");
			System.exit(-1);
		}
		
		try {
			k.socket = new MulticastSocket(k.port);
	        k.socket.setReuseAddress(true);
	        k.socket.joinGroup(InetAddress.getByName(k.host));
	        k.socket.setLoopbackMode(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override public void run() { k.runWysylanie(k); }
			}.start();

		k.runSluchanie(k);

	}

	public void runSluchanie(KlientUDP k) {

		while (true) {
			try {
				byte data[] = new byte[256];
				DatagramPacket packet = new DatagramPacket(data, 256);
				socket.receive(packet);
				data = packet.getData();
				//DataInputStream dataInputStream = new DataInputStream(
				//	new ByteArrayInputStream(data));

				Wiadomosc w = new Wiadomosc();
				w.doKlasy(data, packet.getOffset(), packet.getLength());
				if(!w.nick.equals(k.nick))
					w.wyswietl(nick);
			}catch (Exception e) {}
		}

	}

	public void runWysylanie(KlientUDP k) {
		while (true) {
			try {
				Scanner scanner = new Scanner(System.in);
				String tekst = scanner.nextLine();
				if(tekst.length()>20)
					System.out.println("za dlugie! lol");
				else{
					Date date = new Date();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss", new Locale("pl") );
					Wiadomosc wiadomosc = new Wiadomosc(simpleDateFormat.format(date),tekst, k.nick);
					
					byte[] bajtowaWiadomosc = wiadomosc.doBajtow();
	
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
					DataOutputStream outputStream = new DataOutputStream(
							byteArrayOutputStream);
					outputStream.write(bajtowaWiadomosc);
					byte[] data = byteArrayOutputStream.toByteArray();
	
					socket.send(new DatagramPacket(data, data.length, InetAddress.getByName(host), port));
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
