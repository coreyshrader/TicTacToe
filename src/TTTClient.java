/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class TTTClient {
    static char[][] board = {{' ', ' ', ' '},
                             {' ', ' ', ' '},
                             {' ', ' ', ' '}};
    static DataInputStream instream;
    static DataOutputStream outstream;
    static Socket toserversocket;
    static int   reply;   // for later use

    public static void main( String[] args)
    {





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

    public static void printBoard() {
        System.out.printf("\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n\n",
                board[0][0],board[0][1],board[0][2],board[1][0],board[1][1],board[1][2],board[2][0],board[2][1],board[2][2]);
    }

    public static void localUD(int r, int c, char m){// local board update with chosen move
        //move is always row-column
        //assumes that board has been checked for open spot
        board[r][c] = m;
    }
}

