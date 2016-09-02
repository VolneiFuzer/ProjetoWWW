import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHTTP {

	private static final int PORTA_HTTP_PADRAO = 80;
	private static final String HTTP_PADRAO = "HTTP/1.1";
	private static PrintWriter saida;

	public static void main(String[] args) {
		try {
			String host = "www.emanuelgandrade.com";
			String path = "/";
			Socket socket = new Socket(host, PORTA_HTTP_PADRAO);
			saida = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//enviando requisicao
			sendData("GET " + path + " " + HTTP_PADRAO);
			sendData("Host: " + host);
			sendData("Connection: Close");
			sendData("");
			
			//recebendo a resposta
			boolean loop=true;
			StringBuffer resposta=new StringBuffer();
			do {
				if (entrada.ready()) {
					int i = 0;
					while ((i = entrada.read()) != -1) {
						resposta.append((char) i);
					}
					loop=false;
				}
			} while (loop);
			System.out.println(resposta.toString());
			socket.close();
			System.out.println("fim");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendData(String data) {
		saida.println(data);
		System.out.println(data);
	}
	
}
