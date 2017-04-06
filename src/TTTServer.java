/*
  Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;

class TTTServer {

    public static void main(String[] args) {

        try {    // NOTE - must be within a try-clause or throw exceptions!!!!

            ServerSocket server = new ServerSocket(7788);

            //noinspection InfiniteLoopStatement
            while (true) {

                System.out.println("waiting for connection");
                Socket toclientsocket = server.accept();

                TTTThread conn = new TTTThread(toclientsocket);

                Thread t = new Thread(conn);
                t.start();
            }
        }   // end try
        catch (IOException e) {
            System.out.println("IOException on socket listen: " + e);
            e.printStackTrace();
        }
    }
}
