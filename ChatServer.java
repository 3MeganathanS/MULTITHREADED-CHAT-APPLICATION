import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Vector<ClientHandler> clients = new Vector<>();
    private static int clientId = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected.");
            ClientHandler client = new ClientHandler(socket, "User" + (++clientId), clients);
            clients.add(client);
            client.start();
        }
    }
}

class ClientHandler extends Thread {
    private String name;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Vector<ClientHandler> clients;

    public ClientHandler(Socket socket, String name, Vector<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.name = name;
        this.clients = clients;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Welcome " + name + "!");
    }

    public void run() {
        try {
            String msg;
            while ((msg = input.readLine()) != null) {
                System.out.println(name + ": " + msg);
                for (ClientHandler client : clients) {
                    if (client != this) {
                        client.output.println(name + ": " + msg);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(name + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            clients.remove(this);
        }
    }
}