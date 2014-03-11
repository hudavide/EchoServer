package echoserver;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadServer extends Thread {

    private Socket s;
	private boolean maiuscolo = false;
	
    public ThreadServer(Socket s) {

        this.s = s;
    }

    public void run() {
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
			while(true){
				String command = in.readLine();
				if(command.equals("fine")){
					s.close();
				}else if(command.equals("Maiuscole: ON")){
					maiuscolo = true;
				}else if(command.equals("Maiuscole: OFF")){
					maiuscolo = false;
				}else{
					if(maiuscolo)
						command = command.toUpperCase();
					out.println(command);
				}
			}
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}