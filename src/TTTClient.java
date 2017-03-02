/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;

public class TTTClient {

    public static void main( String[] args)
    {

        Socket toserversocket;
        int   reply;   // for later use

        System.out.println("CLIENT is attempting connection....");
        try {
            toserversocket = new Socket("localhost", 7788);
            System.out.println("CONNECTION HAS BEEN MADE");
        }
        catch (IOException  e) {};
    }
}
