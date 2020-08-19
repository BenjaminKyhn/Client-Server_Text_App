import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        int port = 8004;
        DataInputStream in;
        DataOutputStream out;
        ServerSocket server;
        Socket socket;

        try {
            server = new ServerSocket(port);

            System.out.println("Serveren er startet " + new Date());

            socket = server.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true){
                String input = in.readUTF();
                System.out.println("Modtaget: \"" + input + "\"");
                out.writeUTF("Du har skrevet: \"" + input + "\"");
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
