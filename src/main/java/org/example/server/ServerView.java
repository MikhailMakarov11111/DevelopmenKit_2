package org.example.server;

public interface ServerView {
    void showMessage(String text);
    void setServerController(ServerController serverController);
}
