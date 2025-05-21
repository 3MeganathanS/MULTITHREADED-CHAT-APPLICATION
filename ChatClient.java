//import statements
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            // Thread to print incoming messages
            Thread listener = new Thread(() -> {
                try {
                    String incoming;
                    while ((incoming = serverIn.readLine()) != null) {
                        System.out.println(incoming);
                    }
                } catch (IOException ex) {
                    System.out.println("Lost connection to server.");
                }
            });
            listener.start();

            // Main loop to send user input
            String userInput;
            while ((userInput = consoleIn.readLine()) != null) {
                serverOut.println(userInput);
            }
        } catch (IOException ex) {
            System.err.println("Client error: " + ex.getMessage());
        }
    }
}
