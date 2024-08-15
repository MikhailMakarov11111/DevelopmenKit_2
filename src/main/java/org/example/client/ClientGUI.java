package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JTextArea log;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField password;
    private JButton btnLogin, btnSend;
    private JPanel headerPanel;
    private ClientController clientController;


    public ClientGUI() {
        setting();
        createPanel();

        setVisible(true);
    }

    private void setting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }


    @Override
    public void showMessage(String msg) {
        log.append(msg + "\n");
    }

    @Override
    public void disconnectedFromServer() {
        setHeaderPanelVisible(true);
    }

    /**@apiNote
     * Метод для отключения клиента от сервера
     */
    public void disconnectFromServer() {
        clientController.disconnectFromServer();
    }

    /**@apiNote
     * Метод управления видимостью панели подключения к серверу
     * @param visible true - панель видима, false - панель невидима
     */
    public void setHeaderPanelVisible(boolean visible) {
        headerPanel.setVisible(visible);
    }

    /**@apiNote
     * Метод для подключения клиента к серверу
     */
    public void login() {
        if (clientController.connectToServer(tfLogin.getText())) {
            setHeaderPanelVisible(false);
        }
    }

    /**@apiNote
     * Метод отправки сообщений при нажатии на кнопку "Send" или Enter.
     */
    private void message() {
        clientController.message(tfMessage.getText());
        tfMessage.setText("");
    }

    /**@apiNote
     * Метод создания панели с виджетами
     */
    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    /**@apiNote
     * Метод создания панели с текстовыми полями для проведения авторизации
     * @return Возвращает панель авторизации
     */
    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("rk");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    /**@apiNote
     * Метод создания области отображения сообщений
     * @return возвращает область сообщений
     */
    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }
    /**@apiNote
     * Метод создания панели для сообщений
     * @return возвращает панель
     */
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    /**@apiNote
     * Метод срабатывающий при важных событиях, связанных с графическим окном (например, закрытие окна)
     * @param e  событие окна
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.disconnectFromServer();
        }
    }

    /**@apiNote
     * Метод создания контроллера в графическом интерфейсе
     * @param clientController контроллер клиента
     */
    @Override
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}