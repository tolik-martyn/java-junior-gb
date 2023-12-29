package hw5_client_server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ServerProgram {
    private static final int SERVER_PORT = 1400;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            Server server = new Server(serverSocket);
            server.runServer();
        } catch (UnknownHostException e) {
            System.err.println("\"UnknownHostException\" в классе \"Program\" пакета \"server\".");
        } catch (IOException e) {
            System.err.println("\"IOException\" в классе \"Program\" пакета \"server\".");
        }
    }
}