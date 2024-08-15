package org.example.server;

public interface ServerRepository {
    void saveInLog(String text);
    String readLog();
}
