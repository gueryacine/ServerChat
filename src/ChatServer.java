
public class ChatServer extends Thread implements Runnable {

	public void run() {
		System.out.println("Serveur Open");
		Socketlistener socketlistener = new Socketlistener(5000);
		socketlistener.run();

	}
}
