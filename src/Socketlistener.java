import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Socketlistener extends Thread implements ClientThreadObserver {
	ArrayList<ClientThread> listeclient = new ArrayList<ClientThread>();
	ServerSocket socketserver;

	public Socketlistener(int portnbr) {
		// TODO Auto-generated constructor stub

		try {
			// creation d'un objet Serveur socket avec un NBport precis
			socketserver = new ServerSocket(portnbr);

		} catch (IOException e) {
			System.out.println("Désole y a une erreur !" + e.getMessage());
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {

				// accepter la connexion d'un client en acceptant sont socket
				Socket socketconnexion = socketserver.accept();

				// ouverture du flux entrant et recuperation du login
				BufferedReader in = new BufferedReader(new InputStreamReader(socketconnexion.getInputStream()));
				String login = in.readLine();
				LogWriter.getInstance("Bienvenue a iiiiiiiiiiii" + login);
				PrintWriter out = new PrintWriter(socketconnexion.getOutputStream());
				System.out.println(login + " Bienvenu");

				// creation du thread Client et démmarrer le run du thread
				ClientThread ct = new ClientThread(socketconnexion, login, in, out, this);
				ct.start();
				// ajout du client dans la liste
				listeclient.add(ct);

				broadcast("bienvenue a " + login, login);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param data
	 *            message a envoyé a tout le monde
	 * @throws IOException
	 *             les exetion pris en charge par le logiciel
	 */
	public void broadcast(String data, String login) throws IOException {
		// boucle pour envoyer le message a tout le monde
		for (int i = 0; i < listeclient.size(); i++) {
			// envoie du message a une personne par a port au i
			listeclient.get(i).envoie(data);
		}

	}

	@Override
	public void signalMessage(String message, ClientThread client) {
		try {
			broadcast(message, client.login);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void signalDeconnexion(ClientThread client) {

		String nom = client.login;
		client.interrupt();
		try {

			broadcast("deconnexion de " + nom, nom);
			LogWriter.getInstance("deconnexion de " + nom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

/*
 * socketserver.close(); socketduserveur.close();
 */