import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {

	/*korzystam z szablonu podanego na zajeciach */
	
	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Input parameters: <IP> <port>");
			System.exit(-1);
		}
		
		try {
			
			//podaje do konstruktora socketu IP i port z parametrow programu
			Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
			
			//strumienie wejscia/wyjscia
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("wysylane beda kolejno liczby 1,2,4 bajtowe");
			
			//---------------------------------------------------------------
			//-----------------------JEDEN BAJT------------------------------
			//---------------------------------------------------------------
			Random rand = new Random();
			
			int liczbaWyslana = rand.nextInt() % 127;
			out.writeByte(liczbaWyslana);
			int liczbaOtrzymana = in.readByte();
			
			System.out.println("JEDEN BAJT");
			System.out.println("liczba wyslana od klienta: " + liczbaWyslana);
			System.out.println("liczba odebrana u klienta: " + liczbaOtrzymana);
			
			//---------------------------------------------------------------
			//-----------------------DWA BAJTY ------------------------------
			//---------------------------------------------------------------
			
			liczbaWyslana = rand.nextInt() % 32768;
			out.writeShort(liczbaWyslana);
			liczbaOtrzymana = in.readShort();
			
			System.out.println("DWA BAJTY");
			System.out.println("liczba wyslana od klienta: "  + liczbaWyslana);
			System.out.println("liczba odebrana u klienta: " + liczbaOtrzymana);
			
			//---------------------------------------------------------------
			//-----------------------CZTERY BAJTY ---------------------------
			//---------------------------------------------------------------
			
			liczbaWyslana = rand.nextInt();
			out.writeInt(liczbaWyslana);
			liczbaOtrzymana = in.readInt();
			
			System.out.println();
			System.out.println("liczba wyslana od klienta: " + liczbaWyslana);
			System.out.println("liczba odebrana u klienta: " + liczbaOtrzymana);
			socket.close();
			
		} catch (NumberFormatException e) {												//jeszcze jakies wyjatki?
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}