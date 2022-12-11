package webnet;

import java.io.*;
import java.net.*;

import static webnet.Client.clientPort;

public class Server implements Runnable{
    public static int serverport = 3436;

    public static DatagramSocket ds;
    public static byte buffer[] = new byte[1024];

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
                    ds.send(new DatagramPacket(buffer,pos,InetAddress.getLocalHost(),clientPort));
                    pos=0;

                    break;
                default:
                    buffer[pos++] = (byte) c;
            }
        }
    }


    @Override
    public void run() {
        System.out.println("Servidor running");
        try {
            ds = new DatagramSocket(serverport);
            TheServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}