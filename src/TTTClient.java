/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;

public class TTTClient {

    public static void main( String[] args)
    {

        char[][] board = {{' ', ' ', ' '},
                          {' ', ' ', ' '},
                          {' ', ' ', ' '}};
        DataInputStream instream;
        DataOutputStream outstream;
        Socket toserversocket;
        int   reply;   // for later use

        System.out.println("CLIENT is attempting connection....");

        try {
            toserversocket = new Socket("localhost", 7788);
            System.out.println("CONNECTION HAS BEEN MADE");

            instream = new  DataInputStream( toserversocket.getInputStream());
            outstream = new DataOutputStream( toserversocket.getOutputStream());

            //PrintWriter out = new PrintWriter(outstream, true);
            //BufferedReader in = new BufferedReader(instream);

            outstream.writeDouble( 3.0 );
            double result = instream.readDouble( );

            System.out.println( "The answer produced was " + result );

        }
        catch (IOException  e) {};
    }
}
