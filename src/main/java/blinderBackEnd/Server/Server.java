package blinderBackEnd.Server;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.GameService;

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
    private static ArrayList<Game> games = GameService.getGamesList();

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        while(true){
            System.out.println("[Server] Waiting client connection");
            Socket client = listener.accept();
            System.out.println("[Server] Connected to client!");
            ClientHandler clientThread = new ClientHandler(client, clients, games);
            clients.add(clientThread);
            pool.execute(clientThread);
            for(ClientHandler aClient : clients){
                System.out.println(aClient+" is connected to the server");
            }
        }
    }

    /*
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
    }*/
}
