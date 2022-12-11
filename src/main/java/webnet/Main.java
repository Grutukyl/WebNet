package webnet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static webnet.Client.clientPort;


public class Main {
    public static int serverport = 3432;

    public static DatagramSocket ds;
    public static byte buffer[] = new byte[1024];
    public static Document document = new Document();
    public static void TheServer() throws IOException {
        int pos = 0;
        ds = new DatagramSocket(serverport);
        while(true){
            int c = System.in.read();
            switch (c){
                case -1:
                    System.out.println("Server Quits.");
                    ds.close();
                    return;

                case '\r':
                    break;
                case '\n':
                    Charset charset =  StandardCharsets.US_ASCII;

                    System.out.println("Enviando :" + document.getNome());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(document);
                    objectOutputStream.flush();
                    byte[] en = byteArrayOutputStream.toByteArray();
                    for (int i = 0; i < en.length; i++) {
                        buffer[pos++] = en[i];
                    }
                    ds.send(new DatagramPacket(buffer,pos, InetAddress.getLocalHost(),clientPort));
                    pos = 0;
                    break;
                default:

            }
        }
    }

    public static void main(String[] args) throws IOException {
       TheServer();

    }

}