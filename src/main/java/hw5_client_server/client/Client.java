package hw5_client_server.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final String name;

    public Client(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything();
        }
    }

    /**
     * Отправить сообщение.
     */
    public void sendMessage() {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String message = consoleReader.readLine();
                    if (message.equals("end")) {
                        closeEverything();
                        break;
                    }
                    if (message.startsWith("$")) {
                        // Личное сообщение
                        String[] parts = message.split(" ", 2);
                        if (parts.length == 2) {
                            String recipient = parts[0].substring(1);
                            String msg = parts[1];
                            bufferedWriter.write("Личное сообщение от пользователя " + name + ": " + msg);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } else {
                            System.out.println("Неправильный формат личного сообщения. Используйте $name message");
                        }
                    } else {
                        // Общее сообщение
                        bufferedWriter.write(name + ": " + message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    /**
     * Слушатель для входящих сообщений.
     */
    public void listenForMessage() {
        new Thread(() -> {
            String message;
            while (socket.isConnected()) {
                try {
                    message = bufferedReader.readLine();
                    System.out.println(message);
                } catch (IOException e) {
                    closeEverything();
                }
            }
        }).start();
    }

    /**
     * Завершение работы всех потоков, закрытие клиентского сокета.
     */
    private void closeEverything() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Что-то пошло не так в методе \"closeEverything\" класса \"Client\".");
        }
    }

    public String getName() {
        return name;
    }
}