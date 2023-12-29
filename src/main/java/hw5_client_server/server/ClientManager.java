package hw5_client_server.server;

import java.io.*;
import java.net.Socket;

public class ClientManager implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            ClientManagerSingleton.getInstance().add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything();
        }
    }

    /**
     * Завершение работы всех потоков, закрытие соединения с клиентским сокетом,
     * удаление клиентского сокета из коллекции.
     */
    private void closeEverything() {
        removeClient();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Что-то пошло не так в методе \"closeEverything\" класса \"ClientManager\"");
        }
    }

    /**
     * Удаление клиента из коллекции.
     */
    private void removeClient() {
        ClientManagerSingleton.getInstance().remove(this);
        System.out.println(name + " покинул чат.");
    }

    @Override
    public void run() {
        String messageFromClient;
        try {
            while (socket.isConnected()) {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) {
                    // Клиент отключился
                    closeEverything();
                    break;
                }
                broadcastMessage(messageFromClient);
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    /**
     * Отправка сообщения всем слушателям.
     *
     * @param message сообщение
     */
    private void broadcastMessage(String message) {
        for (ClientManager client : ClientManagerSingleton.getInstance()) {
            try {
                if (message.startsWith("Личное сообщение пользователю ")) {
                    // Отправка личного сообщения
                    if (message.startsWith("Личное сообщение пользователю " + client.name)) {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                } else if (!client.name.equals(name)) {
                    // Отправка общего сообщения только другим клиентам
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything();
            }
        }
    }
}