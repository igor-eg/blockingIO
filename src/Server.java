import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 23444;
    public static void main(String[] args) throws IOException {

        System.out.println("Server started");
        //  Занимаем порт, определяя серверный сокет
        ServerSocket servSocket = new ServerSocket(PORT);
        while (true) {
            //  Ждем подключения клиента и получаем поток и для дальнейшей работы
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {

                    Integer j = Integer.valueOf(line);
                    int[] arr = new int[j];
                    arr[0] = 0;
                    arr[1] = 1;
                    for (int i = 2; i < arr.length; ++i) {
                        arr[i] = arr[i - 1] + arr[i - 2];
                    }
                    for (int i = 0; i < arr.length; ++i) {
                        System.out.println(arr[i]);
                    }
                    out.println("Значение N-го члена ряда Фибоначчи: " + arr[arr.length - 1]);

                    if (line.equals("end")) {
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

        }
    }
}