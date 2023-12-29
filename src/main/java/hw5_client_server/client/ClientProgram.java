package hw5_client_server.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Программа клиента для подключения к серверу чата.
 */
public class ClientProgram {
    private static final int SERVER_PORT = 1400;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите своё имя: ");
        String name = scanner.nextLine();

        try {
            InetAddress address = InetAddress.getByName("localhost");
            Socket socket = new Socket(address, SERVER_PORT);
            Client client = new Client(socket, name);

            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort:" + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();
        } catch (UnknownHostException e) {
            System.err.println("\"UnknownHostException\" в классе \"Program\" пакета \"client\".");
        } catch (IOException e) {
            System.err.println("\"IOException\" в классе \"Program\" пакета \"client\".");
        }
    }
}