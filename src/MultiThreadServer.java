import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultiThreadServer {
    private static int clientNo = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(8005);
                System.out.println("Serveren er startet " + new Date());

                while (true) {
                    Socket socket = server.accept();
                    clientNo++;

                    System.out.println("Starting thread for client: " + clientNo + " at " + new Date());
                    InetAddress inetAddress = socket.getInetAddress();
                    System.out.println("Client " + clientNo + "'s host name is " + inetAddress.getHostName());
                    System.out.println("Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress());

                    new Thread(new HandleAClient(socket)).start();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    static class HandleAClient implements Runnable {
        private Socket socket;

        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String input = inputFromClient.readUTF();
                    System.out.println("Modtaget: \"" + input + "\"");
                    outputToClient.writeUTF("Du har skrevet: \"" + input + "\"");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
