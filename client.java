import java.util.Scanner;
import java.net.Socket;
import java.io.*;
import java.net.InetAddress;
/*import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;*/






public class client{

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Starting...");

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            Socket myClient = new Socket( localhost, 6282);
            
            System.out.println("Connexion...");
            InputStream  inClient = myClient.getInputStream();
            OutputStream outClient = myClient.getOutputStream();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(inClient));
            PrintStream out = new PrintStream(outClient);

            Thread send = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true) {
                      /* System.out.println(">"); */
                        msg = scan.nextLine();
                        out.println(msg);
                        out.flush();  } } });
            send.start();
            


            Thread receive = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while(msg != null) {
                            System.out.println(msg);
                            msg = in.readLine();
                        }
                        System.out.println("Server disconnected");
                        out.close();
                        myClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } } });
            receive.start();



    
        } catch (Exception e) {
            System.out.println("error: "+ e);
        }
        

            

    

    }
}
