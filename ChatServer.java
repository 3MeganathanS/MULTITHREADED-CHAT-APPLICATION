import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Vector;

public class ChatServer {
    private static Vector<ClientHandler> clients = new Vector<>();
    private static int clientCount = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server listening on port 1234");  // startup message
            while (true) {
                Socket socket = serverSocket.accept();            // wait for client
                String userName = "User" + (++clientCount);
                ClientHandler handler = new ClientHandler(socket, userName, clients);
                clients.add(handler);
                handler.start();                                 // spawn handler thread
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private final String userName;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final Vector<ClientHandler> clients;

    public ClientHandler(Socket socket, String userName, Vector<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.userName = userName;
        this.clients = clients;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Welcome, " + userName + "!");      // welcome message
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(userName + ": " + msg);   // log on server
                broadcast(userName + ": " + msg);
            }
        } catch (IOException e) {
            System.out.println(userName + " disconnected");  // client left
        } finally {
            cleanup();
        }
    }

    private void broadcast(String message) {
        for (ClientHandler c : clients) {
            if (c != this) {
                c.out.println(message);     // send to others
            }
        }
    }

    private void cleanup() {
        try { 
            socket.close(); 
        } 
        catch (IOException ignored)
            {
                
            }
        clients.remove(this);
    }
}
