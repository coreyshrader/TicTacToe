/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;

public class TTTServer {

    public static void main(String[] args) {

        ServerSocket server; // this is the "door"
        DataInputStream  in;
        DataOutputStream out;
        Socket toclientsocket;


        try {    // NOTE - must be within a try-clause or throw exceptions!!!!

            server = new ServerSocket(7788);   //listen at the door
            System.out.println("waiting for connection");
            toclientsocket = server.accept();   // block UNTIL request received

            //AT THIS POINT CONNECTION MADE
            System.out.println("RECEIVED REQUEST");

            in = new DataInputStream(toclientsocket.getInputStream());
            out = new DataOutputStream(toclientsocket.getOutputStream());

            double D = in.readDouble( );  // read a double from client
            D = D * D;
            out.writeDouble(D);		// write the square to the client

        }   // end try
        catch (IOException e) {}
    }
}
