package blinderBackEnd.Server;

import blinderBackEnd.model.PlaylistService;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    //public static Socket socket;

    //public Client(){}

    /*public static void clientConnection() throws IOException {
        Client.socket = new Socket("localhost", Server.PORT);

    }*/

    public static void sendMessageToServer(String message){

    }



    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", Server.PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        while(true) {
            System.out.println("> ");
            String command = keyboard.readLine();

            if(command.equals("quit")) break;

            out.println(command);

            String serverResponse = input.readLine();
            switch(serverResponse){
                case "lance cette fonction":
                    System.out.println("reponse reçue du serveur");
            }
            //System.out.println("Server : " + serverResponse);
        }
    }
}
