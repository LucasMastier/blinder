package blinderBackEnd.Server;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;

    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private ArrayList<Game> games;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, ArrayList<Game> games) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        this.games = games;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
        inputStream = clientSocket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }



    @Override
    public void run() {
        try{
            while(true){
                String line = in.readLine();

                out.println(line);
                System.out.println("Client : "+line);
                /*switch(line){
                    case "TEST":
                        out.println("Menu");
                        break;
                    case "Send":
                        outToAll("SALUT LES FDP DE CLIENTS");
                        break;
                }*/

                String[] command = line.split(" ");
                if(command[0].equals("AddPlayerToGame")){
                    for(Game game : games){
                        if(game.getName().equals(command[1])){
                            System.out.println("Joueur "+command[2]+" bien recu");
                            game.addPlayer(new Player(command[2]));
                            outToAll("ClientsAddPlayerToGame "+command[1]+" "+command[2]);
                        }
                    }

                }

            }
        } catch (IOException e) {
            System.err.println("IO exception in client handler");
            System.err.println(e.getStackTrace());
        } finally {
                out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToAll(String string) {
        for(ClientHandler client : clients){
            client.out.println(string);
        }
    }


    public synchronized void sendMessage(String val){
        out.write(val+"\r\n");
        out.flush();
    }
}
