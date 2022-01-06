package blinderBackEnd.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int PORT = 1236;
    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        System.out.println("[Server] Waiting client connection");
        Socket client = listener.accept();
        System.out.println("[Server] Connected to client!");

        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
/*
        try{
            while(true){

            }
        }*/
    }
}
