package org.example.server;

import java.io.FileReader;
import java.io.FileWriter;

public class FileStorage implements ServerRepository {
    public static final String LOG_PATH = "src/main/resources/log.txt";

    /**@apiNote
     * Метод для записи лога сервера в файл
     * @param text текст, записываемый в файл
     */
    @Override
    public void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**@apiNote
     * Метод для извлечения сохраненной истории сообщений из файла
     * @return возвращает строку сообщений
     */
    @Override
    public String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}