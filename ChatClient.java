import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        // Thread to read messages from server
        Thread reader = new Thread(() -> {
            String msg;
            try {
                while ((msg = input.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        });
        reader.start();

        // Main thread to send messages
        String userMsg;
        while ((userMsg = keyboard.readLine()) != null) {
            output.println(userMsg);
        }

        socket.close();
    }
}