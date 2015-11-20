
public interface ClientThreadObserver {

	/**
	 * 
	 * @param message
	 *            message recu du client
	 * @param client
	 *            le thread du client
	 */
	public void signalMessage(String message, ClientThread client);

	/**
	 * 
	 * @param client
	 *            le thread du client a deconnecter
	 */
	public void signalDeconnexion(ClientThread client);

}
