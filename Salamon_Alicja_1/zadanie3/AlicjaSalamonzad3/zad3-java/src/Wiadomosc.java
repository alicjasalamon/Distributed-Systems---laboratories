import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class Wiadomosc {
	
	String data;
	String wiadomosc;
	String nick;
	int sumaKontrolna;
	
	public Wiadomosc(String d, String w, String n)
	{
		data=d;
		wiadomosc=w;
		nick = n;
		sumaKontrolna = obliczSumeKontrolna();
	}
	
	public Wiadomosc(){}
	
	byte[] doBajtow(){
		
		byte[] dataBajtowa = this.data.getBytes(Charset.forName("UTF-8"));
		byte[] wiadomoscBajtowa = this.wiadomosc.getBytes(Charset.forName("UTF-8"));
		byte[] nickBajtowy = this.nick.getBytes(Charset.forName("UTF-8"));
		
		ByteBuffer bufor= ByteBuffer.allocate(1 + nickBajtowy.length + 1 + 
				wiadomoscBajtowa.length + 1 + dataBajtowa.length + 4);
		
		bufor.put((byte) nickBajtowy.length);
		bufor.put(nickBajtowy);
		bufor.put((byte) wiadomoscBajtowa.length);
        bufor.put(wiadomoscBajtowa);
		bufor.put((byte) dataBajtowa.length);        
        bufor.put(dataBajtowa);
        bufor.putInt(sumaKontrolna);
        
        return bufor.array();

	}
	
	void doKlasy(byte[] wiadomosc, int offset, int dlugosc){
		
		ByteBuffer bufor = ByteBuffer.wrap(wiadomosc, offset, dlugosc);
        
        int dlugoscNicku = bufor.get();
        byte[] nickBajtowy = new byte[dlugoscNicku];
        bufor.get(nickBajtowy);
        
        int dlugoscWiadomosci = bufor.get();
		byte[] wiadomoscBajtowa = new byte[dlugoscWiadomosci];
		bufor.get(wiadomoscBajtowa);
		
		int dlugoscDaty = bufor.get();
		byte[] dataBajtowa = new byte[dlugoscDaty];
		bufor.get(dataBajtowa);

		int sumaKontrolna = bufor.getInt();

		this.wiadomosc = new String(wiadomoscBajtowa, Charset.forName("UTF-8"));
		this.nick = new String(nickBajtowy, Charset.forName("UTF-8"));
        this.data = new String(dataBajtowa, Charset.forName("UTF-8"));
        this.sumaKontrolna = sumaKontrolna;

	}
	
	int obliczSumeKontrolna(){
		return this.data.hashCode() + this.nick.hashCode() + this.wiadomosc.hashCode();
	}
	
	void wyswietl(String nickNadawcy){
		if(obliczSumeKontrolna() == sumaKontrolna)
			System.out.println(data + " " + nick + ">>\t" +  wiadomosc);
		else
			System.out.println("blad sumy kontrolnej");
	}
	
}
