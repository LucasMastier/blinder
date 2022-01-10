package blinderBackEnd.Server;

import blinderBackEnd.model.Game;
import blinderBackEnd.model.GameService;
import blinderBackEnd.model.Player;
import blinderBackEnd.model.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection implements Runnable{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }


    @Override
    public void run() {
        try{
            while(true){
                Request serverResponse = (Request) in.readObject();

                switch(serverResponse.getMessage()){
                    case "UpdatePlayersList":

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
}
