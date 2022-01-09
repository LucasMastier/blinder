package blinderBackEnd.Server;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.Player;
import blinderBackEnd.model.Request;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ArrayList<ClientHandler> clients;
    private ArrayList<Game> games;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, ArrayList<Game> games) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        this.games = games;
        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
    }



    @Override
    public void run() {
        try{
            while(true){
                //String line = in.readLine();
                //System.out.println("Client : "+line);
                Request request = (Request) in.readObject();

                switch(request.getMessage()){
                    case"AddPlayerToGame":
                        System.out.println("Received player "+request.getPlayer().getUsername()+" from client");
                        for(Game serverGame : games){
                            System.out.println(serverGame.getName());
                            if(serverGame.getName().equals(request.getGame().getName())){
                                serverGame.addPlayer(request.getPlayer());
                                System.out.println("Une partie correspond");
                            }
                        }
                        updateAllGamePlayersList(request.getGame());

                        break;

                    case"test":
                        System.out.println("test reussi");
                        break;

                    case "UpdatePlayersList":
                        for(Game serverGame : games){
                            if(serverGame.getName().equals(request.getGame().getName())){
                                for(Player player : serverGame.getPlayersList()){
                                    System.out.println(player.getUsername()+" is connected to "+serverGame.getName());
                                }
                                out.writeObject(serverGame.getPlayersList());
                                break;
                            }
                        }

                }



            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("IO exception in client handler");
            System.err.println(e.getStackTrace());
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToAll(String string) {
        for(ClientHandler client : clients){
            //client.out.println(string);
        }
    }

    private void updateAllGamePlayersList(Game game) throws IOException {
        System.out.println(games.get(0).getPlayersList().get(0).getUsername()+" est dans la partie");
        for(ClientHandler client : clients){
            client.out.writeObject(game.getPlayersList());
            System.out.println("Updated players list for "+client);
        }
        for(Player player : game.getPlayersList()){
            System.out.println("Joueurs présents :");
            System.out.println(player.getUsername());
        }
    }

    /*
    public synchronized void sendMessage(String val){
        out.write(val+"\r\n");
        out.flush();
    }*/
}
