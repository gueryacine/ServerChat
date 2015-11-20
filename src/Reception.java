import java.io.BufferedReader;
import java.io.IOException;


public class Reception extends Thread implements Runnable {

	private BufferedReader in;
	private String message = null, login = null;
	
	public Reception(BufferedReader in){
		
		this.in = in;

	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			System.out.println(login+" : "+message);
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
