import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public final static int ILE = 1024;
	
	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Input parameters: <IP> <port>");
			System.exit(-1);
		}
		Socket socket=null;
		InputStream in=null;
		FileOutputStream out =null;
		try {
		
			socket = new Socket(args[0], Integer.parseInt(args[1]));
			in = new DataInputStream(socket.getInputStream()); 
			out = new FileOutputStream("plik.txt");
			
			int ile =1;
			while(ile!=0)
			{
				byte [] bytes = new byte[ILE];
				int bytesRead = in.read(bytes);				
				if(bytesRead == -1)
					break;
				
				out.write(bytes, 0, bytesRead);
			}			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(socket!=null)
					socket.close();
				if (out != null)
					out.close();
				if(in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		
		}
		
	}

}