package blinderBackEnd.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void clientConnection() throws IOException {
        Socket socket = new Socket("localhost", Server.PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        while(true) {
            System.out.println("> ");
            String command = keyboard.readLine();

            //if(command.equals("quit")) break;

            out.println(command);

            String serverResponse = input.readLine();
            System.out.println("Server : " + serverResponse);
        }
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
            System.out.println("Server : " + serverResponse);
        }
    }
}
