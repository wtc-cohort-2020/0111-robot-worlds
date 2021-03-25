

import java.net.*;
import java.io.*;
import java.security.cert.TrustAnchor;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 1999);
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()))
        )
        {
            Scanner scanner = new Scanner(System.in);
            String input;
            out.println("Hello WeThinkCode");
            out.flush();
            String messageFromServer = in.readLine();
            System.out.println("Response: "+messageFromServer);
            while (true){
                input = scanner.nextLine();
                if(input.equalsIgnoreCase("exit")){
                    break;
                }
                else {
                    out.println(input);
                    out.flush();
                }
                messageFromServer = in.readLine();
                System.out.println("Response: "+messageFromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}