/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;

public class TTTServer {

    public static void main(String[] args) {

        ServerSocket server; // this is the "door"

        Socket toclientsocket;


        try {    // NOTE - must be within a try-clause or throw exceptions!!!!

            server = new ServerSocket(4030);   //listen at the door
            System.out.println("waiting for connection");
            toclientsocket = server.accept();   // block UNTIL request received

            //AT THIS POINT CONNECTION MADE

            System.out.println("RECEIVED REQUEST");

        }   // end try
        catch (IOException e) {}
    }
}
