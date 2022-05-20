import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String LOCALHOST = "127.0.0.1";
    private static final int PORT = 23444;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(LOCALHOST, PORT)) {
            // Получаем входящий и исходящий потоки информации
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 Scanner scanner = new Scanner(System.in)) {
                String msg;
                while (true) {
                    System.out.println("Введите число для расчета значения N-го члена ряда Фибоначчи");
                    msg = scanner.nextLine();
                    out.println(msg);
                    System.out.println("SERVER: " + in.readLine());
                    if ("end".equals(msg)) break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
