package org.example;

import org.example.client.ClientController;
import org.example.client.ClientGUI;
import org.example.server.FileStorage;
import org.example.server.ServerController;
import org.example.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerController serverController = new ServerController(new ServerWindow(), new FileStorage());
        new ClientController(new ClientGUI(), serverController);
        new ClientController(new ClientGUI(), serverController);
    }
}