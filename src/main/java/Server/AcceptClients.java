package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptClients implements Runnable{
    private final World world;
    private ServerSocket s;

    public AcceptClients(World world) throws IOException{
        this.world = world;
        s = new ServerSocket( Server.PORT);
        System.out.println("Server.Server running & waiting for client connections.");
    }

    public void run() {
        while(true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket, world);
                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
