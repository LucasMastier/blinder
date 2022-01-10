package blinderBackEnd.Server;

import blinderBackEnd.model.*;

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
    private static ExecutorService pool = Executors.newFixedThreadPool(100);
    private static ArrayList<Game> games = GameService.getGamesList();

    public static ArrayList<Game> getGames(){
        return games;
    }

    public static ArrayList<ClientHandler> getClients(){
        return clients;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        Song vvs = new Song("Ninho","VVS", "src/main/resources/mp3files/VVS.mp3");
        Song tr = new Song("Leto","Tout recommencer", "src/main/resources/mp3files/Tout_recommencer.mp3");
        Song fr = new Song("Freeze Corleone","Freeze raël", "src/main/resources/mp3files/Freeze_Rael.mp3");
        Song hs = new Song("Hamza","HS", "src/main/resources/mp3files/HS.mp3");
        Song em = new Song("Eminem","Real Slim Shady","src/main/resources/mp3files/eminem.mp3");
        Song dk = new Song("Drake","God's Plan","src/main/resources/mp3files/drake.mp3");
        Song aw = new Song("Alpha Wann","Le Piège","src/main/resources/mp3files/Alpha_Wann.mp3");
        Song da = new Song("Damso","Morose","src/main/resources/mp3files/Damso.mp3");
        Song ni = new Song("Niska","Salé","src/main/resources/mp3files/Niska.mp3");
        Song pnl = new Song("PNL","Au DD","src/main/resources/mp3files/PNL.mp3");


        Playlist rap = new Playlist("Rap");
        rap.addToPlaylist(vvs);
        rap.addToPlaylist(tr);
        rap.addToPlaylist(fr);
        rap.addToPlaylist(hs);
        rap.addToPlaylist(em);
        rap.addToPlaylist(dk);
        rap.addToPlaylist(aw);
        rap.addToPlaylist(da);
        rap.addToPlaylist(ni);
        rap.addToPlaylist(pnl);

        PlaylistService.setCurrentPlaylist(rap);
        PlaylistService.addToPlaylists(rap);

        Game game = new Game(rap, "Partie 1");
        GameService.addGameToList(game);



        while(true){
            System.out.println("[Server] Waiting client connection");
            Socket client = listener.accept();
            System.out.println("[Server] Connected to client!");
            ClientHandler clientThread = new ClientHandler(client);
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
