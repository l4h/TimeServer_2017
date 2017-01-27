package com.company;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

class CommandManager {
    enum guestCommands {time, exit}
    enum adminCommands{time, status, exit, stop , }
}

class LoginManager{
    private static int maxGuest=30;
    private int guestSessions=0;
    private static boolean adminLogon=false;
    private boolean adminRelogin=true;
    private Socket clientSocket;



}


class Log{
    File log=new File("timeserverconnection.log");
    public synchronized  void writeLog(String action, Date d, String role, int sessionID, Inet4Address ip, int existingConnections) throws IOException {
        if(!log.exists())log.createNewFile();
                else throw new IOException();
        BufferedWriter fbw=new BufferedWriter(new FileWriter(log));
        fbw.write("Client " +action +"\t" + d + "\t" + role + "\t" + sessionID + "\t" + ip + "\t" + existingConnections+"\n");
        fbw.
    }

}

class ClientManager{
    static private Map<Integer, ClientConnection> mClConnection;

    public ClientManager(){
        mClConnection=new HashMap<>();
    }
    public boolean NewCl(ClientConnection y){

    }

}

class User{
    private String login;
    private Map<User,ClientConnection> userMap;
    private ArrayList<String> roles;


}

class Accepter {
    ServerSocket ss;
    Socket cs;
    int connections;

    public Accepter() {
        try {
            ss = new ServerSocket(3366);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Accepter(int listenPort) {
        try {
            ss = new ServerSocket(listenPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMainThread() {
        connections=0;
        while (true) {
            try {
                cs = ss.accept();
                ClientConnection cc=new ClientConnection(cs);
                connections++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}//end of the class Accepter


class ClientConnection implements Runnable {
    private UUID uuid;
    private Random r;
    private Socket clSocket;
    private Inet4Address clIPV4;
    private  int sessionId;
    private Thread clThread;


    public ClientConnection(Socket s){
        clSocket=s;
        r=new Random();
        clIPV4= (Inet4Address) clSocket.getInetAddress();
        uuid=new UUID(r.nextLong(),r.nextLong()+5);
        sessionId=uuid.variant();
        clThread=new Thread(this,"clConnection");
        clThread.start();
    }


    public void run(){
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(clSocket.getInputStream()));
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(clSocket.getOutputStream()));
            bw.write("hellow new realisation ");
            bw.flush();
            bw.write(sessionId+" "+clIPV4+" ");
            bw.flush();
            bw.write("Bye!");
            Thread.sleep(10000);
            bw.close();
            br.close();
            clSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}// end ClientConnection


public class Main {

    public static void main(String[] args) {

        Accepter mainServer = new Accepter(3366);
        mainServer.startMainThread();

    }
}
