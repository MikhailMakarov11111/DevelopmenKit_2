package org.example.client;

import org.example.server.ServerController;

public class ClientController {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private ServerController serverController;

    /**@apiNote
     * Конструктор контроллера клиента
     * @param clientView Интерфейс клиента
     * @param serverController Контроллер сервера
     */
    public ClientController(ClientView clientView, ServerController serverController) {
        this.clientView = clientView;
        this.serverController = serverController;
        clientView.setClientController(this);
    }

    /**@apiNote
     * Метод для получения имени клиента
     * @return Возвращает имя клиента
     */
    public String getName() {
        return name;
    }

    /**@apiNote
     * Метод для подключения клиента к серверу
     * @param name Имя клиента
     * @return Возвращает статус подключения (true - подключение выполнено, false - подключение не выполнено)
     */
    public boolean connectToServer(String name) {
        this.name = name;
        if (serverController.connectUser(this)){
            showOnWindow("Client has been connected\n");
            connected = true;
            String log = serverController.getHistory();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Connection failed\n");
            return false;
        }
    }

    /**@apiNote
     * Метод для отключения клиента со стороны сервера
     */
    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("Client has been disconnected\n");
        }
    }

    /**@apiNote
     * Метод для отключения клиента от сервера (например, при закрытии графического интерфейса)
     */
    public void disconnectFromServer() {
        serverController.disconnectUser(this);
    }

    /**@apiNote
     * Метод для передачи сообщений от сервера клиенту
     * @param text текст полученного сообщения
     */
    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    /**@apiNote
     * Метод для передачи сообщения от клиента на сервер
     * @param text текст передаваемого сообщения
     */
    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                serverController.message(name + ": " + text);
            }
        } else {
            showOnWindow("No server connection\n");
        }
    }

    /**@apiNote
     * Метод для отображения сообщений в графическом интерфейсе
     * @param text текст отображаемого сообщения
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text);
    }
}