package hw5_client_server.server;

import java.util.ArrayList;

public class ClientManagerSingleton {
    private static ArrayList<ClientManager> clients;
    private ClientManagerSingleton(){
    }

    public static ArrayList<ClientManager> getInstance(){
        if (clients == null){
            clients = new ArrayList<>();
        }
        return clients;
    }
}