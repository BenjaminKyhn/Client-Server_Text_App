import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        int port = 8005;
        String host = "localhost";
        DataInputStream in;
        DataOutputStream out;
        Socket socket;

        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while(true){
                System.out.println("Skriv noget til serveren: ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                out.writeUTF(name);
                System.out.println(in.readUTF());
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
