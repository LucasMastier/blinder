package blinderBackEnd.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static int PORT = 1236;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        while(true){
            System.out.println("[Server] Waiting client connection");
            Socket client = listener.accept();
            System.out.println("[Server] Connected to client!");
            ClientHandler clientThread = new ClientHandler(client, clients);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }
    public synchronized void onConnectionReady(ClientHandler conn){
        clients.add(conn);
        sendToAllConnections("Client connect: "+conn);
    }
    public synchronized void onReceiveString(ClientHandler conn, String val){
        sendToAllConnections(val);
    }
    public synchronized void onDisconnect(ClientHandler conn){
        clients.remove(conn);
        sendToAllConnections("Client disconect: "+conn);
    }
    private void sendToAllConnections(String mess){
        System.out.println(mess);
        final int length=clients.size();
        for(int i=0;i<length;i++){
            clients.get(i).sendMessage(mess);
        }
    }
}
