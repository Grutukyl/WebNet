package webnet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Client {
    public static int clientPort = 3436;
    public static DatagramSocket dsCli;
    public static byte[] buffer = new byte[1024];
    public static void TheClient() throws IOException, ClassNotFoundException {
        while (true){
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            dsCli.receive(p);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(p.getData());
            ObjectInputStream obj = new ObjectInputStream(inputStream);
            Document teste = (Document) obj.readObject();
            System.out.println(teste.getNome());

            System.out.println(new String(p.getData(),0,p.getLength()));
        }
    }

    public static void main(String[] args) {
        System.out.println("Cliente running");
        try {
            dsCli = new DatagramSocket(clientPort);
            TheClient();
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
