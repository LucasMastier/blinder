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


    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
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
                        for(Game serverGame : Server.getGames()){
                            System.out.println(serverGame.getName());
                            if(serverGame.getName().equals(request.getGame().getName())){
                                serverGame.addPlayer(request.getPlayer());
                                System.out.println("Une partie correspond");
                            }
                        }
                        //updateAllGamePlayersList(request.getGame());

                        break;


                    case"test":
                        System.out.println("test reussi");
                        break;

                    case "UpdatePlayersList":
                        for(Game serverGame : Server.getGames()){
                            if(serverGame.getName().equals(request.getGame().getName())){
                                for(Player player : serverGame.getPlayersList()){
                                    System.out.println(player.getUsername()+" is connected to "+serverGame.getName());
                                }
                                updateAllGamePlayersList(serverGame);
                            }
                        }
                        break;

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

    public void outToAll(String string) {
        for(ClientHandler client : Server.getClients()){
            //client.out.println(string);
        }
    }

    public void updateAllGamePlayersList(Game game) throws IOException {
        System.out.println("Joueurs présents :");
        for(Player player : game.getPlayersList()){
            System.out.println(player.getUsername());
        }
        for(ClientHandler client : Server.getClients()){
            client.out.writeObject(game);
            System.out.println("Updated players list for "+client);
        }

    }

    /*
    public synchronized void sendMessage(String val){
        out.write(val+"\r\n");
        out.flush();
    }*/
}
