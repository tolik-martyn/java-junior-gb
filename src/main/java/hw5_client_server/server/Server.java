package hw5_client_server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    /**
     * Сервер для принятия подключений от клиентов и управления чатом.
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void runServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientManager clientManager = new ClientManager(socket);
                Thread thread = new Thread(clientManager);
                thread.start();
            }
        } catch (IOException e) {
            closeSocket();
        }
    }

    private void closeSocket() {
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            System.err.println("Что-то пошло не так в методе \"closeSocket\" класса \"Server\".");
        }
    }
}