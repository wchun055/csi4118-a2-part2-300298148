// Built off of the client/server code provided in class lecture 7.
import java.io.*;
import java.net.*;

public class myServer{
    public static void main(String[] args) {
    // variable declaration
    ServerSocket srv = null;
    Socket clientSocket = null;
    String msg = "";

    // Checking command line argument.
    if (args.length != 1) {
        System.out.println("Usage: java myServer <port>");
        return;
    }

    // Creating socket.
    try {
        srv = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("Server is running...");
    }
    catch (IOException e) {
        System.out.println(e);
        return;
    }

    // Main server loop
        while(true) {
            try {
                // Listen for a connection from client and accept it.
                clientSocket = srv.accept();

                // Reject localhost connections.
                if (clientSocket.getInetAddress().isLoopbackAddress()) {
                    System.out.println("Localhost connection rejected.");
                    clientSocket.close();
                    continue;
                }
                
                // Create input and output streams.
                BufferedReader is = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

                PrintStream os = new PrintStream(
                    clientSocket.getOutputStream());

                // GREETINGS when connection is made.
                os.println("GREETINGS");

                System.out.println("Client connected.");

                // Read commands from client.
                while ((msg = is.readLine()) != null) {

                    // Student number, parameter.
                    String[] parts = msg.split(" ", 3);

                    if (parts.length < 2) {
                        continue;
                    }

                    String commandType = parts[0];
                    String studentNumber = parts[1];
                    String parameter = "";

                    if (parts.length == 3) {
                        parameter = parts[2];
                    }

                    String response = "";

                    try {
                        FileWriter fw = new FileWriter("server.log", true);

                        fw.write("IP: " +
                            clientSocket.getInetAddress().getHostAddress() +
                            " Student Number: " + studentNumber +
                            " Operation: " + msg + "\n");

                        fw.close();
                    }
                    catch (IOException e) {
                        System.out.println("Log error: " + e);
                    }

                    // Operation A: IP Address
                    if (commandType.equals("typeA")) {

                        response = clientSocket.getInetAddress()
                            .getHostAddress();
                    }
                    // Operation B: Capitalize
                    else if (commandType.equals("typeB")) {

                        response = parameter.toUpperCase();
                    }
                    // Operation C: Store message
                    else if (commandType.equals("typeC")) {
                        msg = parameter;
                        response = "ACKN";
                    }

                    // Operation D: Exit
                    else if (commandType.equals("typeD")) {
                        response = "EXIT";
                        os.println(response);
                        break;
                    }

                    else {
                        continue;
                    }

                    os.println(response);
                }

                // Close the client connection.
                is.close();
                os.close();
                clientSocket.close();

                System.out.println("Client disconnected.");

            }
        catch(IOException e) {
            System.out.println(e);
        }
    }
    }
}