import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import com.sun.xml.internal.messaging.saaj.util.Base64;


public class LogWriter {
	
		private LogWriter(){
			
			
		}
	
		static File f = new File("log.txt");

		
		private static LogWriter INSTANCE = new LogWriter();
		
		
		public static LogWriter getInstance(String message)
		{	

				try {
					FileWriter fw = new FileWriter(f);
					fw.write(message + "\n");
					fw.flush();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("probleme d'ecriture sur le fichier log \n");
				}

			
			return INSTANCE;
		}
		


}
