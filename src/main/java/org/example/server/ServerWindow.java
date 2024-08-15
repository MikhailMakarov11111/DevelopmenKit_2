package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JButton btnStart, btnStop;
    static JTextArea log;
    private ServerController serverController;

    /**@apiNote
     * Конструктор окна сервера
     */
    public ServerWindow(){
        setting();
        createPanel();
        setVisible(true);
    }

    /**@apiNote
     * Метод для настройки окна сервера
     */
    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
    }

    /**@apiNote
     * Метод создания области отображения сообщений
     */
    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    /**@apiNote
     * Метод создания панели с кнопками запуска и остановки сервера
     * @return Возвращает панель с кнопками
     */
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stop();
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    /**@apiNote
     * Метод отображения сообщений в окне сервера
     * @param text текст отображаемого сообщения
     */
    @Override
    public void showMessage(String text) {
        log.append(text);
    }

    /**@apiNote
     * Метод создания контроллера в графическом интерфейсе
     * @param serverController контроллер сервера
     */
    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
