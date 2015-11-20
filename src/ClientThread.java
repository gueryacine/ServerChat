import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread implements Runnable {

	private static final String NULL = null;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	public String login;
	public boolean authentifier = false;
	private Socketlistener parent;

	/**
	 * 
	 * @param socket
	 *            Socket qui va vers le client
	 * @param login
	 *            nom du client
	 * @param inputStream
	 *            flux d'entrer recuperer
	 * @param out 
	 * 			flux de sortie recuperer
	 * @param socketlistener 
	 */
	
	public ClientThread(Socket socket, String login, BufferedReader inputStream, PrintWriter out, Socketlistener socketlistener) {
		input = inputStream;
		output = out;
		this.login = login;
		this.socket = socket;
		this.parent = socketlistener;
	}

	public void run() {
		try {
			while (true) {
				// lire les message envoyer par l'utilisateur
				String message = input.readLine();
				message = login + " : " + message;
				parent.signalMessage(message, this);
				LogWriter.getInstance(message);
			}
		} catch (IOException e) {
			String message = login + " c'est déconnecter";
			parent.signalMessage(message, this);
			LogWriter.getInstance(message);
		}
	}
	
	/**
	 * 
	 * @param message
	 * 		envoie d'un message a cette utilisateur
	 */
	public void envoie(String message ) {
		// envoie du message dans sont flux de sortie(serveur) entré(client)
		output.println(message);
		output.flush();
	}


}
