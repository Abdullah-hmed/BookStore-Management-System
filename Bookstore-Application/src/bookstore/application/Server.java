package bookstore.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    private static ServerSocket serverSocket;
    
    public static void main(String[] args) {
        final int portNumber = 8000;

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Setup input and output streams for communication
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read and respond to client messages
                String clientMessage;
                do {
                    try{
                        // Read message from client
                        clientMessage = reader.readLine();
                        System.out.println("Client: " + clientMessage);

                        // Respond based on the client's input
                        String serverResponse = getServerResponse(clientMessage);
                        writer.println(serverResponse);
                    } catch(SocketException e){
                        System.out.println("Client Disconnected.");
                        break; //Loop Break
                    }
                } while (!clientMessage.equals("bye"));

                // Close the connection
                //clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server is shutting down.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String getServerResponse(String clientMessage) {
        // Handle the incoming value and generate a response based on FAQs
        try {
            int faqNumber = Integer.parseInt(clientMessage);
            return getFAQResponse(faqNumber);
        } catch (NumberFormatException e) {
            return "Invalid input. Please enter a number corresponding to an FAQ.";
        }
    }

    private static String getFAQResponse(int faqNumber) {
        // Provide specific responses based on the FAQ number
        // Modify this method based on your specific FAQ responses
        switch (faqNumber) {
            case 1:
                return "The search bar can be used to search books by their names.";
            case 2:
                return "There are no events currently being run, therefore there are no discounts.";
            case 3:
                return "Once logged in, right click on your User Icon in the top right of the window and open the cart.";
            case 4:
                return "Please contact us at bookstore@support.com for any support regarding your order. We will try to assist you with any query regarding any placed order.";
            default:
                return "Invalid FAQ number. Please enter a valid number.";
        }
    }
}
