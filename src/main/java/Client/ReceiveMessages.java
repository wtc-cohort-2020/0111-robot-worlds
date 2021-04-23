package Client;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveMessages implements Runnable{
    BufferedReader in;

    public ReceiveMessages(BufferedReader in){
        this.in = in;
    }

    public void run() {
        boolean flag = true;
        while (flag){
            try {
                String messageFromServer = in.readLine();
                System.out.println("Response: "+messageFromServer);
                if(messageFromServer.equals("Off")){
                    flag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
