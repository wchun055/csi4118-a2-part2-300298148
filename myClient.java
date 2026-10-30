// Built off of the client/server code provided in class lecture 7.
import java.io.*;
import java.net.*;
import java.util.*;

public class myClient{
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintStream out = null;
        Scanner keyboard = new Scanner(System.in);
        String msg = "";
        String response = "";

        // Check command-line arguments.
        if (args.length != 2) {
            System.out.println("Usage: java myClient <server IP> <port>");
            return;
        }

        try {
            // Create a socket and connect to specified server.
            socket = new Socket(args[0], Integer.parseInt(args[1]));
        } 
        catch (UnknownHostException e) {
            System.out.println("Unknown host.");
            return;
        } 
        catch (IOException e) {
            System.out.println("connect failed");
            return;
        }

        // Read data from socket, write to screen.
        try {
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            response = in.readLine();

            System.out.println("Session has been established.");
            System.out.println("Server: " + response);

            // Continue until user requests disconnection.
            while (true) {
                System.out.print("> ");

                msg = keyboard.nextLine();

                // Send command to server.
                out.println(msg);

                // Read server response.
                response = in.readLine();
                if (response == null) {
                    System.out.println("Server disconnected.");
                    break;
                }

                System.out.println("Server: " + response);

                // Check if exit.
                if (msg.startsWith("typeD ")) {
                    if (response.equals("EXIT")) {
                        System.out.println("Session is terminated.");
                        break;
                    }
                }
            }

            // Close connection.
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
    }}}