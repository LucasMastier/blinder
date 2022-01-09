package blinderBackEnd.Server;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();

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
                Player player = (Player) in.readObject();
                System.out.println("Received player "+player.getUsername()+" from client");
                //out.println(line);
                /*
                switch(line){
                    case "TEST":
                        out.println("Menu");
                        break;
                    case "Send":
                        outToAll("hey");
                }*/


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

    /*
    public synchronized void sendMessage(String val){
        out.write(val+"\r\n");
        out.flush();
    }*/
}
